package com.handsome.frame.android.utils.encrypt;

import java.security.MessageDigest;

/**
 * MD5加密
 * Created with Android Studio.
 * User: shuaizhimin
 * Date: 2016/3/26
 * Time: 18:33
 */
public class HDMD5Util {
    /**
     * 获取MD5字符串
     * @param s
     * @return
     */
    public  static String getMD5(String s) {
        try {
            byte[] btInput = s.getBytes("utf-8");
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            return byteArray2HexStr(md);
        } catch (Exception e) {
            return null;
        }
    }

    private static String byteArray2HexStr(byte[] data) {
        char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        int l = data.length;
        char[] out = new char[l << 1];
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = digits[(0xF0 & data[i]) >>> 4];
            out[j++] = digits[0x0F & data[i]];
        }
        return new String(out);
    }
}
