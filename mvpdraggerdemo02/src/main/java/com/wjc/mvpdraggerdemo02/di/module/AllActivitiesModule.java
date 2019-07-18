package com.wjc.mvpdraggerdemo02.di.module;

import com.wjc.mvpdraggerdemo02.login.LoginActivity02;
import com.wjc.mvpdraggerdemo02.login.di.LoginActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * ClassName:com.wjc.mvpdraggerdemo02.login.di
 * Description:
 * JcChen on 2019/7/18 8:40
 */
@Module
public abstract class AllActivitiesModule {
    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity02 contributeLoginActivityInjector();

}
