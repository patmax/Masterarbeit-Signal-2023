/*
 * Copyright 2013-2020 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.whispersystems.textsecuregcm.entities;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;
import javax.annotation.Nullable;

public record AccountIdentityResponse(UUID uuid,
                                      String number,
                                      UUID pni,
                                      @Nullable byte[] usernameHash,
                                      boolean storageCapable) {
                                        @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountIdentityResponse)) return false;

        AccountIdentityResponse other = (AccountIdentityResponse) o;
        return storageCapable == other.storageCapable &&
                Objects.equals(uuid, other.uuid) &&
                Objects.equals(number, other.number) &&
                Objects.equals(pni, other.pni) &&
                Arrays.equals(usernameHash, other.usernameHash);
    }

    @Override
    public int hashCode() {
        int hash = Objects.hash(uuid, number, pni, storageCapable);
        hash = 31 * hash + Arrays.hashCode(usernameHash);
        return hash;
    }
}
