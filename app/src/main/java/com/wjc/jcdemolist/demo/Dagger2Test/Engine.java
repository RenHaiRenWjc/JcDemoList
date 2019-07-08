package com.wjc.jcdemolist.demo.Dagger2Test;

import com.wjc.jcdemolist.Utils.LogUtils;

import javax.inject.Inject;

/**
 * ClassName:com.wjc.jcdemolist.demo.Dagger2Test
 * Description:依赖对象
 * JcChen on 2019/7/8 23:07
 */
public class Engine {
    private static final String TAG = "Engine";
    private String name;

    @Inject
    public Engine() {
    }

    public Engine(String name) {
        this.name = name;
    }

    public void run() {
        System.out.println("run:引擎启动成功了。。。。");
    }
}
