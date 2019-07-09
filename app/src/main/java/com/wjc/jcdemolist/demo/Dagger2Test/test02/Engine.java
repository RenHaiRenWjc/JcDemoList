package com.wjc.jcdemolist.demo.Dagger2Test.test02;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Inject;
import javax.inject.Qualifier;
import javax.inject.Scope;

/**
 * ClassName:com.wjc.jcdemolist.demo.Dagger2Test
 * Description:依赖对象
 * JcChen on 2019/7/8 23:07
 */
public class Engine {
    private static final String TAG = "Engine";
    private String name;

    @Scope
    @Retention(RetentionPolicy.RUNTIME)
    public @interface CarScope {
    }

    @Inject
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
