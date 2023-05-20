/*
 * Copyright 2013-2021 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.whispersystems.textsecuregcm.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BaseProfileResponse implements Cloneable {

  @JsonProperty
  private String identityKey;

  @JsonProperty
  private String unidentifiedAccess;

  @JsonProperty
  private boolean unrestrictedUnidentifiedAccess;

  @JsonProperty
  private UserCapabilities capabilities;

  @JsonProperty
  private List<Badge> badges;

  @JsonProperty
  private UUID uuid;

  public BaseProfileResponse() {
  }

  public BaseProfileResponse(final String identityKey,
      final String unidentifiedAccess,
      final boolean unrestrictedUnidentifiedAccess,
      final UserCapabilities capabilities,
      final List<Badge> badges,
      final UUID uuid) {

    this.identityKey = identityKey;
    this.unidentifiedAccess = unidentifiedAccess;
    this.unrestrictedUnidentifiedAccess = unrestrictedUnidentifiedAccess;
    this.capabilities = capabilities;
    this.badges = badges;
    this.uuid = uuid;
  }

  public String getIdentityKey() {
    return identityKey;
  }

  public String getUnidentifiedAccess() {
    return unidentifiedAccess;
  }

  public boolean isUnrestrictedUnidentifiedAccess() {
    return unrestrictedUnidentifiedAccess;
  }

  public UserCapabilities getCapabilities() {
    return capabilities;
  }

  public List<Badge> getBadges() {
    return badges.stream().collect(Collectors.toList());
  }

  public UUID getUuid() {
    return uuid;
  }

  @Override
	public Object clone() {
		try {
      return super.clone();
		} catch (CloneNotSupportedException cnse) {
      throw new RuntimeException("Cloning not supported", cnse);
		}
	}
}
