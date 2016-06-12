package com.prateekj.android.activities;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Action;
import android.support.v4.app.NotificationCompat.WearableExtender;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.prateekj.android.R;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;
import static com.prateekj.android.activities.NotificationClickActivity.SOURCE_TEXT;

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
    WearableExtender extender = new WearableExtender().
        addAction(mapAction);

    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
        .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
        .setContentTitle("Wearable Only Action")
        .extend(extender)
        .setAutoCancel(true);
    NotificationManagerCompat mNotificationManager = NotificationManagerCompat.from(getApplicationContext());
    mNotificationManager.notify(UNIQUE_ID, notificationBuilder.build());
  }

  @NonNull
  private PendingIntent getPendingIntentForText(String text) {
    Intent intent = new Intent(this, NotificationClickActivity.class);
    intent.putExtra(SOURCE_TEXT, text);
    return PendingIntent.getActivity(this, 0, intent, FLAG_UPDATE_CURRENT);
  }
}
