package com.wjc.jcdemolist.demo.Dagger2Test.test01;

import dagger.Component;

/**
 * ClassName:com.wjc.jcdemolist.demo.Dagger2Test
 * Description: 注射器
 * JcChen on 2019/7/8 23:12
 */
@Component(modules = MarkCarModule.class)
public interface CarComponent {
    void inject(Car car);
}
