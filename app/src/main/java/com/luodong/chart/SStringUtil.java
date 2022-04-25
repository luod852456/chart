package com.luodong.chart;

/**
 * @author luodong
 * @date 2020/12/22 10:49
 * @describe
 */
public class SStringUtil {

    public static String append(String... s) {
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < s.length; i++) {
            sb.append(s[i]);
        }
        return sb.toString();
    }

}
