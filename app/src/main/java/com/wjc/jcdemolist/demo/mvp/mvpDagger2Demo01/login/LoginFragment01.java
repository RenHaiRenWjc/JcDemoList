package com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.login;


import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;
import com.wjc.jcdemolist.R;
import com.wjc.jcdemolist.Utils.LogUtils;
import com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.base.BaseFragment;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * ClassName:com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.login
 * Description:
 * JcChen on 2019/7/15 22:52
 */
public class LoginFragment01 extends BaseFragment<LoginContract.Presenter> implements LoginContract.View {
    private static final String TAG = "LoginFragment01";
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.bt_ok)
    Button btnLogin;
//    Unbinder unbinder;


    protected static LoginFragment01 newInstance() {
        return new LoginFragment01();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {

    }

    private static boolean isUsrValid(String usr) {
        return true;
    }

    private static boolean isPasswordValid(String pwd) {
        return true;
    }


    @Override
    public void loginSuccess(LoginContract.Model result) {
        LogUtils.i(TAG, "loginSuccess: result=" + result.getMsg() + ", is " + result.isSuccess());
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        if (mPresenter == null) {
            mPresenter = presenter;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        unbinder.unbind();
    }

    @OnClick(R.id.bt_ok)
    public void onViewClicked() {
        Observable<CharSequence> ObservableName = RxTextView.textChanges(name);
        Observable<CharSequence> ObservablePassword = RxTextView.textChanges(pwd);

        Observable.combineLatest(ObservableName, ObservablePassword
                , (phone, password) -> isUsrValid(phone.toString()) && isPasswordValid(password.toString()))
                .subscribe(btnLogin::setEnabled);
        RxView.clicks(btnLogin)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(v -> {
                    String username = name.getText().toString();
                    String password = pwd.getText().toString();
                    mPresenter.login(username, password);
                });
    }
}
