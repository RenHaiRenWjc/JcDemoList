package com.wjc.jcdemolist.demo.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.appcompat.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.wjc.jcdemolist.R;
import com.wjc.jcdemolist.Utils.LogUtils;
import com.wjc.jcdemolist.Utils.ToolUtils;

/**
 * ClassName:com.wjc.jcdemolist.demo.customView
 * Description:
 * JcChen on 2019/6/29 8:16
 */
public class CustomMarqueeView extends AppCompatTextView {
    private static final String TAG = "CustomMarqueeView";

    private Paint mTextPaint;
    private String mTitleText;//显示标题
    private String mTitleWithSpace;
    private String mDrawingText;//滚动的文字
    private int mWidth, mHeight;
    private float mTextWidth;


    private int mTitleType = TYPE_IDLE;//标题类型
    private static final int TYPE_IDLE = -1;
    private static final int TYPE_NO_SCROLL = 0; //标题在控件范围内，标题不用滚动
    private static final int TYPE_SCROLL = 1; //标题超出控件，滚动

    private boolean isRunning = true;

    private float mMaxScroll;//向左侧滑动的最大距离
    private char[] mTitleCharList;//标题数组
    private int startIndex; //右边空间填充字数开始位置
    private float mTextDefalutXShaft;//文字的默认x轴坐标

    // View 属性
    private float mSpeed;//标题滚动速度
    private long mPauseTime;//当标题滚动回来到最开始位置，停留时长
    private boolean mCenterNoScroll;//当标题能全部在控件上显示，而不用滚动时，标题是否居中
    private float mMarqueeTextSize;
    private int mTextColor;
    private int mSpaceCount;
    private Paint otherPaint;


