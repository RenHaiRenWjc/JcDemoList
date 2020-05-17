package com.wjc.jcdemolist.demo.JcRetrofit;

import androidx.annotation.Nullable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

/**
 * ClassName: com.wjc.jcdemolist.demo.JcRetrofit
 * Description:
 * JcChen on 2020.05.14.22:24
 */
public class JcRetrofit {
  final Map<Method, ServiceMethod> serviceMethodCache = new ConcurrentHashMap<>();
  final Call.Factory callFactory;
  final HttpUrl baseUrl;

  public JcRetrofit(Call.Factory callFactory, HttpUrl baseUrl) {
    this.callFactory = callFactory;
    this.baseUrl = baseUrl;
  }

  public <T> T create(final Class<T> service) {//返回动态代理对象
    return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service}, (proxy, method, args) -> {
      ServiceMethod serviceMethod = loadServeMethod(method);
      return serviceMethod.invoke(args);
    });

  }

  private ServiceMethod loadServeMethod(Method method) {
    ServiceMethod result = serviceMethodCache.get(method);
    if (result != null) return result;
    synchronized (serviceMethodCache) {
      result = serviceMethodCache.get(method);
      if (result == null) {
        result = new ServiceMethod.Builder(this, method).build();
        serviceMethodCache.put(method, result);
      }
    }
    return result;
  }

  public static final class Builder {
    private HttpUrl baseUrl;
    private okhttp3.Call.Factory callFactory;

    public Builder callFactory(okhttp3.Call.Factory factory) {
      this.callFactory = factory;
      return this;
    }

    public Builder baseUrl(String baseUrl) {
      this.baseUrl = HttpUrl.parse(baseUrl);
      return this;
    }

    public JcRetrofit build() {
      if (baseUrl == null) throw new IllegalArgumentException("baseUrl required");
      okhttp3.Call.Factory callFactory = this.callFactory;
      if (callFactory == null) {
        callFactory = new OkHttpClient();
      }
      return new JcRetrofit(callFactory, baseUrl);
    }


  }

}
