package com.wjc.jcdemolist.demo.customView;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wjc.jcdemolist.R;
import com.wjc.jcdemolist.Utils.LogUtils;

/**
 * ClassName:com.wjc.jcdemolist.demo.customView
 * Description:
 * JcChen on 2019/9/26 19:47
 */
public class CustomClearDrawable extends Drawable {
    private static final String TAG = "CustomClearDrawable";


    public CustomClearDrawable(Context context, int width, int height) {
        super();
        init(context, width, height);
    }

    // 动画状态
    private final int state_origin = 0; // 初始状态
    private final int state_rotate = 1; // 外圈旋转
    private final int state_up = 2;//上移
    private final int state_down = 3;//下移
    private final int state_finish = 4; //结束
    private int mAnimState = state_origin;

    private final long DURATION_ROTATE = 1250; //外圈旋转时长
    private final long DURATION_CROSS = 250; // x 缩小至0的时长
    private final long DURATION_CROSS_DELAY = 1000;
    private final long DURATION_POINT_UP = 250;// 点 往上移动的时长
    private final long DURATION_POINT_DOWN = 350;// 点 往下移动的时长
    private final long DURATION_FINISH = 200;// √ 缩放的时长
    private final long DURATION_ORIGIN_DELAY = 3000;// 返回初始状态的时长


    private final float ROTATE_DEGREE_TOTAL = -1080.0f;//总共旋转的角度 即旋转3圈 6π
    private float mRotateDegreeScale; //旋转动画系数

    private final float PI_DEGREE = (float) (180.0f / Math.PI);// 一弧度多少度
    private final float cross_length = 62.0f; // X 的长度
    private final float cross_degree = 45.0f / PI_DEGREE; // X 与 X轴的夹角
    private float mCrossScale = 1.0f;

    private float mCircleRadius = 2.0f;
    private float up_distance = 24.0f;
    private float down_distance = 20.0f;

    private float mPointPosScale;

    private float fork_left_len = 33.0f;//左边短边距离
    private float fork_left_degree = 40.f / PI_DEGREE;
    private float fork_right_len = 50.0f;
    private final float fork_right_degree = 50.0f / PI_DEGREE;

    private final float drawable_width = 180.0f; // 默认drawable 大小
    private final float paint_width = 4.0f;//画笔宽
    private final float paint_point = 1.0f;//点笔宽
    private Matrix mRotateMatrix = new Matrix();


    private Paint mPaint;
    private Paint mLinePaint;
    private int mWidth, mHeight;
    private Bitmap mCircleBitmap, mBgBitmap;
    private float mViewScale; // drawable 缩放率
    private float mCenterX, mCenterY;// 圆心坐标
    private float mPaintWith, mPointPaintWith;
    private float mCrossLen;
    private Interpolator fast_out_slow_in, fast_out_linear_in;
    private AnimatorSet mAnimatorSet;


