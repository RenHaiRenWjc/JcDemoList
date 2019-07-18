package com.wjc.mvpdraggerdemo02.di.module;

import android.app.Application;


import com.wjc.mvpdraggerdemo02.JcApplication;

import dagger.Module;
import dagger.Provides;

/**
 * ClassName:com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.di
 * Description:生成依赖对象
 * JcChen on 2019/7/13 16:30
 */
@Module
public class AppModule {
    private JcApplication mApplication;

    public AppModule(JcApplication application) {
        this.mApplication = application;
    }

    @Provides  //预先提供好的对象当做依赖给标注了@Inject的变量赋值
    public Application provideApplication() {
        return mApplication;
    }
}
