package com.wjc.jcdemolist.Utils.Reflect;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: com.wjc.jcdemolist.Utils.Reflect
 * Description:
 * JcChen on 2020.05.10.15:49
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@EventType(listenerType = View.OnLongClickListener.class, listenerSetter = "setOnLongClickListener")
public @interface onLongClick {
  int[] id();
}
