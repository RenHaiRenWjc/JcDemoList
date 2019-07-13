package com.wjc.jcdemolist.demo.mvp.mvpDemo02.login;

import com.wjc.jcdemolist.demo.mvp.mvpDemo02.base.BasePresenter;
import com.wjc.jcdemolist.demo.mvp.mvpDemo02.base.BaseView;

/**
 * ClassName:com.wjc.jcdemolist.demo.mvp.mvpDemo02.login
 * Description:
 * JcChen on 2019/7/13 9:08
 */
public interface LoginContract {
    interface Presenter extends BasePresenter {
        void login(String mobile, String pwd);
    }

    interface View extends BaseView<Presenter> {
        void loginSuccess(String result);
    }

}
