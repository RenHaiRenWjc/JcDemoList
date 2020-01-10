package com.wjc.jcdemolist.itemActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.wjc.jcdemolist.MainActivity;
import com.wjc.jcdemolist.R;
import com.wjc.jcdemolist.Utils.SharedPreferencesUtil;

public class LauncherActivity extends AppCompatActivity  {
  private static final String TAG = "LauncherActivity";
  private TextView tvFirst;
  //private MyHandler myHandler = new MyHandler(this);
  private int count = 1;

  private Handler myHandler = new Handler(){
    @Override
    public void handleMessage(Message msg) {
      super.handleMessage(msg);
      switch (msg.what) {
        case 0:
          if (count>9){
            sendEmptyMessage(1);
          }else {
            tvFirst.setText("" + count);
            myHandler.sendEmptyMessageDelayed(0,1000);
            count++;
          }
          break;
        case 1:
          removeMessages(0);
          Intent intent = new Intent();
          intent.setClass(LauncherActivity.this, MainActivity.class);
          startActivity(intent);
          SharedPreferencesUtil.saveBoolean(SharedPreferencesUtil.SECOND_INTO,true);
          break;
      }
    }
  };




  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_launcher);
    tvFirst = findViewById(R.id.tv_first);
    boolean isSecond = SharedPreferencesUtil.getBoolean(SharedPreferencesUtil.SECOND_INTO);
    if (!isSecond) {
      myHandler.sendEmptyMessage(0);
    } else {
      myHandler.sendEmptyMessage(1);
    }
    tvFirst.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        myHandler.sendEmptyMessage(1);
      }
    });
  }

/*
  static class MyHandler extends Handler {
    private final WeakReference<LauncherActivity> wLauncherActivity;

    private MyHandler(LauncherActivity activity) {
      wLauncherActivity = new WeakReference<>(activity);
    }

    @Override
    public void handleMessage(Message msg) {
      super.handleMessage(msg);
      switch (msg.what) {
        case 0:
          Log.i(TAG, "handleMessage: count="+count);
          if (count>3){
            SharedPreferencesUtil.saveBoolean(SharedPreferencesUtil.SECOND_INTO,true);
            myHandler.sendEmptyMessage(1);
          }else {
            tvFirst.setText("" + count);
            myHandler.sendEmptyMessageDelayed(0,1000);
            count++;
          }
          break;
        case 1:
          myHandler.removeCallbacks(null);
          Intent intent = new Intent();
          intent.setClass(wLauncherActivity.get(), MainActivity.class);
          startActivity(intent);
          break;
      }
    }
  }*/


}
