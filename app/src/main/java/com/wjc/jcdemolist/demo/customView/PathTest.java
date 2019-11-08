package com.wjc.jcdemolist.demo.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * ClassName:com.wjc.jcdemolist.demo.customView
 * Description:https://github.com/harvic/AndroidCustomCtrlRes/blob/master/34556-%E7%AC%AC1%E7%AB%A0%E5%92%8C%E7%AC%AC11%E7%AB%A0%EF%BC%88%E4%B9%A6%E7%AD%BE%E7%9B%AE%E5%BD%95%EF%BC%89.pdf
 * author:wjc on 2019/11/8 15:57
 */
public class PathTest extends View {
    private Paint radarPaint, valuePaint;
    private float radius;// 网格最大半径
    private int centerX, centerY;
    private int levelCount = 6;
    private int cateCount = 6;
    private float angle = 360 / cateCount;
    private int maxValue = 10;
    private int[] data = {5, 7, 9, 1, 6, 10};
    private int valueCircleRadius = 10;

    public PathTest(Context context) {
        super(context);
        init();
    }

    public PathTest(Context context, @Nullable @android.support.annotation.Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathTest(Context context, @Nullable @android.support.annotation.Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void init() {
        radarPaint = new Paint();
        radarPaint.setStyle(Paint.Style.STROKE);
        radarPaint.setColor(Color.RED);

        valuePaint = new Paint();
        valuePaint.setStyle(Paint.Style.FILL);
        valuePaint.setColor(Color.GREEN);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) { // 当控件大小发生改变时，会回调
        super.onSizeChanged(w, h, oldw, oldh);
        radius = Math.min(w, h) / 2 * 0.9f;
        centerX = w / 2;
        centerY = h / 2;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制蜘蛛网格
        drawPolygon(canvas);
        // 网格中线
        drawLine(canvas);
        // 画数据图
        drawValue(canvas);
    }

    private void drawPolygon(Canvas canvas) {
        Path path = new Path();
        float r = radius / levelCount;
        for (int i = 0; i < levelCount; i++) {
            float curR = r * i;
            path.reset();
            for (int j = 0; j < cateCount; j++) {
                float x = (float) (centerX + curR * Math.cos(angle * j));
                float y = (float) (centerX + curR * Math.sin(angle * j));
                if (j == 0) {
                    path.moveTo(x, y);
                } else {

                    path.lineTo(x, y);
                }
            }
        }
        path.close();
        canvas.drawPath(path, radarPaint);
    }

    private void drawLine(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < cateCount; i++) {
            float x = (float) (centerX + radius * Math.cos(angle * i));
            float y = (float) (centerY + radius * Math.sin(angle * i));
            path.lineTo(x, y);
        }
        canvas.drawPath(path, radarPaint);
    }

    private void drawValue(Canvas canvas) {
        Path path = new Path();
        valuePaint.setAlpha(127);
        for (int i = 0; i < cateCount; i++) {
            float valuePercent = data[i] / maxValue;
            float x = (float) (centerX + radius * Math.cos(angle * i) * valuePercent);
            float y = (float) (centerY + radius * Math.sin(angle * i) * valuePercent);
            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
            canvas.drawCircle(x, y, valueCircleRadius, valuePaint);
        }
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path, valuePaint);
    }
}
