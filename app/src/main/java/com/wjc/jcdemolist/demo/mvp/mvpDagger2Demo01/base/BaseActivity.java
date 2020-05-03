package com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.base;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wjc.jcdemolist.JcApplication;


import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * ClassName:com.wjc.jcdemolist.demo.mvp.mvpDemo01.base
 * Description:
 * JcChen on 2019/7/12 7:38
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {
    protected JcApplication mApplication;
    private Unbinder mUnbinder;
    protected Context context;
    @Inject
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        mApplication = (JcApplication) getApplication();
        context = this;
        mUnbinder = ButterKnife.bind(this);
        initDate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != Unbinder.EMPTY) mUnbinder.unbind();
        if (mPresenter != null) mPresenter.onDestroy();
        this.mPresenter = null;
        this.mUnbinder = null;
        this.context = null;
        this.mApplication = null;
    }

    protected abstract int layoutId();

    protected abstract void initDate();
}
