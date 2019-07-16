package com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.network.di;

import com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.network.WanAndroidApi;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import retrofit2.Retrofit;

/**
 * ClassName:com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.network.di
 * Description:
 * JcChen on 2019/7/15 8:26
 */
@Module
public class ApiServiceModule {
    @Provides
    HttpUrl provideBaseUrl() {
        return HttpUrl.parse("https://www.wanandroid.com/");
    }

    @Provides
    WanAndroidApi provideUserService(Retrofit retrofit) {
        return retrofit.create(WanAndroidApi.class);
    }
}
