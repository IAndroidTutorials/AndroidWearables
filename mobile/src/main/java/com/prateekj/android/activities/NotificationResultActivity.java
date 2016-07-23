package com.prateekj.android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.prateekj.android.R;

@SuppressWarnings("ALL")
public class NotificationResultActivity extends AppCompatActivity{

  public static final String SOURCE_TEXT = "source_text";
  public static final String VOICE_REPLY = "voice_reply";
  public static final String EXTRA_VOICE_REPLY_KEY = "extra_voice_reply";
  private TextView textView;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_notification_click);
    textView = (TextView) findViewById(R.id.text);
    Intent intent = getIntent();
    if(isVoiceReply(intent)) {
      showVoiceInput(intent);
    }else {
      textView.setText(intent.getStringExtra(SOURCE_TEXT));
    }
  }

  private void showVoiceInput(Intent intent) {
    Bundle bundle = RemoteInput.getResultsFromIntent(intent);
    if(bundle != null) {
      String voiceText = bundle.getCharSequence(EXTRA_VOICE_REPLY_KEY).toString();
      textView.setText(voiceText);
    }
  }

  private boolean isVoiceReply(Intent intent) {
    return intent.getBooleanExtra(VOICE_REPLY, false);
  }
}
