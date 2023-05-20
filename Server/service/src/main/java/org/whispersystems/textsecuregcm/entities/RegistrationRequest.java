/*
 * Copyright 2023 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.whispersystems.textsecuregcm.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Arrays;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.whispersystems.textsecuregcm.util.ByteArrayAdapter;

public record RegistrationRequest(String sessionId,
                                  @JsonDeserialize(using = ByteArrayAdapter.Deserializing.class) byte[] recoveryPassword,
                                  @NotNull @Valid AccountAttributes accountAttributes,
                                  boolean skipDeviceTransfer) implements PhoneVerificationRequest {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegistrationRequest)) return false;

        RegistrationRequest other = (RegistrationRequest) o;
        return skipDeviceTransfer == other.skipDeviceTransfer &&
                Objects.equals(sessionId, other.sessionId) &&
                Arrays.equals(recoveryPassword, other.recoveryPassword) &&
                Objects.equals(accountAttributes, other.accountAttributes);
    }

    @Override
    public int hashCode() {
        int hash = Objects.hash(sessionId, accountAttributes, skipDeviceTransfer);
        hash = 31 * hash + Arrays.hashCode(recoveryPassword);
        return hash;
    }
}
