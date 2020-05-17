package com.wjc.jcdemolist.demo.JcRetrofit;

/**
 * ClassName: com.wjc.jcdemolist.demo.JcRetrofit
 * Description:
 * JcChen on 2020.05.14.23:29
 */
public abstract class ParameterHandler {
  abstract void apply(ServiceMethod serviceMethod, String value);

  static class QueryParameterHandler extends ParameterHandler {
    String key;

    public QueryParameterHandler(String key) {
      this.key = key;
    }

    @Override
    void apply(ServiceMethod serviceMethod, String value) {
      serviceMethod.addQueryParameter(key, value);
    }
  }

  static class FiledParameterHandler extends ParameterHandler {
    String key;

    public FiledParameterHandler(String key) {
      this.key = key;
    }

    @Override
    void apply(ServiceMethod serviceMethod, String value) {
      serviceMethod.addFieldParameter(key, value);
    }
  }
}
