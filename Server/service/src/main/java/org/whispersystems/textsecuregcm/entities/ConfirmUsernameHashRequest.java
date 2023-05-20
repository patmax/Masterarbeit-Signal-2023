/*
 * Copyright 2022 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.whispersystems.textsecuregcm.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Arrays;

import org.whispersystems.textsecuregcm.controllers.AccountController;
import org.whispersystems.textsecuregcm.util.ByteArrayBase64UrlAdapter;
import org.whispersystems.textsecuregcm.util.ExactlySize;

public record ConfirmUsernameHashRequest(
    @JsonSerialize(using = ByteArrayBase64UrlAdapter.Serializing.class)
    @JsonDeserialize(using = ByteArrayBase64UrlAdapter.Deserializing.class)
    @ExactlySize(AccountController.USERNAME_HASH_LENGTH)
    byte[] usernameHash,

    @JsonSerialize(using = ByteArrayBase64UrlAdapter.Serializing.class)
    @JsonDeserialize(using = ByteArrayBase64UrlAdapter.Deserializing.class)
    byte[] zkProof
) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConfirmUsernameHashRequest)) return false;

        ConfirmUsernameHashRequest other = (ConfirmUsernameHashRequest) o;
        return Arrays.equals(usernameHash, other.usernameHash) &&
                Arrays.equals(zkProof, other.zkProof);
    }

    @Override
    public int hashCode() {
        int hash = Arrays.hashCode(usernameHash);
        hash = 31 * hash + Arrays.hashCode(zkProof);
        return hash;
    }
}