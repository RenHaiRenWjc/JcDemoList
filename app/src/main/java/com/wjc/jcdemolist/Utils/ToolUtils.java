package com.wjc.jcdemolist.Utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

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
}
