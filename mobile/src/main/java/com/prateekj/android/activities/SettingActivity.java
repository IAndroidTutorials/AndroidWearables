package com.prateekj.android.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.prateekj.android.R;
import com.prateekj.android.utils.SharedPrefsUtils;

import static com.prateekj.android.utils.SharedPrefsUtils.SERVER_IP;

public class SettingActivity extends AppCompatActivity {

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
    setIpIfAlreadySaved();
    saveIpButton = findViewById(R.id.save_ip_button);
    saveIpButton.setOnClickListener(saveIp());
  }

  private void setIpIfAlreadySaved() {
    SharedPrefsUtils sharedPrefsUtils = new SharedPrefsUtils(this);
    String url = sharedPrefsUtils.getString(SERVER_IP);
    if (url != null)
      serverIpContainer.setText(url);
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
