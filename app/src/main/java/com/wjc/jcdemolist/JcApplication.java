package com.wjc.jcdemolist;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.di.AppComponent;
import com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.di.AppModule;
import com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.di.DaggerAppComponent;

/**
 * ClassName:com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01
 * Description:
 * JcChen on 2019/7/13 15:58
 */
public class JcApplication extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        //使用Chrome来调试Android Application的工具
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
