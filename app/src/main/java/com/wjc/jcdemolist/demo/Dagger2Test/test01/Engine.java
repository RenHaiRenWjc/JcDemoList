package com.wjc.jcdemolist.demo.Dagger2Test.test01;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Inject;
import javax.inject.Qualifier;

/**
 * ClassName:com.wjc.jcdemolist.demo.Dagger2Test
 * Description:依赖对象
 * JcChen on 2019/7/8 23:07
 */
public class Engine {
    private static final String TAG = "Engine";
    private String name;

    @Qualifier  //@QualifierA 用来标记自定义的注解，目的是为了用自定义注解去标注提供依赖方法和被依赖变量
    @Retention(RetentionPolicy.RUNTIME)
    public @interface QualifierA {
    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface QualifierB {
    }

    @Inject // 标记构造函数、
    public Engine() {
    }


    public Engine(String name) {
        this.name = name;
        System.out.println("create---" + name);
    }

    public void run() {
        System.out.println("run:引擎启动成功了。。。。");
    }

    @Override
    public String toString() {
        return "Engine{" +
                "name='" + name + '\'' +
                '}';
    }
}