    public CustomMarqueeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyStyle(context, attrs);
        initPaint();
    }

    public CustomMarqueeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyStyle(context, attrs);
        initPaint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        LogUtils.i(TAG, "-------------onSizeChanged: ------------");
        initMarqueeText();
    }

    private void applyStyle(Context context, AttributeSet attributeSet) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.CustomMarqueeView);
        mMarqueeTextSize = typedArray.getDimension(R.styleable.CustomMarqueeView_text_size, 18);
        mPauseTime = typedArray.getInteger(R.styleable.CustomMarqueeView_text_pause_time, 3000);
        mTextColor = typedArray.getInteger(R.styleable.CustomMarqueeView_text_color, Color.BLACK);
        mSpeed = typedArray.getInteger(R.styleable.CustomMarqueeView_text_speed, 1);
        mCenterNoScroll = typedArray.getBoolean(R.styleable.CustomMarqueeView_text_no_scroll_center, false);
        mSpaceCount = typedArray.getInteger(R.styleable.CustomMarqueeView_text_space_count, 5);
        typedArray.recycle();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawMarqueeText(canvas);
        mTextPaint.setColor(getResources().getColor(R.color.color1));
        canvas.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2, mTextPaint);

        otherPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        otherPaint.setStrokeWidth(20);
        otherPaint.setColor(getResources().getColor(R.color.colorAccent));
    }

    private void drawText(Canvas canvas, float x, float y) {
        Rect bounds = new Rect();
        mTextPaint.getTextBounds(mDrawingText, 0, mDrawingText.length(), bounds);// 位置有可能改变
        float offSet = (bounds.top + bounds.bottom) / 2; // 基准线偏移量
        canvas.drawText(mDrawingText, x, y - offSet, mTextPaint);

//        Paint.FontMetrics fontMetrics = new Paint.FontMetrics();
//        mTextPaint.getFontMetrics(fontMetrics);
//        float offset = (fontMetrics.ascent + fontMetrics.descent) / 2;
//        canvas.drawText(mDrawingText, x, y - offset, mTextPaint);
    }

    private void initPaint() {
        if (mTextPaint == null) {
            mTextPaint = new Paint();
            mTextPaint.setAntiAlias(true);
            mTextPaint.setDither(true);
            mTextPaint.setStrokeWidth(4);
            mTextPaint.setAlpha(200);
            mTextPaint.setColor(mTextColor);
            mTextPaint.setTextSize(mMarqueeTextSize);
        }

        otherPaint = new Paint();
    }

    private void initMarqueeText() {
        if (!TextUtils.isEmpty(mTitleText)) {
            initPaint();
            //重置相关属性
            mWidth = getWidth();
            mHeight = getHeight() - getPaddingTop() - getPaddingBottom();
//            mTextHeight = measureTextHeight();
            mTextWidth = measureTextWidth(String.valueOf(mTitleText));
            //文字滚动判断
            if (mTextWidth > mWidth) {
                mTitleType = TYPE_SCROLL;
                mTitleWithSpace = ToolUtils.addIndentBlank(mTitleText, mSpaceCount);
                mDrawingText = mTitleWithSpace;
                mTitleCharList = mDrawingText.toCharArray();
                mMaxScroll = mTextWidth + mSpaceCount * oneSpaceWidth();
                mTextPaint.setTextAlign(Paint.Align.LEFT);
                startIndex = 0;
            } else {
                //不滚动
                mDrawingText = mTitleText;
                mTitleType = TYPE_NO_SCROLL;
            }
            mTextDefalutXShaft = 0;
        }
    }


    private void drawMarqueeText(Canvas canvas) {
        if (!isRunning) {
            LogUtils.i(TAG, "drawMarqueeText: pause");
            if (isShown()) {
                drawText(canvas, 0, mHeight / 2);  //从坐标0开始绘制
            }
            return;
        }
        switch (mTitleType) {
            case TYPE_NO_SCROLL:
                LogUtils.i(TAG, "跑马灯标题不滚动");
                if (mDrawingText != null && !mDrawingText.equals("")) {
                    if (mCenterNoScroll) {
                        mTextPaint.setTextAlign(Paint.Align.CENTER);
//                        canvas.drawText(mDrawingText, mWidth / 2, mHeight / 2, mTextPaint);
                        drawText(canvas, mWidth / 2, mHeight / 2);
                    } else {
                        mTextPaint.setTextAlign(Paint.Align.LEFT);
//                        canvas.drawText(mDrawingText, 0, mHeight / 2, mTextPaint);
                        drawText(canvas, 0, mHeight / 2);
                    }
                }
                break;
            case TYPE_SCROLL:
                if (!TextUtils.isEmpty(mDrawingText)) {
                    if (isShown()) {
//                        canvas.drawText(mDrawingText, mTextDefalutXShaft, mHeight / 2, mTextPaint);//从坐标0开始绘制
                        drawText(canvas, mTextDefalutXShaft, mHeight / 2);
                        mTextDefalutXShaft = mTextDefalutXShaft - mSpeed;
                    }
                    if (mMaxScroll < Math.abs(mTextDefalutXShaft)) {//当达到条件后，暂停一下,然后从0开始绘制
                        mDrawingText = mTitleWithSpace;
                        mTextDefalutXShaft = 0;
                        startIndex = 0;
                        postInvalidateDelayed(mPauseTime);
                    } else {
                        // 控件总宽度 - 可见文字宽度（文字宽度-文字向x轴原点左边滑动距离）= 右边剩余无文字的宽度
                        int rightTitleCount = (int) ((mWidth - (mTextWidth + mTextDefalutXShaft)) / oneSpaceWidth());//右侧的剩余空间可以填充的字数
                        if (rightTitleCount < 0) {
                            postInvalidateDelayed(30);
                            return;
                        }
                        for (int i = startIndex; i < rightTitleCount - mSpaceCount; i++) {
                            if (i < mTitleCharList.length) {//拿titleText中的文字
                                mDrawingText = mDrawingText + mTitleCharList[i];
                                startIndex++;
                            }
                        }
                        postInvalidateDelayed(30);
                    }

                } else {
                    LogUtils.d(TAG, "drawMarqueeText mMarqueeText是空的");
                }
                break;
        }
    }

    //设置滚动的文字
    public void setScrollText(String text) {
        if (TextUtils.isEmpty(text) || text.equals(mTitleText)) {
            return;
        }
        mTitleText = text;
        boolean isScroll = mTitleType == TYPE_SCROLL;
        if (mTitleType != TYPE_IDLE) {//idle 状态 onSizeChanged 去初始化
            initMarqueeText();
        }
        if (isRunning && !isScroll) {//不是需要暂停并且没有滚动的情况下，调用
            postInvalidate();
        }
    }

    //暂停滚动
    public void stopScroll() {
        isRunning = false;
    }

    //继续滚动
    public void startScroll() {
        if (!isRunning) {
            isRunning = true;
            postInvalidate();
        }
    }

    //测量文字宽度
    private float measureTextWidth(String text) {
        return mTextPaint.measureText(text, 0, text.length());
    }

    //获取文字的高度
    private float measureTextHeight() {
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        return Math.abs((fontMetrics.bottom - fontMetrics.top)) / 2;
    }

    //测量一个空格的宽度
    private float oneSpaceWidth() {
        String arg0 = "en en";
        String arg1 = "enen";
        return (measureTextWidth(arg0) - measureTextWidth(arg1));
    }
}

