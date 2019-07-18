package com.wjc.mvpdraggerdemo02.login;


import com.wjc.mvpdraggerdemo02.R;
import com.wjc.mvpdraggerdemo02.Utils.ToolUtils;
import com.wjc.mvpdraggerdemo02.base.BaseActivity;

import javax.inject.Inject;


public class LoginActivity02 extends BaseActivity<LoginContract.Presenter> implements LoginContract.View {
    @Inject
    LoginPresenter02 presenter;

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
            ToolUtils.addFragmentToActivity(getSupportFragmentManager(), loginFragment01, R.id.fl_fragment);
        }
        loginFragment01.setPresenter(presenter);
    }

    @Override
    public void loginSuccess(LoginContract.Model model) {

    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {

    }
}
