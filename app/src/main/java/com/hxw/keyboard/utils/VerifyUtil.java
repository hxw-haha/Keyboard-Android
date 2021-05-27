package com.hxw.keyboard.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>文件描述：<p>
 * <p>作者：hanxw<p>
 * <p>创建时间：2020/9/26<p>
 * <p>更改时间：2020/9/26<p>
 * <p>版本号：1<p>
 */
public final class VerifyUtil {

    /**
     * 手机号码格式
     */
    private static final String REGEX_PHONE =
            "^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$";

    /**
     * 判断手机格式是否正确
     *
     * @param phone
     * @return
     */
    public static boolean isPhone(String phone) {
        if (TextUtils.isEmpty(phone)) {
            return true;
        }
        Pattern p = Pattern.compile(REGEX_PHONE);
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    /**
     * 定义判别用户身份证号的正则表达式 18 位
     */
    private static final String REGEX_ID_NUMBER_18 =
            "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
    /**
     * 定义判别用户身份证号的正则表达式 15位
     */
    private static final String REGEX_ID_NUMBER_15 =
            "^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}[0-9Xx]$";

    /**
     * 判断身份证号码格式是否正确
     *
     * @param idNo
     * @return
     */
    public static boolean isIDNo(String idNo) {
        if (TextUtils.isEmpty(idNo)) {
            return true;
        }
        int idNoLength = idNo.length();
        if (idNoLength == 18 || idNoLength == 15) {
            Pattern p = Pattern.compile(idNoLength == 18 ? REGEX_ID_NUMBER_18 : REGEX_ID_NUMBER_15);
            Matcher m = p.matcher(idNo);
            return m.matches();
        }
        return false;
    }

    /**
     * 判断是否输入汉字
     *
     * @return true：是
     */
    public static boolean isCheckCh(String s) {
        if (TextUtils.isEmpty(s)) {
            return false;
        }
        try {
            return Pattern.matches("[\u4E00-\u9FA5]", s);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
