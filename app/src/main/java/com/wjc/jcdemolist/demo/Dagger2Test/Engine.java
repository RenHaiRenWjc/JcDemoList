package com.wjc.jcdemolist.demo.Dagger2Test;

import com.wjc.jcdemolist.Utils.LogUtils;

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

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface QualifierA {
    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface QualifierB {
    }

    @Inject
    public Engine() {
    }


    public Engine(String name) {
        this.name = name;
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
