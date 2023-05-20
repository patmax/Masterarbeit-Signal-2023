/*
 * Copyright 2013-2020 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */
package org.whispersystems.websocket.logging.layout;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.pattern.PatternLayoutBase;
import org.whispersystems.websocket.logging.WebsocketEvent;
import org.whispersystems.websocket.logging.layout.converters.ContentLengthConverter;
import org.whispersystems.websocket.logging.layout.converters.DateConverter;
import org.whispersystems.websocket.logging.layout.converters.EnsureLineSeparation;
import org.whispersystems.websocket.logging.layout.converters.NAConverter;
import org.whispersystems.websocket.logging.layout.converters.RemoteHostConverter;
import org.whispersystems.websocket.logging.layout.converters.RequestHeaderConverter;
import org.whispersystems.websocket.logging.layout.converters.RequestUrlConverter;
import org.whispersystems.websocket.logging.layout.converters.StatusCodeConverter;

import java.util.HashMap;
import java.util.Map;

public class WebsocketEventLayout extends PatternLayoutBase<WebsocketEvent> {

  private static final Map<String, String> DEFAULT_CONVERTERS = new HashMap<>();

  public static final String CLF_PATTERN = "%h %l %u [%t] \"%r\" %s %b";
  public static final String CLF_PATTERN_NAME = "common";
  public static final String CLF_PATTERN_NAME_2 = "clf";
  public static final String COMBINED_PATTERN = "%h %l %u [%t] \"%r\" %s %b \"%i{Referer}\" \"%i{User-Agent}\"";
  public static final String COMBINED_PATTERN_NAME = "combined";
  public static final String HEADER_PREFIX = "#logback.access pattern: ";

  public WebsocketEventLayout(Context context) {
    DEFAULT_CONVERTERS.put("h", RemoteHostConverter.class.getName());
    DEFAULT_CONVERTERS.put("l", NAConverter.class.getName());
    DEFAULT_CONVERTERS.put("u", NAConverter.class.getName());
    DEFAULT_CONVERTERS.put("t", DateConverter.class.getName());
    DEFAULT_CONVERTERS.put("r", RequestUrlConverter.class.getName());
    DEFAULT_CONVERTERS.put("s", StatusCodeConverter.class.getName());
    DEFAULT_CONVERTERS.put("b", ContentLengthConverter.class.getName());
    DEFAULT_CONVERTERS.put("i", RequestHeaderConverter.class.getName());

    setOutputPatternAsHeader(false);
    setPattern(COMBINED_PATTERN);
    setContext(context);

    this.postCompileProcessor = new EnsureLineSeparation();
  }

  @Override
  public Map<String, String> getDefaultConverterMap() {
    return DEFAULT_CONVERTERS;
  }

  @Override
  public String doLayout(WebsocketEvent event) {
    if (!isStarted()) {
      return null;
    }

    return writeLoopOnConverters(event);
  }

  @Override
  public void start() {
    if (getPattern().equalsIgnoreCase(CLF_PATTERN_NAME) || getPattern().equalsIgnoreCase(CLF_PATTERN_NAME_2)) {
      setPattern(CLF_PATTERN);
    } else if (getPattern().equalsIgnoreCase(COMBINED_PATTERN_NAME)) {
      setPattern(COMBINED_PATTERN);
    }

    super.start();
  }

  @Override
  protected String getPresentationHeaderPrefix() {
    return HEADER_PREFIX;
  }

}
