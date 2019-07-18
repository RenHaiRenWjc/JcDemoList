package com.wjc.mvpdraggerdemo02.login.di;

import com.wjc.mvpdraggerdemo02.login.LoginActivity02;

import dagger.Module;
import dagger.Provides;

/**
 * ClassName:com.wjc.mvpdraggerdemo02.login.di
 * Description:
 * JcChen on 2019/7/18 8:16
 */
@Module
public class LoginActivityModule {
    @Provides
    static String provideName(){
        return LoginActivity02.class.getName();
    }
}
