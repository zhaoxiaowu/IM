package com.zxw.ui.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AutoSizeTool {

    public static double getWidth(String msg) {
        int len = msg.length();
        double width = 0;
        for (int i = 0; i < len; i++) {
            if (isChinese(msg.charAt(i))) {
                width += 16;
            } else {
                width += 16;
            }
        }

        width += 22; // 补全前后空格

        if (width > 450) {
            return 450;
        }

        return width < 50 ? 50 : width;
    }

    public static double getHeight(String msg) {
        //判断包含多少换行
        System.out.println("msg" + msg);
        Pattern p = Pattern.compile("\r|\n");
        int countLineFeed = 0;
        Matcher m = p.matcher(msg);
        while (m.find()) {
            countLineFeed++;
        }

        int len = msg.length();
        double width = 0;
        for (int i = 0; i < len; i++) {
            if (isChinese(msg.charAt(i))) {
                width += 16;
            } else {
                width += 16;
            }
        }

        width += 22; // 补全前后空格

        double remainder = width % 450;
        int line = (int) (width / 450);

        if (remainder != 0) {
            line = line + 1;
        }

        double autoHeight = (line + countLineFeed) * 24 + 10;

        return autoHeight < 30 ? 30 : autoHeight;

    }

    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }
}
