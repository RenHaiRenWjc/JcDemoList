package com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.login;

import android.util.Log;

import com.wjc.jcdemolist.Utils.LogUtils;
import com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.base.AbsPresenter;


import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * ClassName:com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.login
 * Description:
 * JcChen on 2019/7/13 17:27
 */
public class LoginPresenter01 extends AbsPresenter<LoginContract.Model, LoginContract.View>
        implements LoginContract.Presenter {
    private static final String TAG = "LoginPresenter01";
    private Disposable disposable;

    //@Inject作用二: 用来标记构造函数，
    // Dagger2通过@Inject注解可以在需要这个类实例的时候来找到这个构造函数并把相关实例构造出来
    // ，以此来为被@Inject标记了的变量提供依赖
    @Inject
    public LoginPresenter01(LoginContract.Model model, LoginContract.View view) {
        mModel = model;
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void login(String mobile, String pwd) {
        LogUtils.d(TAG, "login() called with: mobile = [" + mobile + "], pwd = [" + pwd + "]");
        mModel.login(mobile, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginContract.Model>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(LoginContract.Model model) {
                        mView.loginSuccess(model);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.i(TAG, "onError: e=" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void start() {
//       this.mView .setPresenter(this);
    }

    @Override
    public void onDestroy() {
        mView = null;
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
            disposable = null;
        }
    }
}
