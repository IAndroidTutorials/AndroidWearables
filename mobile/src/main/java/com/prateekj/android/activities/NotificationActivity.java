package com.prateekj.android.activities;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Action;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.WearableExtender;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.prateekj.android.R;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;
import static com.prateekj.android.activities.NotificationResultActivity.EXTRA_VOICE_REPLY_KEY;
import static com.prateekj.android.activities.NotificationResultActivity.SOURCE_TEXT;
import static com.prateekj.android.activities.NotificationResultActivity.VOICE_REPLY;

public class NotificationActivity extends AppCompatActivity {

  public static final int UNIQUE_ID = 1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_notification);
  }

  public void generateNotification(View view) {
    PendingIntent pendingIntent = getPendingIntentForText("Simple Notification");
    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
        .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
        .setContentTitle("My Notification")
        .setContentIntent(pendingIntent)
        .setAutoCancel(true);
    NotificationManagerCompat mNotificationManager = NotificationManagerCompat.from(getApplicationContext());
    mNotificationManager.notify(UNIQUE_ID, notificationBuilder.build());
  }

  public void wearableSpecificNotifications(View view) {
    PendingIntent pendingIntent = getPendingIntentForText("Wearable Specific Notification");
    Action mapAction = new Action(R.drawable.common_full_open_on_phone, "Wearable Only Action", pendingIntent);
    WearableExtender extender = new WearableExtender()
        .setBackground(BitmapFactory.decodeResource(getResources(), R.drawable.ic_media_pause))
        .setContentIcon(R.drawable.ic_media_pause)
        .addAction(mapAction);

    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
        .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
        .setContentTitle("Wearable Only Action")
        .extend(extender)
        .setAutoCancel(true);
    NotificationManagerCompat mNotificationManager = NotificationManagerCompat.from(getApplicationContext());
    mNotificationManager.notify(UNIQUE_ID, notificationBuilder.build());
  }

  public void bigStyleNotifications(View view) {
    PendingIntent pendingIntent = getPendingIntentForText("Big Style Notification");
    Action action = new Action(R.drawable.common_full_open_on_phone, "Big Style Notification Action", pendingIntent);

    BigTextStyle bigTextStyle = new BigTextStyle();
    bigTextStyle.bigText(getString(R.string.big_style_description));

    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
        .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
        .setContentTitle("Big Style Title")
        .addAction(action)
        .setStyle(bigTextStyle)
        .setAutoCancel(true);
    NotificationManagerCompat mNotificationManager = NotificationManagerCompat.from(getApplicationContext());
    mNotificationManager.notify(UNIQUE_ID, notificationBuilder.build());
  }

  public void voiceReplyNotification(View view) {
    RemoteInput remoteInput = new RemoteInput.Builder(EXTRA_VOICE_REPLY_KEY)
        .setLabel("Please describe your query")
        .build();

    PendingIntent pendingIntent = getPendingIntentForVoiceReply();

    Action action = new Action.Builder(R.drawable.common_full_open_on_phone, "Voice Reply Action", pendingIntent)
        .addRemoteInput(remoteInput)
        .build();

    WearableExtender extender = new WearableExtender()
        .setBackground(BitmapFactory.decodeResource(getResources(), R.drawable.ic_media_pause))
        .setContentIcon(R.drawable.ic_media_pause)
        .addAction(action);

    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
        .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
        .setContentTitle("Voice Reply Action")
        .extend(extender)
        .setAutoCancel(true);
    NotificationManagerCompat mNotificationManager = NotificationManagerCompat.from(getApplicationContext());
    mNotificationManager.notify(UNIQUE_ID, notificationBuilder.build());
  }

  @NonNull
  private PendingIntent getPendingIntentForText(String text) {
    Intent intent = new Intent(this, NotificationResultActivity.class);
    intent.putExtra(SOURCE_TEXT, text);
    return PendingIntent.getActivity(this, 0, intent, FLAG_UPDATE_CURRENT);
  }

  @NonNull
  private PendingIntent getPendingIntentForVoiceReply() {
    Intent intent = new Intent(this, NotificationResultActivity.class);
    intent.putExtra(VOICE_REPLY, true);
    return PendingIntent.getActivity(this, 0, intent, FLAG_UPDATE_CURRENT);
  }
}
