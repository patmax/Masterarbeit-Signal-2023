/*
 * Copyright 2013-2020 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.whispersystems.textsecuregcm.auth;

import java.security.MessageDigest;
import java.time.Duration;
import java.util.Arrays;
import java.util.Objects;

import javax.annotation.Nullable;
import org.whispersystems.textsecuregcm.util.Util;

public record StoredVerificationCode(String code,
                                     long timestamp,
                                     String pushCode,
                                     @Nullable byte[] sessionId) {

  public static final Duration EXPIRATION = Duration.ofMinutes(10);

  public boolean isValid(String theirCodeString) {
    if (Util.isEmpty(code) || Util.isEmpty(theirCodeString)) {
      return false;
    }

    byte[] ourCode = code.getBytes();
    byte[] theirCode = theirCodeString.getBytes();

    return MessageDigest.isEqual(ourCode, theirCode);
  }

  @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StoredVerificationCode)) return false;

        StoredVerificationCode other = (StoredVerificationCode) o;
        return timestamp == other.timestamp &&
                Objects.equals(code, other.code) &&
                Objects.equals(pushCode, other.pushCode) &&
                Arrays.equals(sessionId, other.sessionId);
    }

    @Override
    public int hashCode() {
        int hash = Objects.hash(code, timestamp, pushCode);
        hash = 31 * hash + Arrays.hashCode(sessionId);
        return hash;
    }
}
