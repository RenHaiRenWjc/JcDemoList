package com.wjc.jcdemolist.demo.Dagger2Test.test01;

import com.wjc.jcdemolist.demo.Dagger2Test.test01.DaggerCarComponent;

import javax.inject.Inject;

/**
 * ClassName:com.wjc.jcdemolist.demo.Dagger2Test
 * Description: 需要依赖 Engine
 * JcChen on 2019/7/8 23:09
 */
public class Car {
    private static final String TAG = "Car";

    @Engine.QualifierA
    @Inject  //@Inject有两个作用，一是用来标记需要依赖的变量，以此告诉Dagger2为它提供依赖
    public Engine engineA;

    @Engine.QualifierB
    @Inject
    public Engine engineB;

    public Car() {
//      DaggerCarComponent.builder().build().inject(this);
        DaggerCarComponent.builder().markCarModule(new MarkCarModule()).build().inject(this);
    }

    public Engine getEngineA() {
        return this.engineA;
    }

    public Engine getEngineB() {
        return this.engineB;
    }

    public static void main(String[] args) {
        Car car = new Car();
        car.getEngineA().run();
        System.out.println("main: " + car.getEngineA().toString());
        System.out.println("main: " + car.getEngineB().toString());
    }
}
