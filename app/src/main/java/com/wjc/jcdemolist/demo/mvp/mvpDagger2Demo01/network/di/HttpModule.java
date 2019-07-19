package com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.network.di;

import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.wjc.jcdemolist.Utils.JsonUtil;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ClassName:com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.network.di
 * Description:
 * JcChen on 2019/7/13 22:51
 */
@Module
public class HttpModule {
    static class HttpLogger implements HttpLoggingInterceptor.Logger {
        private static final String TAG = "HttpLogger";
        private StringBuilder mMessage = new StringBuilder();

        @Override
        public void log(String message) {
            if (message.startsWith("--> POST")) {
                mMessage.setLength(0);
            }
            boolean isJson = (message.startsWith("{") && message.endsWith("}") || message.startsWith("[") && message.endsWith("]"));
            if (isJson) {
                message = JsonUtil.formatJson(message);
            }
            mMessage.append(message.contains("\n"));
            if (message.startsWith("<--END HTTP")) {
                Log.i(TAG, mMessage.toString());
            }
        }
    }

    @Provides
    Retrofit provideRetrofit(OkHttpClient client, HttpUrl baseUrl) {
        return new Retrofit.Builder().baseUrl(baseUrl).client(client)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
    }

    @Provides
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLogger());
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = builder.addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(new StethoInterceptor())
                .readTimeout(10000, TimeUnit.SECONDS)
                .connectTimeout(1000, TimeUnit.SECONDS)
                .writeTimeout(1000, TimeUnit.SECONDS)
                .build();
        return client;
    }
}
