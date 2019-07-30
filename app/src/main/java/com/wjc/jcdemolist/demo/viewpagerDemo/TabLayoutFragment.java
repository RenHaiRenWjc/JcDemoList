package com.wjc.jcdemolist.demo.viewpagerDemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wjc.jcdemolist.R;

/**
 * ClassName:com.wjc.jcdemolist.demo.viewpagerDemo
 * Description:
 * author:wjc on 2019/7/30 17:14
 */

public class TabLayoutFragment extends Fragment {
    private String title;
    private static final String TAB_TITLE = "tab";

    public static TabLayoutFragment newInstance(String title) {
        TabLayoutFragment tabLayoutFragment = new TabLayoutFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TAB_TITLE, title);
        tabLayoutFragment.setArguments(bundle);
        return tabLayoutFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_layout_fragment, container, false);
        return view;
    }
}
