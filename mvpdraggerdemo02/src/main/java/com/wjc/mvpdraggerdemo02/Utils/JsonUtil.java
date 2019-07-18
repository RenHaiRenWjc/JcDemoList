package com.wjc.mvpdraggerdemo02.Utils;

import android.text.TextUtils;

/**
 * ClassName:com.wjc.jcdemolist.demo.mvp.mvpDemo02.Utils
 * Description:
 * JcChen on 2019/7/13 22:58
 */
public class JsonUtil {
    /**
     * [{"children":[],"courseId":13,"id":294,"name":"完整项目","order":145000,"parentChapterId":293},
     * {"children":[],"courseId":13,"id":312,"name":"富文本编辑器","order":145041,"parentChapterId":293}]
     * @param jsonStr
     * @return
     */
    public static String formatJson(String jsonStr) {
        if (TextUtils.isEmpty(jsonStr)) return null;
        StringBuilder stringBuilder = new StringBuilder();
        char last = '\0';
        char current = '\0';
        int index = 0;
        for (int i = 0; i < jsonStr.length(); i++) {
            last = current;
            current = jsonStr.charAt(i);
            switch (current) {
                case '{':
                case '[':
                    stringBuilder.append(current);
                    stringBuilder.append('\n');
                    index++;
                    addIndentBlank(stringBuilder, index);
                    break;
                case '}':
                case ']':
                    stringBuilder.append('\n');
                    index--;
                    addIndentBlank(stringBuilder, index);
                    stringBuilder.append(current);
                    break;
                case ',':
                    stringBuilder.append(current);
                    if (last != '\\') {
                        stringBuilder.append('\n');
                        addIndentBlank(stringBuilder, index);
                    }
                    break;
                default:
                    stringBuilder.append(current);
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 添加 space
     *
     * @param stringBuilder
     * @param index
     */
    private static void addIndentBlank(StringBuilder stringBuilder, int index) {
        for (int i = 0; i < index; i++) {
            stringBuilder.append('\t');
        }
    }
}
