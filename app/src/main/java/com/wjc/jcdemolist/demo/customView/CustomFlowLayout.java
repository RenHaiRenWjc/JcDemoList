package com.wjc.jcdemolist.demo.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.wjc.jcdemolist.Utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:com.wjc.jcdemolist.demo.customView
 * Description:
 * JcChen on 2019/9/2 8:26
 */
public class CustomFlowLayout extends ViewGroup {
    private static final String TAG = "CustomFlowLayout";
    List<Integer> lineHeights = new ArrayList<>();
    List<List<View>> views = new ArrayList<>();
    ArrayList<View> lineViews = new ArrayList<>();

    public CustomFlowLayout(Context context) {
        super(context);
    }

    public CustomFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 1. 测量 ViewGroup 宽高
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        init();

        int flowLayoutWidth = 0;
        int flowLayoutHeight = 0;
        int lineWidth = 0, lineHeight = 0;  // 一行的高度、宽度

        //2. 测量子View 获取到当前子View的测量的宽度/高度，并计算 ViewGroup 宽高
        LogUtils.i(TAG, "onMeasure: " + getChildCount());
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            //获取到当前子View的测量的宽度/高度
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            if (lineWidth + childWidth > width) {//换行
                flowLayoutHeight += lineHeight;
                flowLayoutWidth = Math.max(flowLayoutWidth, lineWidth);
                views.add((ArrayList<View>) lineViews.clone());
                lineHeights.add(lineHeight);
                lineViews.clear();
                lineWidth = 0;
                lineHeight = 0;
            }
            lineViews.add(getChildAt(i));
            lineWidth += childWidth;
            lineHeight = Math.max(lineHeight, childHeight);

            if (i == getChildCount() - 1) {
                flowLayoutHeight += lineHeight;
                flowLayoutWidth = Math.max(flowLayoutWidth, lineWidth);
                views.add(lineViews);
                lineHeights.add(lineHeight);
            }
        }
        // 3. 保存自身宽高
        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? width : flowLayoutWidth, heightMode == MeasureSpec.EXACTLY ? height : flowLayoutHeight);
    }


    private void init() {
        lineViews.clear();
        views.clear();
        lineHeights.clear();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int top = 0;
        for (int i = 0; i < views.size(); i++) {// 所有子 View
            List<View> lineView = views.get(i);
            int left = 0;
            for (int j = 0; j < lineView.size(); j++) { // 一行中的子View
                View child = lineView.get(j);
                child.layout(left, top, left + child.getMeasuredWidth(), top + child.getMeasuredHeight());
                left += child.getMeasuredWidth();
            }
            top += lineHeights.get(i);
        }
    }
}
