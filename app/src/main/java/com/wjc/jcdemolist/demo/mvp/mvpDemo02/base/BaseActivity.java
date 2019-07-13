package com.wjc.jcdemolist.demo.mvp.mvpDemo02.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * ClassName:com.wjc.jcdemolist.demo.mvp.mvpDemo02.base
 * Description:
 * JcChen on 2019/7/12 8:47
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {
    private Unbinder mUnbinder;
    protected Context mContext;
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        mUnbinder = ButterKnife.bind(this);
        mContext = this;
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }
        mPresenter = null;
        mContext = null;
    }

    protected abstract void initData();

    protected abstract int layoutId();
}
