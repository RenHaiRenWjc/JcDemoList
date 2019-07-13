package com.wjc.jcdemolist.demo.mvp.mvpDemo01.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wjc.jcdemolist.R;
import com.wjc.jcdemolist.demo.mvp.mvpDemo01.Main2Activity;
import com.wjc.jcdemolist.demo.mvp.mvpDemo01.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginContract.Presenter>
        implements LoginContract.View {


    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.bt_ok)
    Button btOk;

    @Override
    protected int layoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initDate() {
        new LoginPresenter(this).start();
    }

    @Override
    public void loginSuccess(String result) {
        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(LoginActivity.this, Main2Activity.class));
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        if (mPresenter == null) {
            mPresenter = presenter;
        }
    }

    @OnClick(R.id.bt_ok)
    public void onViewClicked() {
        String userName = name.getText().toString();
        String userPwd = pwd.getText().toString();
        mPresenter.login(userName, userPwd);
    }
}
