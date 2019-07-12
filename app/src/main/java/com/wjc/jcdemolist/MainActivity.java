package com.wjc.jcdemolist;

import android.os.Bundle;

import com.wjc.jcdemolist.base.BaseActivity;
import com.wjc.jcdemolist.bean.ActivityTypeBean;
import com.wjc.jcdemolist.demo.mvp.mvpDemo01.ui.login.LoginActivity;
import com.wjc.jcdemolist.demo.rxbinding_test.RxbindingActivity;
import com.wjc.jcdemolist.itemActivity.MarqueeActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int setContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public List<ActivityTypeBean> getList() {
        List<ActivityTypeBean> list = new ArrayList<>();
        list.add(new ActivityTypeBean("跑马灯测试", MarqueeActivity.class));
        list.add(new ActivityTypeBean("Rxbinding 防抖", RxbindingActivity.class));
        list.add(new ActivityTypeBean("mvpdemo01", LoginActivity.class));
        return list;
    }
}
