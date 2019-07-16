package com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.login.di;

import com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.di.AppComponent;
import com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.login.LoginActivity01;

import dagger.Component;

/**
 * ClassName:com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.login.di
 * Description:注射器
 * 以理解为快递员，那么他需要送的货物就是 modules 里面包含的包裹
 * JcChen on 2019/7/13 16:38
 */
@Component(modules = LoginModule.class, dependencies = AppComponent.class)
public interface LoginComponent {
    /**
     * @param loginActivity01 目标容器
     *                      inject的参数。。。不能是父类，必须是你注入的那个内
     *                      快递地址
     */
    void inject(LoginActivity01 loginActivity01);
}
