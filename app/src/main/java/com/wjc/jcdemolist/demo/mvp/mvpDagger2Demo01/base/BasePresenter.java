package com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.base;

/**
 * ClassName:com.wjc.jcdemolist.demo.mvp.mvpDemo01.base
 * Description:
 * JcChen on 2019/7/11 23:32
 */
public interface BasePresenter {
    /**
     * 初始化数据
     */
    void start();

    /**
     * 销毁 尤其是销毁view --》jetpack lifecycle
     * 当view的生命周期结束后，如 P 继续持有 view 引用，会导致内存泄漏
     */
    void onDestroy();
}
