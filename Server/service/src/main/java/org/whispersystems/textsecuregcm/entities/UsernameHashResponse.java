/*
 * Copyright 2022 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.whispersystems.textsecuregcm.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.whispersystems.textsecuregcm.controllers.AccountController;
import org.whispersystems.textsecuregcm.util.ByteArrayBase64UrlAdapter;
import org.whispersystems.textsecuregcm.util.ExactlySize;

import java.util.Arrays;

import javax.validation.Valid;

public record UsernameHashResponse(
    @Valid
    @JsonSerialize(using = ByteArrayBase64UrlAdapter.Serializing.class)
    @JsonDeserialize(using = ByteArrayBase64UrlAdapter.Deserializing.class)
    @ExactlySize(AccountController.USERNAME_HASH_LENGTH)
    byte[] usernameHash
) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsernameHashResponse)) return false;
        
        UsernameHashResponse other = (UsernameHashResponse) o;
        return Arrays.equals(usernameHash, other.usernameHash);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(usernameHash);
    }
}
