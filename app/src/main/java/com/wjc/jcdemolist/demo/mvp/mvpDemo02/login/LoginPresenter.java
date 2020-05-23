package com.wjc.jcdemolist.demo.mvp.mvpDemo02.login;

import com.wjc.jcdemolist.Utils.LogTools;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * ClassName:com.wjc.jcdemolist.demo.mvp.mvpDemo02.login
 * Description:
 * JcChen on 2019/7/13 9:08
 */
public class LoginPresenter implements LoginContract.Presenter {
    private static final String TAG = "LoginPresenter01";
    private LoginContract.View mView;
    private Disposable disposable;

    public LoginPresenter(LoginContract.View mView) {
        this.mView = mView;
        this.mView.setPresent(this);
    }

    @Override
    public void login(String mobile, String pwd) {
        disposable = Observable.just("登录成功：" + mobile)
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mView::loginSuccess);
    }

    @Override
    public void start() {
        LogTools.i(TAG, "start: ");
    }

    @Override
    public void onDestroy() {
        mView = null;
        if (disposable != null && disposable.isDisposed()) {
            disposable.dispose();
            disposable = null;
        }
    }
}
