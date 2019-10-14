package com.wjc.jcdemolist.demo.customView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.Nullable;

import com.wjc.jcdemolist.R;
import com.wjc.jcdemolist.Utils.LogUtils;

/**
 * ClassName:com.wjc.jcdemolist.demo.customView
 * Description:
 * JcChen on 2019/9/23 23:30
 */
public class CustomRoundView extends View {
    private static final String TAG = "CustomRoundView";
    private Bitmap mBitmap, mBitmapEx;
    private int width;
    private int height;
    private float mDensity;
    private Paint mPaint;
    private Path mClipPath;

    public CustomRoundView(Context context) {
        super(context);
        init(context);
    }

    public CustomRoundView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomRoundView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mClipPath = new Path();
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon_0);
        width = mBitmap.getWidth();
        height = mBitmap.getHeight();

//        mBitmapEx = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        displayMetrics = context.getResources().getDisplayMetrics();
        mDensity = displayMetrics.density;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float x = 0, y = 0;

//        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
//        mBitmapEx = processRoundBitmap(mBitmap);
//        canvas.drawBitmap(mBitmapEx, x, y, mPaint);

//        processRoundBitmap02(canvas, x, y);
        test(canvas, mBitmap, new RectF(0, 200, 200, 400));
//        canvas.drawColor(Color.RED);
    }

    private void processRoundBitmap02(Canvas canvas, float x, float y) {
        canvas.save();
        mPaint.setAntiAlias(true);
        LogUtils.i(TAG, "onDraw: x=" + x + ",y=" + y + ",width=" + width + ",height=" + height);
        canvas.clipRect(x, y, x + width, y + height);  //用于裁剪画布，也就是设置画布的显示区域
        canvas.drawBitmap(mBitmap, x, y, mPaint);

//        // Rect 与 RectF 区别：http://teachcourse.cn/2268.html
        mClipPath.addOval(new RectF(x, y, x + width, y + width),
                Path.Direction.CCW);//圆，外切矩形

        canvas.clipPath(mClipPath, Region.Op.DIFFERENCE); //是A形状中不同于B的部分显示出来
//        canvas.drawColor(Color.TRANSPARENT);
        canvas.restore();
    }


    private Bitmap processRoundBitmap(Bitmap bitmap) {
        int x, y;
        int radius;
        Bitmap bitmapEx;
        Canvas canvas;
        Paint paint = new Paint();
        // 转换器，控制Paint如何与已有的Canvas图像进行交互
        final PorterDuffXfermode mXfermodeSrcIn = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

        bitmapEx = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmapEx);
        radius = bitmapEx.getWidth() / 2;
        x = bitmapEx.getWidth() / 2 - radius;
        y = bitmapEx.getWidth() / 2 - radius;

//        paint.setColor(getResources().getColor(R.color.color3));
        paint.setAntiAlias(true);
        LogUtils.i(TAG, "processRoundBitmap: x=" + x + ",y=" + y + ",radius=" + radius);
        canvas.drawOval(new RectF(x, y, x + 2 * radius, y + 2 * radius), paint);
        paint.setAntiAlias(false);
        paint.setXfermode(mXfermodeSrcIn);
        canvas.drawBitmap(bitmap, 0, 0, paint);

        return bitmapEx;
    }


    private Bitmap test(Canvas canvas, Bitmap source, RectF rectF) {
//        Canvas canvas = new Canvas();
        canvas.drawColor(Color.RED);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        LogUtils.i(TAG, "test: 11111111111");
        Path path = new Path();
        path.addCircle(rectF.right / 2, rectF.bottom - rectF.height() / 2, 100, Path.Direction.CCW);
        canvas.clipPath(path);
        canvas.drawBitmap(source, 0, 0, paint);
        return source;
    }
}
