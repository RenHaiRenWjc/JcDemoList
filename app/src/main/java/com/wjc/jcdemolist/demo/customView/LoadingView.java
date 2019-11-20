package com.wjc.jcdemolist.demo.customView;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * ClassName:com.wjc.jcdemolist.demo.customView
 * Description:
 * author:wjc on 2019/11/20 11:29
 */
public class LoadingView extends View {
    private Paint mPaint;
    private Path mDesPath;
    private Path mCirclePath;
    private PathMeasure mPathMeasure;
    private Float mCurAnimValue;

    public LoadingView(Context context) {
        super(context);
    }

    public LoadingView(Context context, @Nullable @android.support.annotation.Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadingView(Context context, @Nullable @android.support.annotation.Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.RED);

        mDesPath = new Path();
        mCirclePath = new Path();
        mCirclePath.addCircle(100, 100, 50, Path.Direction.CW);
        mPathMeasure = new PathMeasure(mCirclePath, true);

        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setRepeatCount(ValueAnimator.INFINITE);

        animator.addUpdateListener(animation -> {
            mCurAnimValue = (Float) animation.getAnimatedValue();
            invalidate();
        });
        animator.setDuration(2000);
        animator.start();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float stop = mPathMeasure.getLength() * mCurAnimValue;
        mDesPath.reset();
        mPathMeasure.getSegment(0, stop, mDesPath, true);// 截图某一部分到 mDesPath
        canvas.drawPath(mDesPath, mPaint);
    }
}
