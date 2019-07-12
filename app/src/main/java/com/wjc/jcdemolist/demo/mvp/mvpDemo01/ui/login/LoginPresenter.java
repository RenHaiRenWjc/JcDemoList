package com.wjc.jcdemolist.demo.mvp.mvpDemo01.ui.login;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * ClassName:com.wjc.jcdemolist.demo.mvp.mvpDemo01.ui.login
 * Description:
 * JcChen on 2019/7/12 7:55
 */
public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;
    private Disposable disposable;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void login(String mobile, String pwd) {
        disposable = Observable.just("登录成功：" + mobile)
                .debounce(500, TimeUnit.MILLISECONDS)
                .delay(1000, TimeUnit.MICROSECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::loginSuccess);

    }

    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {
        view = null;
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
            disposable = null;
        }
    }
}
