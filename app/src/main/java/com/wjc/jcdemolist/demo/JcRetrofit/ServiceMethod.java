package com.wjc.jcdemolist.demo.JcRetrofit;

import com.wjc.jcdemolist.demo.JcRetrofit.annotation.JcField;
import com.wjc.jcdemolist.demo.JcRetrofit.annotation.JcGET;
import com.wjc.jcdemolist.demo.JcRetrofit.annotation.JcPOST;
import com.wjc.jcdemolist.demo.JcRetrofit.annotation.JcQuery;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * ClassName: com.wjc.jcdemolist.demo.JcRetrofit
 * Description:记录请求类型、参数、完整地址
 * JcChen on 2020.05.14.23:23
 */
public class ServiceMethod {

  private final Call.Factory callFactory;
  private final String relativeUrl;
  private final boolean hasBody;
  private final ParameterHandler[] parameterHandler;
  private FormBody.Builder formBuild;
  HttpUrl baseUrl;
  String httpMethod;
  HttpUrl.Builder urlBuilder;

  public ServiceMethod(Builder builder) {
    baseUrl = builder.jcRetrofit.baseUrl;
    callFactory = builder.jcRetrofit.callFactory;
    httpMethod = builder.httpMethod;
    relativeUrl = builder.relativeUrl;
    hasBody = builder.hasBody;
    parameterHandler = builder.parameterHandler;
    if (hasBody) {
      formBuild = new FormBody.Builder();
    }
  }

  public Object invoke(Object[] args) {
    for (int i = 0; i < parameterHandler.length; i++) {
      ParameterHandler handler = parameterHandler[i];
      handler.apply(this, args[i].toString());
    }
    HttpUrl url;
    if (urlBuilder == null) {
      urlBuilder = baseUrl.newBuilder(relativeUrl);//如果是get的话，直接加参数即可
    }
    url = urlBuilder.build();

    FormBody formBody = null;
    if (formBuild != null) { // post 构建request body
      formBody = formBuild.build();
    }

    Request request = new Request.Builder().url(url).method(httpMethod, formBody).build();
    return callFactory.newCall(request);
  }

  public void addQueryParameter(String key, String value) { //get
    if (urlBuilder == null) {
      urlBuilder = baseUrl.newBuilder(relativeUrl);
    }
    urlBuilder.addQueryParameter(key, value);
  }

  public void addFieldParameter(String key, String value) { //post
    formBuild.add(key, value);
  }

  public static class Builder {
    private final JcRetrofit jcRetrofit;
    private final Annotation[] methodAnnotations;
    private final Annotation[][] parameterAnnotations;
    ParameterHandler[] parameterHandler;
    private String httpMethod;
    private String relativeUrl;
    private boolean hasBody;

    public Builder(JcRetrofit jcRetrofit, Method method) {
      this.jcRetrofit = jcRetrofit;
      methodAnnotations = method.getAnnotations();//所有注解
      parameterAnnotations = method.getParameterAnnotations(); //方法参数的注解
    }


    public ServiceMethod build() {
      //方法上的注解，只处理POST与GET
      for (Annotation annotation : methodAnnotations) {
        if (annotation instanceof JcPOST) {
          this.httpMethod = "POST";
          this.relativeUrl = ((JcPOST) annotation).value();
          this.hasBody = true;
        } else if (annotation instanceof JcGET) {
          this.httpMethod = "GET";
          this.relativeUrl = ((JcGET) annotation).value();
          this.hasBody = false;
        }
      }

      // 解析方法参数注解
      int length = parameterAnnotations.length;
      parameterHandler = new ParameterHandler[length];
      for (int i = 0; i < length; i++) {
        Annotation[] annotations = parameterAnnotations[i];//一个参数上注解
        for (Annotation annotation : annotations) {
          // 可以加一个判断:如果httpMethod是get请求,现在又解析到Filed注解,可以提示使用者使用Query注解
          if (annotation instanceof JcField) {//post时的参数注解
            // Call login(@JcField("username") String name, @JcField("password") String psw);
            String value = ((JcField) annotation).value();
            parameterHandler[i] = new ParameterHandler.FiledParameterHandler(value);
          } else if (annotation instanceof JcQuery) {// get时的参数注解
            String value = ((JcQuery) annotation).value();
            parameterHandler[i] = new ParameterHandler.QueryParameterHandler(value);
          }
        }
      }
      return new ServiceMethod(this);

    }
  }


}

