package com.wjc.jcdemolist.demo.dispatchDemo02;

import android.content.Context;
import android.util.AttributeSet;
import com.wjc.jcdemolist.Utils.LogUtils;
import android.view.MotionEvent;
import android.view.animation.Interpolator;
import android.widget.ScrollView;
import android.widget.Scroller;

/**
 * ClassName:com.wjc.jcdemolist.demo.dispatchDemo02
 * Description:
 * JcChen on 2019/9/10 0:12
 */
public class MyScrollView extends ScrollView {
    private static final String TAG = "MyScrollView";

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int mLastX, mLastY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                LogUtils.i(TAG, "dispatchTouchEvent: ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                LogUtils.i(TAG, "dispatchTouchEvent:action " + event.getAction());
                LogUtils.i(TAG, "dispatchTouchEvent:  getScrollY()=" + getScrollY());
                LogUtils.i(TAG, "dispatchTouchEvent: deltaX=" + deltaX + ",deltaY=" + deltaY);
                if (deltaY > 0 && getScrollY() == 0) {//顶部下滑
                    getParent().requestDisallowInterceptTouchEvent(false); //父类拦截
                    return false;
                } else if (deltaY < 0 && getScrollY() == this.getMeasuredHeight()) { //底部上滑
                    getParent().requestDisallowInterceptTouchEvent(false); //父类拦截
                    return false;
                }
                break;
        }
        mLastX = x;
        mLastY = y;

        boolean result = super.dispatchTouchEvent(event);
        LogUtils.i(TAG, "dispatchTouchEvent: result=" + result);
        return true;
    }
}
