package com.prateekj.android.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.prateekj.android.R;

public class NotificationClickActivity extends AppCompatActivity{

  public static final String SOURCE_TEXT = "source_text";

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_notification_click);
    TextView textView = (TextView) findViewById(R.id.text);
    textView.setText(getIntent().getStringExtra(SOURCE_TEXT));
  }
}
