/*
 * Copyright 2021-2022 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.whispersystems.textsecuregcm.captcha;

import static org.whispersystems.textsecuregcm.metrics.MetricsUtil.name;

import java.io.IOException;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecaptchaClient implements CaptchaClient {

  private static final Logger log = LoggerFactory.getLogger(RecaptchaClient.class);

  private static final String V2_PREFIX = "signal-recaptcha-v2";
  private static final String INVALID_REASON_COUNTER_NAME = name(RecaptchaClient.class, "invalidReason");
  private static final String ASSESSMENT_REASON_COUNTER_NAME = name(RecaptchaClient.class, "assessmentReason");

  public RecaptchaClient(@Nonnull final String projectPath) {

  }

  @Override
  public String scheme() {
    return V2_PREFIX;
  }

  @Override
  public org.whispersystems.textsecuregcm.captcha.AssessmentResult verify(final String sitekey,
      final @Nullable String expectedAction,
      final String token, final String ip) throws IOException {
      return new AssessmentResult(
          true,
          AssessmentResult.scoreString(100));
  }
}
