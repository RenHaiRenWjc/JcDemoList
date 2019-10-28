package com.wjc.jcdemolist.demo.PaintAndCanvas;

import android.view.View;
import android.widget.Button;

import com.wjc.jcdemolist.R;
import com.wjc.jcdemolist.base.BaseFragment;
import com.wjc.jcdemolist.demo.customView.DrawGestureView;

/**
 * ClassName:com.wjc.jcdemolist.demo.PaintAndCanvas
 * Description: 手写笔
 * JcChen on 2019/10/2 13:16
 */
public class DrawGestureFragment extends BaseFragment {

    @Override
    public int setContentLayoutId() {
        return R.layout.fragment_draw_gesture_layout;
    }

    @Override
    public void initView() {
        DrawGestureView drawGestureView = rootView.findViewById(R.id.dv);
        Button bt = rootView.findViewById(R.id.bt_reset);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawGestureView.reset();
            }
        });
    }

    @Override
    public void doTask() {

    }
}
