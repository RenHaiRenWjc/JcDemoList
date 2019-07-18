package com.wjc.mvpdraggerdemo02.di;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

import com.wjc.mvpdraggerdemo02.JcApplication;
import com.wjc.mvpdraggerdemo02.di.module.AllActivitiesModule;
import com.wjc.mvpdraggerdemo02.di.module.AppModule;
import com.wjc.mvpdraggerdemo02.network.WanAndroidApi;
import com.wjc.mvpdraggerdemo02.network.di.ApiServiceModule;
import com.wjc.mvpdraggerdemo02.network.di.HttpModule;

/**
 * ClassName:com.wjc.mvpdraggerdemo02.di
 * Description:
 * JcChen on 2019/7/18 8:31
 */
@Component(modules = {AndroidInjectionModule.class
        , AndroidSupportInjectionModule.class
        , AllActivitiesModule.class
        , AppModule.class
        , HttpModule.class
        , ApiServiceModule.class})
public interface AppComponent {
    void inject(JcApplication application);

    WanAndroidApi getWanAndroidApi();
}
