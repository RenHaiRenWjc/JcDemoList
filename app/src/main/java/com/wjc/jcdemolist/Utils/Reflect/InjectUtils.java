package com.wjc.jcdemolist.Utils.Reflect;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.wjc.jcdemolist.Utils.LogTools;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import butterknife.OnClick;

/**
 * ClassName: com.wjc.jcdemolist.demo.changeSkin
 * Description:
 * JcChen on 2020.05.07.23:59
 */
public class InjectUtils {
  private static final String TAG = "InjectUtils";


  public static void injectView(Activity activity) {
    Class<? extends Activity> mClass = activity.getClass();
    Field[] fields = mClass.getDeclaredFields();
    for (Field field : fields) {
      InjectView injectView = field.getAnnotation(InjectView.class);
      if (injectView != null) {
        int valueId = injectView.value();
        try {
          Method method = mClass.getMethod("findViewById", int.class);//参数：方法名、参数类型
          Object view = method.invoke(activity, valueId);// activity 为对象，对象的方法
          field.setAccessible(true);
          field.set(activity, view);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static void injectEvent02(Activity activity) {
    Class<?> mClass = activity.getClass();
    Method[] methods = mClass.getDeclaredMethods();
    for (Method method : methods) {
      Annotation[] annotations = method.getDeclaredAnnotations();
      for (Annotation annotation : annotations) {
        Class<? extends Annotation> annotationTyep = annotation.annotationType();// 有没有 EventBase 注解
        if (annotationTyep.isAnnotationPresent(EventBase.class)) {
          EventBase eventBase = annotationTyep.getAnnotation(EventBase.class);
          // EventBase 里面的值
          String setListener = eventBase.setListener();
          Class<?> listener = eventBase.listenerType();
           String methodCallBack = eventBase.methodCallback();

          Object proxy = Proxy.newProxyInstance(listener.getClassLoader(), new Class[]{listener}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method1, Object[] args) throws Throwable {
              return method.invoke(activity, args); // 动态执行 Activity 中的 onClick 方法
            }
          });

          onClick02 onClick02 = method.getAnnotation(onClick02.class);
          int[] ids = onClick02.id();
          for (int id : ids) {
            try {
              View targetView = activity.findViewById(id);
//              Method callback = listener.getMethod(methodCallBack, View.class);// onClick(View view) 方法

              Method methodSetlistener = targetView.getClass().getMethod(setListener, listener);// setOnClickListener(new OnClickListener{ void onClick () })
              methodSetlistener.invoke(targetView, proxy);
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        }
      }
    }
  }

  public static void injectEvent(Activity activity) {
    Class<? extends Activity> activityClass = activity.getClass();
    Method[] methods = activityClass.getDeclaredMethods();//拿到这个类所有方法
    for (Method method : methods) {
      LogTools.i(TAG, "injectEvent:method -- " + method.getName());
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
//            ListenerInvocationHandler<Activity> hanlder = new ListenerInvocationHandler<>(method, activity);
//            Object listenerProxy = Proxy.newProxyInstance(listenerType.getClassLoader(), new Class[]{listenerType}, hanlder);
            Object listenerProxy = Proxy.newProxyInstance(listenerType.getClassLoader(),
              new Class[]{listenerType},  //动态代理生成 View.OnClickListener 这个类
              (proxy, method1, args) -> {//  proxy:View.OnClickListener  method1:onClick 相当于监听有没有调用该方法，如果有，则反射调用该方法
                return method.invoke(activity, args);
              });
            for (int viewId : viewIds) {
              View view = activity.findViewById(viewId);
              Method setter = view.getClass().getMethod(listenerSetter, listenerType);//setOnClickListener
              setter.invoke(view, listenerProxy);
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
      //LogTools.i(TAG, "invoke:proxy= " + proxy + "，method==" + method);
      Log.i(TAG, "invoke: method=" + method + ",target=" + target);
      return this.method.invoke(target, args);
    }
  }
}
