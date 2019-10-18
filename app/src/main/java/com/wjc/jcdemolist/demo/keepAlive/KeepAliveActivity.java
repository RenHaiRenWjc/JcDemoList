package com.wjc.jcdemolist.demo.keepAlive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wjc.jcdemolist.R;

public class KeepAliveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keep_alive);
        startService(new Intent(this, ForegroundService.class));
    }
}
