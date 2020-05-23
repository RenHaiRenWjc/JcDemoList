package com.wjc.jcdemolist.demo.changeSkin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wjc.jcdemolist.R;
import com.wjc.jcdemolist.Utils.LogTools;
import com.wjc.jcdemolist.Utils.Reflect.AutoWrited;
import com.wjc.jcdemolist.Utils.Reflect.InjectUtils;

public class SecondActivity extends AppCompatActivity {
  private static final String TAG = "SecondActivity";


  @AutoWrited(key = "index")
  String name;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_second2);
    InjectUtils.injectAutowrited(this);
    LogTools.i(TAG, "onCreate: name=" + name);
  }

}
