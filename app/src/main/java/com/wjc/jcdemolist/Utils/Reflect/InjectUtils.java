package com.wjc.jcdemolist.Utils.Reflect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;

import com.wjc.jcdemolist.Utils.LogUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * ClassName: com.wjc.jcdemolist.demo.changeSkin
 * Description:
 * JcChen on 2020.05.07.23:59
 */
public class InjectUtils {
  private static final String TAG = "InjectUtils";

  public static void injectEvent(Activity activity) {
    Class<? extends Activity> activityClass = activity.getClass();
    Method[] methods = activityClass.getDeclaredMethods();//拿到这个类所有方法
    for (Method method : methods) {
      LogUtils.i(TAG, "injectEvent:method -- "+method.getName());
      Annotation[] annotations = method.getAnnotations();//拿到方法上的所有注解
      for (Annotation annotation : annotations) {
        Class<? extends Annotation> annotationType = annotation.annotationType(); // 注解的注解
        if (annotationType.isAnnotationPresent(EventType.class)) {//是否是指定注解类型
          EventType eventType = annotationType.getAnnotation(EventType.class);
          Class listenerType = eventType.listenerType(); // View.OnClickListener.class
          String listenerSetter = eventType.listenerSetter(); // setOnClickListener
          try {
            Method valudeMethod = annotationType.getDeclaredMethod("id");// 不需要关心到底是OnClick 还是 OnLongClick,拿到注解的方法
            int[] viewIds = (int[]) valudeMethod.invoke(annotation);//注解的方法里面返回值

            method.setAccessible(true);
            ListenerInvocationHandler<Activity> hanlder = new ListenerInvocationHandler<>(method, activity);//执行如OnClick方法，然后给每个 view 设置监听
            Object listenerProxy = Proxy.newProxyInstance(listenerType.getClassLoader(), new Class[]{listenerType}, hanlder);
            for (int viewId : viewIds) {
              View view = activity.findViewById(viewId);
              Method setter = view.getClass().getMethod(listenerSetter, listenerType);//setOnClickListener
              setter.invoke(view,listenerProxy);
            }
          } catch (Exception e) {
            e.printStackTrace();
          }

        }
      }
    }

  }

  // 反射注解AutoWrited
  public static void injectAutowrited(Activity activity) {
    Class<? extends Activity> cls = activity.getClass();
    Intent intent = activity.getIntent();
    Bundle extras = intent.getExtras();
    if (extras == null) return;
    Field[] declaredFields = cls.getDeclaredFields();
    for (Field field : declaredFields) {
      if (field.isAnnotationPresent(AutoWrited.class)) {
        AutoWrited autoWrited = field.getAnnotation(AutoWrited.class);
        String key = TextUtils.isEmpty(autoWrited.key()) ? field.getName() : autoWrited.key();
        if (extras.containsKey(key)) {
          Object object = extras.get(key);

          Class<?> componetType = field.getType().getComponentType();
          if (field.getType().isArray() && Parcelable.class.isAssignableFrom(componetType)) {//如果数据是Parcelable类型，则需要特殊处理
            Object[] objects = (Object[]) object;
            //创建对应类型的数组并由objs拷贝
            Object[] objects1 = Arrays.copyOf(objects, objects.length, (Class<? extends java.lang.Object[]>) field.getType());
            object = objects1;
          }

          field.setAccessible(true); //反射时可以访问私有变量
          try {
            field.set(activity, object);
          } catch (IllegalAccessException e) {
            e.printStackTrace();
          }
        }

      }
    }
  }

  static class ListenerInvocationHandler<T> implements InvocationHandler {
    private Method method;
    private T target;

    public ListenerInvocationHandler(Method method, T target) {
      this.method = method;
      this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      return this.method.invoke(target, args);
    }
  }
}
