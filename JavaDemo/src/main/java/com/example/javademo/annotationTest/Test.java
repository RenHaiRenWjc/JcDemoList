package com.example.javademo.annotationTest;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName:com.example.javademo.annotationTest
 * Description:
 * JcChen on 2019/7/6 17:28
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
    int id() default -1;
    String msg() default "hi";
}
