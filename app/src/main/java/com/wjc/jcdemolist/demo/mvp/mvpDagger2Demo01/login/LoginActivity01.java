package com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.login;

import com.wjc.jcdemolist.R;
import com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.base.BaseActivity;
import com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.login.di.DaggerLoginComponent;
import com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.login.di.LoginModule;
import com.wjc.jcdemolist.demo.mvp.mvpDemo02.Utils.Utils;


public class LoginActivity01 extends BaseActivity<LoginContract.Presenter> {

    @Override
    protected int layoutId() {
        return R.layout.activity_main3;
    }

    @Override
    protected void initDate() {
        LoginFragment01 loginFragment01 = (LoginFragment01) getSupportFragmentManager()
                .findFragmentById(R.id.fl_fragment);
        if (loginFragment01 == null) {
            loginFragment01 = LoginFragment01.newInstance();
            Utils.addFragmentToActivity(getSupportFragmentManager(), loginFragment01, R.id.fl_fragment);
        }
        DaggerLoginComponent.builder()
                .appComponent(mApplication.getAppComponent())
                .loginModule(new LoginModule(loginFragment01))
                .build()
                .inject(this);
    }
}
