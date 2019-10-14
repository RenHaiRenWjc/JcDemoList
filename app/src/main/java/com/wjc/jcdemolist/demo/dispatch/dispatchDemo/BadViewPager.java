package com.wjc.jcdemolist.demo.dispatch.dispatchDemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * ClassName:com.wjc.jcdemolist.demo.dispatch.dispatchDemo
 * Description:
 * JcChen on 2019/9/9 23:07
 */
public class BadViewPager extends ViewPager {
    private int mLastX, mLastY;

    public BadViewPager(@NonNull Context context) {
        super(context);
    }

    public BadViewPager(@NonNull Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        //内部拦截发
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            super.onInterceptTouchEvent(event);
//            return false; // 因为 down，  resetTouchState()  重置标志位 ---手动设置不拦截
//        }
//
//        return true; // 进入到这里，一定要拦截他，因为是子 View 回调的

        // 外部拦截法
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                mLastX = (int) event.getX();
                mLastY = (int) event.getY();
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                if (Math.abs(deltaX) > Math.abs(deltaY)) {  // 水平滑动，ViewPager 处理；其他的 ViewPager 子View 处理
                    return true;
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                break;
            }
            default:
                break;
        }
        return super.onInterceptTouchEvent(event);
    }
}