    private void init(Context context, int w, int h) {
        mPaint = new Paint();
        mLinePaint = new Paint();
        mWidth = w;
        mHeight = h;
        Bitmap tempCircleBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.circle);
        Bitmap tempBgBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.bg);
        mCircleBitmap = Bitmap.createScaledBitmap(tempCircleBitmap, mWidth, mHeight, true);
        mBgBitmap = Bitmap.createScaledBitmap(tempBgBitmap, mWidth, mHeight, true);
        mViewScale = mWidth / drawable_width;
        if (mCircleBitmap != tempCircleBitmap) {
            tempCircleBitmap.recycle();
        }
        if (mBgBitmap != tempBgBitmap) {
            tempBgBitmap.recycle();
        }
        mCenterX = mWidth / 2.0f;
        mCenterY = mHeight / 2.0f;

        mPaintWith = paint_width * mViewScale;
        mPointPaintWith = paint_width * mViewScale;
        mCrossLen = cross_length * mViewScale;
        up_distance = up_distance * mViewScale;
        mCircleRadius = mCircleRadius * mViewScale;
        fork_left_len = fork_left_len * mViewScale;
        fork_right_len = fork_right_len * mViewScale;
        fast_out_slow_in = AnimationUtils.loadInterpolator(context, android.R.interpolator.fast_out_slow_in);
        fast_out_linear_in = AnimationUtils.loadInterpolator(context, android.R.interpolator.fast_out_linear_in);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {


        mPaint.setAntiAlias(true);
        mPaint.setColor(0xffffffff);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mPaintWith);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        // 绘制背景
        canvas.drawBitmap(mBgBitmap, 0, 0, mPaint);

        // 画辅助线
        mLinePaint.setAntiAlias(true);
        mLinePaint.setColor(Color.BLACK);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(4);
        mLinePaint.setStrokeCap(Paint.Cap.ROUND);
        mLinePaint.setPathEffect(new DashPathEffect(new float[]{20f, 10f}, 0));
        canvas.drawLine(0, mCenterY, mWidth, mCenterY, mLinePaint);
        canvas.drawLine(mCenterX, 0, mCenterX, mHeight, mLinePaint);

        switch (mAnimState) {
            case state_origin:
                drawCross(canvas, mPaint);
                canvas.drawBitmap(mCircleBitmap, 0, 0, null);
                break;
            case state_rotate:
                mRotateMatrix.setRotate(ROTATE_DEGREE_TOTAL * mRotateDegreeScale, mCenterX, mCenterY);
                canvas.drawBitmap(mCircleBitmap, mRotateMatrix, null);
                drawCross(canvas, mPaint);
                break;
            case state_up:
                mPaint.setStyle(Paint.Style.FILL);
                mPaint.setStrokeWidth(mPointPaintWith);
                float upLen = up_distance * mPointPosScale;
                canvas.drawCircle(mCenterX, mCenterY - upLen, mCircleRadius, mPaint);
                canvas.drawBitmap(mCircleBitmap, 0, 0, null);
                break;
            case state_down:
                mPaint.setStyle(Paint.Style.FILL);
                mPaint.setStrokeWidth(mPointPaintWith);
                float downPointPos = (up_distance + down_distance) * mPointPosScale;
                canvas.drawCircle(mCenterX, mCenterY - up_distance + downPointPos, mCircleRadius, mPaint);
                break;
            case state_finish: // 画 √ ，下移 20 ，夹角40、50
                mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setStrokeWidth(mPaintWith);
                drawFork(canvas, mPaint);
                canvas.drawBitmap(mCircleBitmap, 0, 0, null);
                break;

            default:
                break;
        }

    }

    @Override
    public void setAlpha(int alpha) {
        LogUtils.i(TAG, "setAlpha: alpha = " + alpha);
        mPaint.setAlpha(alpha);
        invalidateSelf();
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

    private void drawFork(Canvas canvas, Paint paint) {
        float x1, y1, x2, y2, x3, y3, x4, y4;
        float cos40 = (float) Math.cos(fork_left_degree);
        float sin40 = (float) Math.sin(fork_left_degree);

        float cos50 = (float) Math.cos(fork_right_degree);
        float sin50 = (float) Math.sin(fork_right_degree);
        x1 = mCenterX - fork_left_len * sin40;
        y1 = mCenterY + down_distance - fork_left_len * cos40;
        x2 = mCenterX;
        y2 = mCenterY + down_distance;

        x3 = mCenterX;
        y3 = mCenterY + down_distance;
        x4 = mCenterX + fork_right_len * sin50;
        y4 = mCenterY + down_distance - fork_right_len * cos50;
        Path path = new Path();
        path.moveTo(x1, y1);
        path.lineTo(x2, y2);
        path.moveTo(x3, y3);
        path.lineTo(x4, y4);
        canvas.drawPath(path, paint);
    }

    private void drawCross(Canvas canvas, Paint paint) {
        float sin45 = (float) Math.sin(cross_degree);
        float length = mCrossLen * mCrossScale * sin45 / 2.0f;
        float x1, y1, x2, y2, x3, y3, x4, y4;
        x1 = mCenterX - length;
        y1 = mCenterY - length;
        x2 = mCenterX + length;
        y2 = mCenterY + length;

        x3 = mCenterX + length;
        y3 = mCenterY - length;
        x4 = mCenterX - length;
        y4 = mCenterY + length;

        Path path = new Path();
        path.moveTo(x1, y1);
        path.lineTo(x2, y2);
        path.moveTo(x3, y3);
        path.lineTo(x4, y4);
        canvas.drawPath(path, paint);
    }

    private ValueAnimator createAnimator(int drawType, long duration, TimeInterpolator interpolator) {
        ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                mAnimState = drawType;
                mPointPosScale = value;
                invalidateSelf();
            }
        });
        animator.setDuration(duration);
        animator.setInterpolator(interpolator);
        return animator;
    }

    public void start() {
        ValueAnimator circleAnim, crossAnim;
        stop();
        circleAnim = ValueAnimator.ofFloat(0.0f, 1.0f);//加载中，旋转动画
        circleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                mAnimState = state_rotate;
                mRotateDegreeScale = value;
                mCrossScale = 1.0f;
                invalidateSelf();
            }
        });
        circleAnim.setDuration(DURATION_ROTATE);
        circleAnim.setInterpolator(fast_out_slow_in);

        crossAnim = ValueAnimator.ofFloat(0.0f, 1.0f);// X 的动画
        crossAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                mAnimState = state_rotate;
                mCrossScale = value;
                invalidateSelf();
            }
        });
        crossAnim.setDuration(DURATION_CROSS);
        crossAnim.setStartDelay(DURATION_CROSS_DELAY);
        crossAnim.setInterpolator(fast_out_linear_in);
        AnimatorSet beginAnimSet = new AnimatorSet();
        beginAnimSet.playTogether(circleAnim, crossAnim);

        ValueAnimator pointUpAnim = createAnimator(state_up, DURATION_POINT_UP, fast_out_slow_in);
        ValueAnimator pointDownAnim = createAnimator(state_down, DURATION_POINT_DOWN, fast_out_slow_in);
        ValueAnimator finishAnim = createAnimator(state_finish, DURATION_FINISH, fast_out_slow_in);
        ValueAnimator delayAnim = ValueAnimator.ofFloat(0, 0);
        delayAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
//                mAnimState = state_origin;
//                invalidateSelf();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        delayAnim.setDuration(DURATION_ORIGIN_DELAY);
        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.playSequentially(beginAnimSet, pointUpAnim, pointDownAnim, finishAnim, delayAnim);
        mAnimatorSet.start();
    }

    public void stop() {
        if (null != mAnimatorSet) {
            mAnimatorSet.cancel();
            mAnimatorSet = null;
        }
        mAnimState = state_origin;
        invalidateSelf();
    }


}
