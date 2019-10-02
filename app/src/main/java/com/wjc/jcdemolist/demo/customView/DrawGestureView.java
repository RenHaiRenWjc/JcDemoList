package com.wjc.jcdemolist.demo.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.wjc.jcdemolist.R;

/**
 * ClassName:com.wjc.jcdemolist.demo.customView
 * Description:
 * JcChen on 2019/10/2 13:06
 */
public class DrawGestureView extends View {
    private Paint mPaint;
    private Path mPath;
    private float preX, preY;

    public DrawGestureView(Context context) {
        super(context);
        init();
    }

    public DrawGestureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawGestureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setColor(getResources().getColor(R.color.color1));
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(), event.getY());
                preX = event.getX();
                preY = event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
//                mPath.lineTo(event.getX(), event.getY());

                float endX = (preX + event.getX()) / 2;
                float endY = (preY + event.getY()) / 2;
                mPath.quadTo(preX, preY, endX, endY); // 贝塞尔曲线
                preX = event.getX();
                preY = event.getY();

                invalidate(); // 与 postInvalidate() 区别，invalidate(): 一定要在UI线程执行，如果不是在UI线程就会报错;
                // postInvalidate()就是利用handler给主线程发送刷新界面的消息来实现的，所以它是可以在任何线程中执行，而不会出错。
                // 而正是因为它是通过发消息来实现的，所以它的界面刷新可能没有直接调Invalidate()刷的那么快
                // 使用场景：
//                  当前线程是主线程的情况下，还是以invalidate()函数为主。当我们不确定当前要刷新页面的位置所处的线程是不是主线程的时候，还是用postInvalidate为好；
                break;
        }
        return super.onTouchEvent(event);
    }

    public void reset() {
        mPath.reset();
        invalidate();
    }
}
