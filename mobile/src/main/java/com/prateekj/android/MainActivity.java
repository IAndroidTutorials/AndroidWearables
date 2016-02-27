package com.prateekj.android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import static com.prateekj.android.SettingActivity.SYSTEM_SETTING_PREFS;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu, menu);
    MenuItem settingMenu = menu.findItem(R.id.settings);
    settingMenu.setOnMenuItemClickListener(settingsMenuListener());
    return super.onCreateOptionsMenu(menu);
  }

  public void fetchPosts(View view) {
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder()
        .url(getUrlForPosts())
        .build();
    client.newCall(request).enqueue(new Callback() {
      @Override
      public void onFailure(Request request, IOException e) {
        e.printStackTrace();
      }

      @Override
      public void onResponse(Response response) throws IOException {
        if (!response.isSuccessful()) return;
        setResponseText(response.body().string());
      }
    });
  }

  private String getUrlForPosts() {
    SharedPreferences sharedPreferences = getSharedPreferences(SYSTEM_SETTING_PREFS, Context.MODE_PRIVATE);
    String url = sharedPreferences.getString(SettingActivity.SERVER_IP, null);
    return String.format("http://%s:3000/posts/", url);
  }

  private void setResponseText(final String responseText) {
    final MainActivity activity = this;
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        TextView view = (TextView) activity.findViewById(R.id.response_text_view);
        view.setText(responseText);
      }
    });
  }

  @NonNull
  private MenuItem.OnMenuItemClickListener settingsMenuListener() {
    return new MenuItem.OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem item) {
        startActivity(new Intent(MainActivity.this, SettingActivity.class));
        return true;
      }
    };
  }

}
