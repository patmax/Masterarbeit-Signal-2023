/*
 * Copyright 2013-2020 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */
package org.whispersystems.textsecuregcm.push;

import com.eatthepath.pushy.apns.ApnsClient;
import com.eatthepath.pushy.apns.ApnsClientBuilder;
import com.eatthepath.pushy.apns.DeliveryPriority;
import com.eatthepath.pushy.apns.PushType;
import com.eatthepath.pushy.apns.auth.ApnsSigningKey;
import com.eatthepath.pushy.apns.util.SimpleApnsPayloadBuilder;
import com.eatthepath.pushy.apns.util.SimpleApnsPushNotification;
import com.google.common.annotations.VisibleForTesting;
import io.dropwizard.lifecycle.Managed;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Timer;
import org.whispersystems.textsecuregcm.configuration.ApnConfiguration;

import static org.whispersystems.textsecuregcm.metrics.MetricsUtil.name;

public class APNSender implements Managed, PushNotificationSender {

  private final ExecutorService executor;

  @VisibleForTesting
  static final String APN_VOIP_NOTIFICATION_PAYLOAD = new SimpleApnsPayloadBuilder()
      .setSound("default")
      .setLocalizedAlertMessage("APN_Message")
      .build();

  @VisibleForTesting
  static final String APN_NSE_NOTIFICATION_PAYLOAD = new SimpleApnsPayloadBuilder()
      .setMutableContent(true)
      .setLocalizedAlertMessage("APN_Message")
      .build();

  @VisibleForTesting
  static final String APN_BACKGROUND_PAYLOAD = new SimpleApnsPayloadBuilder()
      .setContentAvailable(true)
      .build();

  @VisibleForTesting
  static final Instant MAX_EXPIRATION = Instant.ofEpochMilli(Integer.MAX_VALUE * 1000L);

  private static final String APNS_CA_FILENAME = "apns-certificates.pem";

  private static final Timer SEND_NOTIFICATION_TIMER = Metrics.timer(name(APNSender.class, "sendNotification"));

  public APNSender(ExecutorService executor, ApnConfiguration configuration)
      throws IOException, NoSuchAlgorithmException, InvalidKeyException
  {
    this.executor = executor;
  }

  @VisibleForTesting
  public APNSender(ExecutorService executor, ApnsClient apnsClient, String bundleId) {
    this.executor = executor;
  }

  @Override
  public CompletableFuture<SendPushNotificationResult> sendNotification(final PushNotification notification) {
    final CompletableFuture<SendPushNotificationResult> completableSendFuture = new CompletableFuture<>();
    completableSendFuture.complete(new SendPushNotificationResult(true, null, false));
    return completableSendFuture;
  }

  @Override
  public void start() {
  }

  @Override
  public void stop() {

  }
}
