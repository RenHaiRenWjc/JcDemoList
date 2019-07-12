package com.wjc.jcdemolist.demo.mvp.mvpDemo01.ui.login;

import com.wjc.jcdemolist.demo.mvp.mvpDemo01.base.BasePresenter;
import com.wjc.jcdemolist.demo.mvp.mvpDemo01.base.BaseView;

/**
 * ClassName:com.wjc.jcdemolist.demo.mvp.mvpDemo01.ui.login
 * Description:接口管理，这里没有 model 层，Presenter 层充当了 model 层职责
 * JcChen on 2019/7/12 7:52
 */
public interface LoginContract {
    interface Presenter extends BasePresenter {
        void login(String mobile, String pwd);
    }

    interface View extends BaseView<Presenter> {
        void loginSuccess(String result);
    }
}
