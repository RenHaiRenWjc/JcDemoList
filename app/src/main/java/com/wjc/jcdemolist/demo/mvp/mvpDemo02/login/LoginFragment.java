package com.wjc.jcdemolist.demo.mvp.mvpDemo02.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wjc.jcdemolist.R;
import com.wjc.jcdemolist.demo.mvp.mvpDemo02.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * ClassName:com.wjc.jcdemolist.demo.mvp.mvpDemo02.login
 * Description:
 * JcChen on 2019/7/13 9:16
 */
public class LoginFragment extends BaseFragment<LoginContract.Presenter>
        implements LoginContract.View {

    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.bt_ok)
    Button btOk;

    protected static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initDate() {

    }

    @Override
    public void loginSuccess(String result) {
        Toast.makeText(mActivity, result, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresent(LoginContract.Presenter present) {
        if (mPresenter == null) {
            mPresenter = present;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @OnClick(R.id.bt_ok)
    public void onViewClicked() {
        String username = name.getText().toString();
        String password = pwd.getText().toString();
        mPresenter.login(username, password);
    }
}
