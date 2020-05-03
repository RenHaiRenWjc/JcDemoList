package com.wjc.jcdemolist.base;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * ClassName:com.wjc.jcdemolist.base
 * Description:
 * JcChen on 2019/10/2 13:17
 */
public abstract class BaseFragment extends Fragment {

    public View rootView;

    public abstract int setContentLayoutId();

    public abstract void initView();

    public abstract void doTask();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(setContentLayoutId(), container, false);
        }
        initView();
        doTask();
        return rootView;
    }
}
