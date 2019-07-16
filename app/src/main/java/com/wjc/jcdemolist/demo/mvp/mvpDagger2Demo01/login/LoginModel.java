package com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.login;

import com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.network.WanAndroidApi;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * ClassName:com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.login
 * Description:
 * JcChen on 2019/7/13 16:53
 */
public class LoginModel implements LoginContract.Model {
    private String user;
    private boolean isSuccess;
    private String msg;

    @Inject
    public LoginModel() {
    }

    public LoginModel(String user, boolean isSuccess, String msg) {
        this.user = user;
        this.isSuccess = isSuccess;
        this.msg = msg;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public boolean isSuccess() {
        return isSuccess;
    }

    @Inject
    WanAndroidApi wanAndroidApi;

    @Override
    public Observable<LoginContract.Model> login(String mobile, String pwd) {
        return wanAndroidApi.login(mobile, pwd).map(baseResponse ->
                (LoginContract.Model) new LoginModel(mobile, baseResponse.getErrorCode() == 0 ? true : false,
                        baseResponse.getErrorMsg())).toObservable();
    }
}
