/*
 * Copyright 2022 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.whispersystems.textsecuregcm.entities;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.whispersystems.textsecuregcm.util.ExactlySize;

public record BatchIdentityCheckRequest(@Valid @NotNull @Size(max = 1000) List<Element> elements) {

  /**
   * @param uuid        account id or phone number id
   * @param fingerprint most significant 4 bytes of SHA-256 of the 33-byte identity key field (32-byte curve25519 public
   *                    key prefixed with 0x05)
   */
  public record Element(@Deprecated @Nullable UUID aci,
                        @Nullable UUID uuid,
                        @NotNull @ExactlySize(4) byte[] fingerprint) {

    public Element {
      if (aci == null && uuid == null) {
        throw new IllegalArgumentException("aci and uuid cannot both be null");
      }

      if (aci != null && uuid != null) {
        throw new IllegalArgumentException("aci and uuid cannot both be non-null");
      }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Element)) return false;
        Element that = (Element) o;
        return Objects.equals(aci, that.aci) &&
                Objects.equals(uuid, that.uuid) &&
                Arrays.equals(fingerprint, that.fingerprint);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(aci, uuid);
        result = 31 * result + Arrays.hashCode(fingerprint);
        return result;
    }
  }
}
