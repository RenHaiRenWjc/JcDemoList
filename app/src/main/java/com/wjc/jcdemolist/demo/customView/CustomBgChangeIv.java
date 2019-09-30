package com.wjc.jcdemolist.demo.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.wjc.jcdemolist.Utils.LogUtils;
import com.wjc.jcdemolist.Utils.ToolUtils;

/**
 * ClassName:com.wjc.jcdemolist.demo.customView
 * Description:
 * JcChen on 2019/9/30 10:46
 */
public class CustomBgChangeIv extends android.support.v7.widget.AppCompatImageView {
    private static final String TAG = "CustomImageView";

    public CustomBgChangeIv(Context context) {
        super(context);
    }

    public CustomBgChangeIv(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomBgChangeIv(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.i(TAG, "onTouchEvent: event=" + ToolUtils.fetchEventAction(event.getAction()));
        boolean result = false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                this.animate().alpha(0.2f).setDuration(1000).rotation(90).setInterpolator(new AccelerateDecelerateInterpolator());
                result = true;
                break;
            case MotionEvent.ACTION_MOVE:
                result = super.onTouchEvent(event);
                break;
            case MotionEvent.ACTION_UP:
                this.animate().alpha(1.0f);
                result = super.onTouchEvent(event);
                break;
            case MotionEvent.ACTION_CANCEL:
                this.clearAnimation();
                this.animate().alpha(1.0f);
                result = super.onTouchEvent(event);
                break;
        }
        return result;
    }
}
