package com.prateekj.android.activities;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.prateekj.android.R;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;
import static android.net.Uri.parse;

public class NotificationActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_notification);
  }

  public void generateNotification(View view) {
    final int UNIQUE_ID = 1;
    Intent intent = new Intent(Intent.ACTION_VIEW, parse(getString(R.string.twitter_uri)));
    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
        .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
        .setContentTitle("My Notification")
        .setContentIntent(PendingIntent.getActivity(this, 0, intent, FLAG_UPDATE_CURRENT))
        .setAutoCancel(true);
    NotificationManagerCompat mNotificationManager = NotificationManagerCompat.from(getApplicationContext());
    mNotificationManager.notify(UNIQUE_ID, notificationBuilder.build());
  }
}
