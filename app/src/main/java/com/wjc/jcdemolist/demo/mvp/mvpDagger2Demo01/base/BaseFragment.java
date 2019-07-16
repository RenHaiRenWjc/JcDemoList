package com.wjc.jcdemolist.demo.mvp.mvpDagger2Demo01.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * ClassName:com.wjc.jcdemolist.demo.mvp.mvpDemo02.base
 * Description:
 * JcChen on 2019/7/13 8:52
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment {
    protected BaseActivity mActivity;
    private View mRootView;
    private Unbinder mUnbinder;
    protected P mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(layoutId(), container, false);
        mUnbinder = ButterKnife.bind(this, mRootView);
        initData();
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }
        mUnbinder = null;
        mPresenter = null;
        mActivity = null;
        mRootView = null;
    }

    protected abstract int layoutId();
    protected abstract void initData();

}
