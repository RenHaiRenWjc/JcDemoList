package com.wjc.jcdemolist;

import android.os.Bundle;

import com.wjc.jcdemolist.base.BaseActivity;
import com.wjc.jcdemolist.bean.ActivityTypeBean;
import com.wjc.jcdemolist.demo.changeSkin.ChangeSkinActivity;
import com.wjc.jcdemolist.demo.ServiceDemo.ServiceDemoActivity;
import com.wjc.jcdemolist.itemActivity.XfermodeActivity;
import com.wjc.jcdemolist.demo.dispatch.dispatchDemo.Main5Activity;
import com.wjc.jcdemolist.demo.dispatch.dispatchDemo02.Main6Activity;
import com.wjc.jcdemolist.demo.jsonDemo.JsonActivity;
import com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.login.LoginActivity01;
import com.wjc.jcdemolist.demo.mvp.mvpDemo01.ui.login.LoginActivity;
import com.wjc.jcdemolist.demo.rxbinding_test.RxbindingActivity;
import com.wjc.jcdemolist.demo.viewpagerDemo.Main4Activity;
import com.wjc.jcdemolist.itemActivity.AnimatorTest;
import com.wjc.jcdemolist.itemActivity.Custom2Activity;
import com.wjc.jcdemolist.itemActivity.CustomActivity;
import com.wjc.jcdemolist.demo.PaintAndCanvas.PaintAndCanvasActivity;
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
        list.add(new ActivityTypeBean("跑马灯、蜘蛛网格图", MarqueeActivity.class));
        list.add(new ActivityTypeBean("Rxbinding 防抖", RxbindingActivity.class));
        list.add(new ActivityTypeBean("mvpdemo01", LoginActivity.class));
        list.add(new ActivityTypeBean("mvpdemo02", com.wjc.jcdemolist.demo.mvp.mvpDemo02.login.LoginActivity.class));
        list.add(new ActivityTypeBean("drggerdemo", LoginActivity01.class));
        list.add(new ActivityTypeBean("viewpager+tablayout", Main4Activity.class));
        list.add(new ActivityTypeBean("序列化json", JsonActivity.class));
        list.add(new ActivityTypeBean("customActivity", CustomActivity.class));
        list.add(new ActivityTypeBean("flowlayout", Custom2Activity.class));
        list.add(new ActivityTypeBean("事件冲突 demo", Main5Activity.class));
        list.add(new ActivityTypeBean("事件冲突 demo02", Main6Activity.class));
        list.add(new ActivityTypeBean("AnimatorTest", AnimatorTest.class));
        list.add(new ActivityTypeBean("AnimatorTest", XfermodeActivity.class));
        list.add(new ActivityTypeBean("绘制paint和canvas", PaintAndCanvasActivity.class));
        list.add(new ActivityTypeBean(" 换肤", ChangeSkinActivity.class));
        list.add(new ActivityTypeBean(" 保活", ServiceDemoActivity.class));
        return list;
    }
}
