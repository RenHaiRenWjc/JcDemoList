package com.wjc.jcdemolist.demo.ServiceDemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wjc.jcdemolist.R;
import com.wjc.jcdemolist.base.BaseFragment;
import com.wjc.jcdemolist.base.BaseItemActivity;

public class ServiceDemoActivity extends BaseItemActivity {

    @Override
    public void addFragment() {
        fragments.add(new KeepAliveFragment());
    }
}
