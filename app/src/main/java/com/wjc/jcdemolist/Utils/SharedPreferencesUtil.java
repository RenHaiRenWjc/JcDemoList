package com.wjc.jcdemolist.Utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class SharedPreferencesUtil {
  private static final String TAG = "SharedPreferencesUtil";
  private static final String SHAREDPREFERENCE_NAME = "jc_sp";
  public static final String SECOND_INTO="second";

  private static SharedPreferences sSharedPreferences = null;


  public static SharedPreferences initSharedPreferences(Context context) {
    if (sSharedPreferences != null) {
      return sSharedPreferences;
    } else {
      sSharedPreferences = context.getSharedPreferences("jc_sp", Application.MODE_PRIVATE);
      return sSharedPreferences;
    }
  }

  private static SharedPreferences.Editor getEditor() {
    return sSharedPreferences.edit();
  }

  public static boolean getBoolean(String key) {
    return sSharedPreferences.getBoolean(key, false);
  }

  public static boolean saveBoolean(String key, boolean value) {
    if (!TextUtils.isEmpty(key)) {
      SharedPreferences.Editor editor = getEditor();
      editor.putBoolean(key, value);
      editor.commit();
      return true;
    }
    return false;
  }


}
