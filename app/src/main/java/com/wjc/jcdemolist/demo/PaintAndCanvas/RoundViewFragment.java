package com.wjc.jcdemolist.demo.PaintAndCanvas;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wjc.jcdemolist.R;

/**
 * ClassName:com.wjc.jcdemolist.demo.PaintAndCanvas
 * Description:
 * JcChen on 2019/10/2 9:28
 */
public class RoundViewFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_roundview_layout, container, false);
    }
}
