package com.wjc.jcdemolist.demo.Dagger2Test.test02;

import dagger.Module;
import dagger.Provides;

/**
 * ClassName:com.wjc.jcdemolist.demo.Dagger2Test
 * Description:生成依赖的对象
 * 1. 应用场景依赖的构造函数是第三方库
 * 2. 构造函数是带参数的
 * JcChen on 2019/7/8 23:29
 */
@Module
public class MarkCarModule {
    public MarkCarModule() {
    }

//    @Engine.QualifierA
//    @Provides
//    Engine provideEngineA() {
//        return new Engine("gearA");
//    }
//
//    @Engine.QualifierB
//    @Provides
//    Engine provideEngineB() {
//        return new Engine("gearB");
//    }

    @Engine.CarScope
    @Provides
    Engine provideEngine() {
        return new Engine("jc-gear");
    }
}
