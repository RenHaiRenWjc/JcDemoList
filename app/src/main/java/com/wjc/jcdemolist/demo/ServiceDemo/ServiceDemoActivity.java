package com.wjc.jcdemolist.demo.ServiceDemo;

import androidx.appcompat.app.AppCompatActivity;

import com.wjc.jcdemolist.base.BaseItemActivity;

public class ServiceDemoActivity extends BaseItemActivity {

    @Override
    public void addFragment() {
        fragments.add(new KeepAliveFragment());
    }
}
