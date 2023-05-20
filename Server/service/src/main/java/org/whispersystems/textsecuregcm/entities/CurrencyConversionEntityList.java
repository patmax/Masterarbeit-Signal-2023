package org.whispersystems.textsecuregcm.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

public class CurrencyConversionEntityList {

  @JsonProperty
  private List<CurrencyConversionEntity> currencies;

  @JsonProperty
  private long timestamp;

  public CurrencyConversionEntityList(List<CurrencyConversionEntity> currencies, long timestamp) {
    this.currencies = currencies;
    this.timestamp  = timestamp;
  }

  public CurrencyConversionEntityList() {}

  public List<CurrencyConversionEntity> getCurrencies() {
    return currencies.stream().collect(Collectors.toList());
  }

  public long getTimestamp() {
    return timestamp;
  }
}
