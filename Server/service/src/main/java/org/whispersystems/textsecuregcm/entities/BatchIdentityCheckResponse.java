/*
 * Copyright 2022 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.whispersystems.textsecuregcm.entities;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.whispersystems.textsecuregcm.util.ExactlySize;

public record BatchIdentityCheckResponse(@Valid List<Element> elements) {

  public record Element(@Deprecated @JsonInclude(JsonInclude.Include.NON_EMPTY) @Nullable UUID aci,
                        @JsonInclude(JsonInclude.Include.NON_EMPTY) @Nullable UUID uuid,
                        @NotNull @ExactlySize(33) byte[] identityKey) {

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

        Element other = (Element) o;
        return Objects.equals(aci, other.aci) &&
                Objects.equals(uuid, other.uuid) &&
                Arrays.equals(identityKey, other.identityKey);
    }

    @Override
    public int hashCode() {
        int hash = Objects.hash(aci, uuid);
        hash = 31 * hash + Arrays.hashCode(identityKey);
        return hash;
    }
  }
}
