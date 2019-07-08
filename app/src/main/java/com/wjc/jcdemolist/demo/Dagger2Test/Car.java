package com.wjc.jcdemolist.demo.Dagger2Test;

import javax.inject.Inject;

/**
 * ClassName:com.wjc.jcdemolist.demo.Dagger2Test
 * Description:
 * JcChen on 2019/7/8 23:09
 */
public class Car {
    private static final String TAG = "Car";
    @Inject
    Engine engine;

    public Car() {
      DaggerCarComponent.builder().build().inject(this);
//      DaggerCarComponent.builder().markCarModle(new MarkCarModle()).build().inject(this);
    }

    public Engine getEngine() {
        return this.engine;
    }

    public static void main(String[] args) {
        Car car = new Car();
         car.getEngine().run();
        System.out.println("main: " + car.getEngine());
    }
}
