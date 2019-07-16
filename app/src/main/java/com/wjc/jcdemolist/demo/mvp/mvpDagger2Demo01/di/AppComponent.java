package com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.di;

import android.app.Application;

import com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.network.WanAndroidApi;
import com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.network.di.ApiServiceModule;
import com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.network.di.HttpModule;

import dagger.Component;

/**
 * ClassName:com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.di
 * Description: 依赖需求方和依赖提供方之间的桥梁
 * JcChen on 2019/7/13 16:34
 */
@Component(modules = {AppModule.class, ApiServiceModule.class, HttpModule.class})
public interface AppComponent {
    Application application();

    WanAndroidApi getWanAndroidApi();
}
