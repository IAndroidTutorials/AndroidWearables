package com.prateekj.android.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.prateekj.android.R;
import com.prateekj.android.utils.SharedPrefsUtils;

public class SettingActivity extends AppCompatActivity {

  public static final String SERVER_IP = "SERVER_IP";
  public static final String SYSTEM_SETTING_PREFS = "SYSTEM_SETTING_PREFS";

  private EditText serverIpContainer;
  private View saveIpButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.setting_activity);
    initializeViews();
  }

  private void initializeViews() {
    serverIpContainer = (EditText) findViewById(R.id.server_ip_container);
    saveIpButton = findViewById(R.id.save_ip_button);
    saveIpButton.setOnClickListener(saveIp());
  }

  @NonNull
  private View.OnClickListener saveIp() {
    return new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        SharedPrefsUtils sharedPrefs = new SharedPrefsUtils(SettingActivity.this);
        sharedPrefs.saveIp(serverIpContainer.getText().toString());
      }
    };
  }
}
