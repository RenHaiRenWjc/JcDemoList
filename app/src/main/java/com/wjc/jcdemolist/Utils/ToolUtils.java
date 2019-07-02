package com.wjc.jcdemolist.Utils;

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
}
