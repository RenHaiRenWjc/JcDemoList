package com.wjc.jcdemolist.Utils.Reflect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: com.wjc.jcdemolist.demo.changeSkin
 * Description:
 * JcChen on 2020.05.08.00:01
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoWrited {
  String key();
}
