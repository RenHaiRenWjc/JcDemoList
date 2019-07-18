package com.wjc.mvpdraggerdemo02.base;

/**
 * ClassName:com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.base
 * Description:
 * JcChen on 2019/7/13 16:06
 */
public abstract class AbsPresenter<M extends BaseModel, V extends BaseView> {
    protected M mModel;
    protected V mView;
}
