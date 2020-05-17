package com.wjc.jcdemolist.demo.JcRetrofit.api;

import com.wjc.jcdemolist.demo.JcRetrofit.bean.ArticleList;
import com.wjc.jcdemolist.demo.JcRetrofit.bean.ResponseData;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * ClassName: com.wjc.jcdemolist.demo.JcRetrofit
 * Description:
 * JcChen on 2020.05.14.20:46
 */
public interface WanApi {

  @GET("/wxarticle/chapters/json")
  Call<ResponseData<Character>> getChapterList();

  @GET("/article/list/0/json")
  Call<ArticleList> getArticleList(@Query("author") String author);

  @POST("/user/login")
  @FormUrlEncoded
  Call<ResponseData> login(@Field("username") String name, @Field("password") String psw);


}
