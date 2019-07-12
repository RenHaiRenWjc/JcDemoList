package com.wjc.jcdemolist.demo.mvp.mvpDemo01.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * ClassName:com.wjc.jcdemolist.demo.mvp.mvpDemo01.base
 * Description:
 * JcChen on 2019/7/12 7:38
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {
    private Unbinder mUbinder;
    protected Context context;
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(layoutId());
        mUbinder = ButterKnife.bind(this);
        initDate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUbinder != Unbinder.EMPTY) mUbinder.unbind();
        if (mPresenter != null) mPresenter.onDestroy();
        this.mPresenter = null;
        this.mUbinder = null;
        this.context = null;
    }

    protected abstract int layoutId();

    protected abstract void initDate();
}
