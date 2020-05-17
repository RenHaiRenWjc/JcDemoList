package com.wjc.jcdemolist.demo.JcRetrofit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: com.wjc.jcdemolist.demo.JcRetrofit.annotation
 * Description:
 * JcChen on 2020.05.14.22:16
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JcPOST {
  String value();
}
