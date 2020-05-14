package com.wjc.jcdemolist.demo.JcRetrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.wjc.jcdemolist.R;
import com.wjc.jcdemolist.Utils.LogUtils;
import com.wjc.jcdemolist.demo.JcRetrofit.bean.ResponseData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main8Activity extends AppCompatActivity {
  private static final String TAG = "Main8Activity";
  private WanApi wanApi;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main8);
    initRetrofit();
  }

  public void initRetrofit() {
    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.wanandroid.com").addConverterFactory(GsonConverterFactory.create())
      .build();
    wanApi = retrofit.create(WanApi.class);


    //get
    Call<ResponseData<Character>> callGet = wanApi.getChapterList();
    callGet.enqueue(new Callback<ResponseData<Character>>() {
      @Override
      public void onResponse(Call<ResponseData<Character>> call, Response<ResponseData<Character>> response) {
        if (response.isSuccessful()) {
          ResponseData body = response.body();
          LogUtils.i(TAG, "onResponse: body=" + body.getErrorCode());
        }
      }

      @Override
      public void onFailure(Call<ResponseData<Character>> call, Throwable t) {

      }
    });
    // post
    Call<ResponseData> callPost = wanApi.login("wjc", "1111");
    callPost.enqueue(new Callback<ResponseData>() {
      @Override
      public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
        Log.i(TAG, "onResponse: error code=" + response.body().getErrorCode());
      }

      @Override
      public void onFailure(Call<ResponseData> call, Throwable t) {

      }
    });
  }

}
