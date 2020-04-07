package com.example.javademo.annotationTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ClassName: com.example.javademo.annotationTest
 * Description:
 * JcChen on 2020.04.07.15:08
 */
public class AnnotionProcessor {


  public <T> T  create(Class service) {
    Method[] methods = AnnotationTest.class.getDeclaredMethods();
    for (Method m : methods) {
      m.getAnnotation(service);

    }
    return null;
  }

  public <T> T create1(final Class<T> service) {//返回动态代理对象
    return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service}, new MyInvocationHandler());
  }

  class MyInvocationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
      System.out.println("method.getDeclaringClass(）=" + method.getDeclaringClass());
      if (method.getDeclaringClass() == Object.class) {
        System.out.println((args == null) ? "args==null," : args[0]);
        return method.invoke(this, args);
      }
      return null;
    }
  }

}
