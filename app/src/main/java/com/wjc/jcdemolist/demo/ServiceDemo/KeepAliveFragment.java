package com.wjc.jcdemolist.demo.ServiceDemo;

import android.content.Intent;

import com.wjc.jcdemolist.R;
import com.wjc.jcdemolist.Utils.LogUtils;
import com.wjc.jcdemolist.base.BaseFragment;


public class KeepAliveFragment extends BaseFragment {
    private static final String TAG = "KeepAliveFragment";

    @Override
    public int setContentLayoutId() {
        return R.layout.fragment_keep_alive;
    }

    @Override
    public void initView() {

    }

    @Override
    public void doTask() {
        if (getActivity() == null) return;
        LogUtils.i(TAG, "doTask: activity=" + getActivity());
        getActivity().startService(new Intent(getActivity(), ForegroundService.class));
    }


}
