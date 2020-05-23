package com.wjc.jcdemolist.Utils.Reflect;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: com.wjc.jcdemolist.demo.changeSkin
 * Description:
 * JcChen on 2020.05.07.23:20
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@EventBase(listenerType = View.OnClickListener.class,  setListener= "setOnClickListener",methodCallback = "onClick")
public @interface onClick02 {
  int[] id();

}
