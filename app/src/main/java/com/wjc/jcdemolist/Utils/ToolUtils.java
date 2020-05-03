package com.wjc.jcdemolist.Utils;

import android.content.res.Resources;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.util.TypedValue;
import android.view.MotionEvent;

/**
 * ClassName:com.wjc.jcdemolist.Utils
 * Description:
 * JcChen on 2019/7/1 22:56
 */
public class ToolUtils {
    /**
     * 添加space
     *
     * @param text   文字
     * @param indent 开始位置
     */
    public static String addIndentBlank(String text, int indent) {
        if (text == null || indent < 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder(text); //单线程下在字符缓冲区进行大量操作
        for (int i = 0; i < indent; i++) {
            sb.append('\t');
        }
        return "" + sb;
    }

    public static void addFragmentToActivity(FragmentManager fragmentManager,
                                             Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    public static int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    public static String fetchEventAction(int action) {
        String result = "";
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                result = "ACTION_DOWN";
                break;
            case MotionEvent.ACTION_UP:
                result = "ACTION_UP";
                break;
            case MotionEvent.ACTION_MOVE:
                result = "ACTION_MOVE";
                break;
            case MotionEvent.ACTION_CANCEL:
                result = "ACTION_CANCEL";
                break;
        }
        return result;
    }
}
