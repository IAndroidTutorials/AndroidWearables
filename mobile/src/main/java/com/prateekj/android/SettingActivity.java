package com.prateekj.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
        SharedPreferences sharedPreferences = SettingActivity.this.getSharedPreferences(SYSTEM_SETTING_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SERVER_IP, serverIpContainer.getText().toString());
        editor.commit();
      }
    };
  }
}
