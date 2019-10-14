package com.wjc.jcdemolist.demo.PaintAndCanvas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wjc.jcdemolist.R;
import com.wjc.jcdemolist.demo.customView.CustomClearDrawable;

/**
 * 自定义 clearDrawable
 */

public class CustomDrawableFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_custom_drawable, container, false);
        ImageView imageView =(ImageView) rootView.findViewById(R.id.iv_test);
        CustomClearDrawable customClearDrawable = new CustomClearDrawable(getActivity(), 400, 400);
        imageView.setImageDrawable(customClearDrawable);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customClearDrawable.start();
            }
        });

        return rootView;
    }


}
