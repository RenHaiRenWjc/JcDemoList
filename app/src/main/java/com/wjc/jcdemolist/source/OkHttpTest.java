package com.wjc.jcdemolist.source;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

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
        Response response = call.execute(); // 执行同步请求  enqueue
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


// 异步请求：
//    Q: 如何决定将请求放入ready还是running？
//    A: 如果当前正在请求数不小于64放入ready；如果小于64，但是已经存在同一域名主机的请求5个放入ready！
//
//    Q: 从running移动ready的条件是什么？
//    A: 每个请求执行完成就会从running移除，同时进行第一步相同逻辑的判断，决定是否移动！  finished
//
//    Q: 分发器线程池的工作行为？
//    A：无等待，最大并发

}


