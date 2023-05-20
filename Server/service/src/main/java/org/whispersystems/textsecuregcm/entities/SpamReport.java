package org.whispersystems.textsecuregcm.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.whispersystems.textsecuregcm.util.ByteArrayAdapter;

import java.util.Arrays;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

public record SpamReport(@JsonSerialize(using = ByteArrayAdapter.Serializing.class)
                         @JsonDeserialize(using = ByteArrayAdapter.Deserializing.class)
                         @NotEmpty byte[] token) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpamReport)) return false;

        SpamReport other = (SpamReport) o;
        return Arrays.equals(token, other.token);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(token);
    }
}
