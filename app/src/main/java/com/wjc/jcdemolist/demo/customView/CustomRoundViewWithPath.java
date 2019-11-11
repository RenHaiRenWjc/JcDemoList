package com.wjc.jcdemolist.demo.customView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.wjc.jcdemolist.R;

import androidx.annotation.Nullable;

/**
 * ClassName:com.wjc.jcdemolist.demo.customView
 * Description: 剪裁圆形图形 path、clipPath
 * JcChen on 2019/9/23 23:30
 */
public class CustomRoundViewWithPath extends View {
    private static final String TAG = "CustomRoundView";
    private Bitmap mBitmap;
    private int width;
    private int height;
    private Paint mPaint;
    private Path mClipPath;

    public CustomRoundViewWithPath(Context context) {
        super(context);
        init(context);
    }

    public CustomRoundViewWithPath(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomRoundViewWithPath(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setLayerType(LAYER_TYPE_SOFTWARE, null); // clipPath 不支持硬件加速
        mPaint = new Paint();
        mClipPath = new Path();
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon_0);
        width = mBitmap.getWidth();
        height = mBitmap.getHeight();
        mClipPath.addCircle(width / 2, height / 2, Math.min(width, height) / 2, Path.Direction.CCW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.clipPath(mClipPath);
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);

    }

}
