package com.wjc.jcdemolist.demo.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.wjc.jcdemolist.Utils.LogUtils;

/**
 * ClassName:com.wjc.jcdemolist.demo.customView
 * Description:
 * JcChen on 2019/8/28 22:56
 */
public class CustomViewGroup extends ViewGroup {
    private static final String TAG = "CustomViewGroup";
    private static final int OFFSET = 100; // 表示缩进的尺寸

    public CustomViewGroup(Context context) {
        super(context);
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // 1. 获取ViewGroup 自身宽高、mode
        int widthSpaceMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpaceMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSpaceSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpaceSize = MeasureSpec.getSize(heightMeasureSpec);
        // 2. 获取 child 宽高、 MeasureSpace ，then Measure childView
        for (int i = 0; i < getChildCount(); i++) {// 设置 child MeasureSpec
            View child = getChildAt(i);
            LayoutParams layoutParams = child.getLayoutParams();
            //View 的 mPaddingLeft 直接调用不了，他们为包内可见  ,获取 childView 的 MeasureSpace
            int childWidthSpace = getChildMeasureSpec(widthMeasureSpec, 0, layoutParams.width);
            int childHeightSpace = getChildMeasureSpec(heightMeasureSpec, 0, layoutParams.height);
            child.measure(childWidthSpace, childHeightSpace);
        }

        int width = 0;
        int height = 0;
        // 3.ViewGroup 根据自身的情况，计算自己尺寸
        switch (widthSpaceMode) {
            case MeasureSpec.EXACTLY:
                width = widthSpaceSize;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                for (int i = 0; i < getChildCount(); i++) {
                    View child = getChildAt(i);
                    int widthAddOffset = i * OFFSET + child.getMeasuredWidth();
                    width = Math.max(width, widthAddOffset);
                }
                break;
            default:
                break;
        }
        switch (heightSpaceMode) {
            case MeasureSpec.EXACTLY:
                height = heightSpaceSize;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                for (int i = 0; i < getChildCount(); i++) {
                    View child = getChildAt(i);
                    height += child.getMeasuredHeight();
                }
                break;
            default:
                break;
        }
        LogUtils.i(TAG, "onMeasure: w=" + width + ",h=" + height);
        // 4. 保存自身尺寸
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        LogUtils.d(TAG, "onLayout() called with: changed = [" + changed + "], l = [" + l + "], " +
                "t = [" + t + "], r = [" + r + "], b = [" + b + "]");
        int left = 0, top = 0, right = 0, bottom = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            left = i * OFFSET;
            right = left + child.getMeasuredWidth();
            bottom = top + child.getMeasuredHeight();
            child.layout(left, top, right, bottom);
            top += child.getMeasuredHeight();

        }
    }
}
