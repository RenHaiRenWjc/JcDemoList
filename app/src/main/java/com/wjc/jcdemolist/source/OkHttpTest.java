package com.wjc.jcdemolist.source;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * ClassName:com.wjc.jcdemolist.source
 * Description:
 * JcChen on 2019/10/16 8:49
 */
public class OkHttpTest {

    OkHttpClient client = new OkHttpClient();

    void get(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        Response response = call.execute(); // 执行同步请求
        ResponseBody body = response.body();
        String b = body.toString();
    }

    void post(String url) throws IOException {
        RequestBody requestBody = new FormBody.Builder().add("city", "GZ").add("key", "ddfa").build();
        Request request = new Request.Builder().url(url).post(requestBody).build();
        Call call = client.newCall(request);
        Response response = call.execute();
        ResponseBody responseBody = response.body();
        String b = requestBody.toString();


    }
}


