package com.wjc.jcdemolist.demo.dispatch.dispatchDemo02;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

import com.wjc.jcdemolist.Utils.LogUtils;

/**
 * ClassName:com.wjc.jcdemolist.demo.dispatch.dispatchDemo02
 * Description:
 * JcChen on 2019/9/11 8:36
 */
public class MyScrollView02 extends ScrollView {
    private static final String TAG = "MyScrollView02";

    public MyScrollView02(Context context) {
        super(context);
    }

    public MyScrollView02(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean result = super.dispatchTouchEvent(ev);
        LogUtils.i(TAG, "dispatchTouchEvent: result="+result);
        return result;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtils.i(TAG, "onInterceptTouchEvent: ev=" + ev.getAction());
//        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//            return false;
//        } else {
//            return super.onInterceptTouchEvent(ev);
//        }
        return super.onInterceptTouchEvent(ev);
    }
}
