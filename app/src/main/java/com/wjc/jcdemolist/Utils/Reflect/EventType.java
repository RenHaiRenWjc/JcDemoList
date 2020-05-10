package com.wjc.jcdemolist.Utils.Reflect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: com.wjc.jcdemolist.Utils.Reflect
 * Description:
 * JcChen on 2020.05.10.15:44
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventType {
  Class listenerType();

  String listenerSetter();
}
