package com.wjc.jcdemolist.demo.mvp.mvpDemo02.login;

import android.os.Bundle;

import com.wjc.jcdemolist.R;
import com.wjc.jcdemolist.Utils.ToolUtils;
import com.wjc.jcdemolist.demo.mvp.mvpDemo02.base.BaseActivity;

public class LoginActivity extends BaseActivity<LoginContract.Presenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    @Override
    protected void initData() {
        LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fl_fragment);
        if (loginFragment == null) {
            loginFragment = LoginFragment.newInstance();
            ToolUtils.addFragmentToActivity(getSupportFragmentManager()
                    , loginFragment, R.id.fl_fragment);
        }
        mPresenter = new LoginPresenter(loginFragment);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main3;
    }
}
