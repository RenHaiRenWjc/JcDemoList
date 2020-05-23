package com.wjc.jcdemolist.Utils.Reflect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: com.wjc.jcdemolist.Utils.Reflect
 * Description:
 * JcChen on 2020.05.22.22:41
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectView {
  int value();
}
