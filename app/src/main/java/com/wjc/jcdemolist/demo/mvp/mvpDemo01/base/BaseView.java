package com.wjc.jcdemolist.demo.mvp.mvpDemo01.base;

/**
 * ClassName:com.wjc.jcdemolist.demo.mvp.mvpDemo01.base
 * Description:
 * JcChen on 2019/7/11 23:30
 */
public interface BaseView<T> {
    /**
     * 把 P 层和 View 层关联起来
     * @param presenter
     */
    void setPresenter(T presenter);
}
