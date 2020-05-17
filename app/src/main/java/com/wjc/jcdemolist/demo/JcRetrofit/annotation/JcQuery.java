package com.wjc.jcdemolist.demo.JcRetrofit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: com.wjc.jcdemolist.demo.JcRetrofit.annotation
 * Description:
 * JcChen on 2020.05.14.22:18
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface JcQuery {
  String value();
}
