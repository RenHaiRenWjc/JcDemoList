package com.wjc.jcdemolist.demo.dispatchDemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * ClassName:com.wjc.jcdemolist.demo.dispatchDemo
 * Description:
 * JcChen on 2019/9/9 23:11
 */
public class MyListView extends ListView {
    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private int mLastX, mLastY;

//    //内部拦截法
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent event) {
//        int x = (int) event.getX();
//        int y = (int) event.getY();
//
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN: {
//                getParent().requestDisallowInterceptTouchEvent(true);//让父类不拦截
//                break;
//            }
//            case MotionEvent.ACTION_MOVE: {
//                int deltaX = x - mLastX;
//                int deltaY = y - mLastY;
//                if (Math.abs(deltaX) > Math.abs(deltaY)) {//水平滑动，父类拦截
//                    getParent().requestDisallowInterceptTouchEvent(false);
//                }
//                break;
//            }
//            case MotionEvent.ACTION_UP: {
//                break;
//
//            }
//            default:
//                break;
//        }
//
//        mLastX = x;
//        mLastY = y;
//        return super.dispatchTouchEvent(event);
//    }
}
