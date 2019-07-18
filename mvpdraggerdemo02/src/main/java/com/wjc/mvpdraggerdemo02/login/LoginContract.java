package com.wjc.mvpdraggerdemo02.login;



import com.wjc.mvpdraggerdemo02.base.BaseModel;
import com.wjc.mvpdraggerdemo02.base.BasePresenter;
import com.wjc.mvpdraggerdemo02.base.BaseView;

import io.reactivex.Observable;


/**
 * ClassName:com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.login
 * Description:
 * JcChen on 2019/7/13 16:45
 */
public interface LoginContract {
    interface Presenter extends BasePresenter {
        void login(String mobile, String pwd);
    }

    interface View extends BaseView<Presenter> {
        void loginSuccess(Model model);
    }

    interface Model extends BaseModel {
        String getMsg();

        String getUser();

        boolean isSuccess();

        Observable<Model> login(String mobile, String pwd);

    }
}
