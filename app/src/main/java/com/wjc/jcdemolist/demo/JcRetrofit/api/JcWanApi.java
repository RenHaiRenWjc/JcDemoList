package com.wjc.jcdemolist.demo.JcRetrofit.api;

import com.wjc.jcdemolist.demo.JcRetrofit.annotation.JcField;
import com.wjc.jcdemolist.demo.JcRetrofit.annotation.JcGET;
import com.wjc.jcdemolist.demo.JcRetrofit.annotation.JcPOST;
import com.wjc.jcdemolist.demo.JcRetrofit.annotation.JcQuery;
import com.wjc.jcdemolist.demo.JcRetrofit.bean.ArticleList;
import com.wjc.jcdemolist.demo.JcRetrofit.bean.ResponseData;

import okhttp3.Call;


/**
 * ClassName: com.wjc.jcdemolist.demo.JcRetrofit
 * Description:
 * JcChen on 2020.05.14.20:46
 */
public interface JcWanApi {

  @JcGET("/wxarticle/chapters/json")
  Call getChapterList();

  @JcGET("/article/list/0/json")
  Call getArticleList(@JcQuery("author") String author);

  @JcPOST("/user/login")
  Call login(@JcField("username") String name, @JcField("password") String psw);


}
