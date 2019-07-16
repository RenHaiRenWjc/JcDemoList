package com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.network;


import com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.network.bean.BaseResponse;

import io.reactivex.Maybe;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * ClassName:com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.network
 * Description:
 * JcChen on 2019/7/13 16:54
 */
public interface WanAndroidApi {
    @POST("user/register")
    @FormUrlEncoded
    Maybe<BaseResponse> register(@Field("username") String username, @Field("password") String password,
                                 @Field("repassword") String repassword);

    @POST("user/login")
    @FormUrlEncoded
    Maybe<BaseResponse> login(@Field("username") String username, @Field("password") String password);
}
