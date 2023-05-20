/*
 * Copyright 2023 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.whispersystems.textsecuregcm.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.whispersystems.textsecuregcm.util.ByteArrayAdapter;

public record ChangeNumberRequest(String sessionId,
                                  @JsonDeserialize(using = ByteArrayAdapter.Deserializing.class) byte[] recoveryPassword,
                                  @NotBlank String number,
                                  @JsonProperty("reglock") @Nullable String registrationLock,
                                  @NotBlank String pniIdentityKey,
                                  @NotNull @Valid List<@NotNull @Valid IncomingMessage> deviceMessages,
                                  @NotNull @Valid Map<Long, @NotNull @Valid SignedPreKey> devicePniSignedPrekeys,
                                  @NotNull Map<Long, Integer> pniRegistrationIds) implements PhoneVerificationRequest {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChangeNumberRequest)) return false;

        ChangeNumberRequest other = (ChangeNumberRequest) o;
        return Objects.equals(sessionId, other.sessionId) &&
                Arrays.equals(recoveryPassword, other.recoveryPassword) &&
                Objects.equals(number, other.number) &&
                Objects.equals(registrationLock, other.registrationLock) &&
                Objects.equals(pniIdentityKey, other.pniIdentityKey) &&
                Objects.equals(deviceMessages, other.deviceMessages) &&
                Objects.equals(devicePniSignedPrekeys, other.devicePniSignedPrekeys) &&
                Objects.equals(pniRegistrationIds, other.pniRegistrationIds);
    }

    @Override
    public int hashCode() {
        int hash = Objects.hash(sessionId, number, registrationLock, pniIdentityKey, deviceMessages, devicePniSignedPrekeys, pniRegistrationIds);
        hash = 31 * hash + Arrays.hashCode(recoveryPassword);
        return hash;
    }

}
