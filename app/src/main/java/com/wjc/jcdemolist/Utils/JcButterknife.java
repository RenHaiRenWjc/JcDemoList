package com.wjc.jcdemolist.Utils;

import android.app.Activity;

import com.wjc.jcdemolist.itemActivity.IBinder;

/**
 * ClassName: com.wjc.jcdemolist.Utils
 * Description:
 * JcChen on 2020.05.24.10:39
 */
public class JcButterknife {
  public static void bind(Activity activity) {
    String name = activity.getClass().getName() + "_ViewBinding";
    try {
      Class<?> mClass = Class.forName(name);
      IBinder iBinder = (IBinder) mClass.newInstance();
      iBinder.bind(activity);
    } catch (Exception e) {
    }
  }
}
