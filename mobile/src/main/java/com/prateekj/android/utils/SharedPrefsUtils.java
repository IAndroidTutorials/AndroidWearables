package com.prateekj.android.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefsUtils {


  public static final String SERVER_IP = "SERVER_IP";
  public static final String SYSTEM_SETTING_PREFS = "SYSTEM_SETTING_PREFS";


  private final SharedPreferences sharedPreferences;
  private final SharedPreferences.Editor editor;

  public SharedPrefsUtils(Context context) {
    sharedPreferences = context.getSharedPreferences(SYSTEM_SETTING_PREFS, Context.MODE_PRIVATE);
    editor = sharedPreferences.edit();
  }

  public void saveIp(String ipAddress) {
    editor.putString(SERVER_IP, ipAddress);
    editor.commit();
  }

  public String getString(String key) {
    return this.sharedPreferences.getString(key, null);
  }
}
