package com.wjc.jcdemolist.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.wjc.jcdemolist.JcApplication;
import com.wjc.jcdemolist.R;

import java.util.Hashtable;

import static android.content.ContentValues.TAG;

/**
 * ClassName: com.hncx.xxm.utils.sys
 * Description:
 * JcChen on 2020.06.08.9:47 PM
 */
public class QrcodeUtils {
	private static final String TAG = "QrcodeUtils";
	public static Bitmap qrcode(String content,int size) {
		Hashtable<EncodeHintType, String> hints = new Hashtable<>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); // 字符转码格式设置
		hints.put(EncodeHintType.ERROR_CORRECTION, "H"); // 容错级别设置 默认为L
		hints.put(EncodeHintType.MARGIN, "2"); // 空白边距设置
		BitMatrix bitMatrix = null;
		try {
			bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, size, size, hints);
		} catch (WriterException e) {
			e.printStackTrace();
		}

		int[] pixels = new int[size * size];
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				if (bitMatrix.get(x, y)) { // 黑色色块像素设置
					pixels[y * size + x] = 0xff000000;
				} else { // 白色色块像素设置
					pixels[y * size + x] = 0xffffffff;
				}
			}
		}

		Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
		bitmap.setPixels(pixels, 0, size, 0, 0, size, size);

		float logoPercent = 0.5f;//logo图片在原图片中的显示大小比例，取值0-1，建议使用0.2,百分比过大可能导致二维码扫描失败
		/*获取原图片和Logo图片各自的宽、高值 */
		int srcWidth = bitmap.getWidth();
		int srcHeight = bitmap.getHeight();
		int logoWidth = bitmap.getWidth();
		int logoHeight = bitmap.getHeight();
		/*计算画布缩放的宽高比 */
		float scaleWidth = srcWidth * logoPercent / logoWidth;
		float scaleHeight = srcHeight * logoPercent / logoHeight;
		/* 使用Canvas绘制,合成图片 */
		Bitmap addLogBitmap = Bitmap.createBitmap(srcWidth, srcHeight, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(addLogBitmap);
		canvas.drawBitmap(bitmap, 0, 0, null);
		canvas.scale(scaleWidth, scaleHeight, srcWidth/2, srcHeight/2);
		Bitmap logoBitmap = BitmapFactory.decodeResource(JcApplication.getContext().getResources(),R.mipmap.ic_launcher);
		if (logoBitmap.isRecycled()){
			LogTools.i(TAG, ": xxxxx");
			return null;
		}
		canvas.drawBitmap(logoBitmap, srcWidth/2 - logoWidth/2, srcHeight/2 - logoHeight/2, null);
		return addLogBitmap;
	}
}
