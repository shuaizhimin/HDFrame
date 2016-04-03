package com.handsome.frame.android.test;

import com.ailk.dazzle.util.SecurityUtils;
import com.handsome.frame.android.utils.encrypt.HDDesUtil;

/**
 * Created with Android Studio.
 * User: shuaizhimin
 * Date: 2016/3/31
 * Time: 11:36
 */
public class DESDemo {
    public static void main(String args[]) throws Exception {
      //System.out.println(""+HDDesUtil.encode("123456","8db4a013a8b515349c307f1e448ce836"));
      System.out.println(""+ SecurityUtils.encryptByDES("123456","8db4a013a8b515349c307f1e448ce836"));
    }
}
