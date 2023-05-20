/*
 * Copyright 2021 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.whispersystems.textsecuregcm.entities;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;

public record GroupCredentials(List<GroupCredential> credentials, @Nullable UUID pni) {

  public record GroupCredential(byte[] credential, long redemptionTime) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupCredential)) return false;

        GroupCredential other = (GroupCredential) o;
        return redemptionTime == other.redemptionTime &&
                Arrays.equals(credential, other.credential);
    }

    @Override
    public int hashCode() {
        int hash = Long.hashCode(redemptionTime);
        hash = 31 * hash + Arrays.hashCode(credential);
        return hash;
    }
  }
}
