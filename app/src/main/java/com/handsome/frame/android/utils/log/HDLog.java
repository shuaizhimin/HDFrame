package com.handsome.frame.android.utils.log;

import android.util.Log;

import com.handsome.frame.android.HandsomeApplication;

/**
 * log
 * Created with Android Studio.
 * User: shuaizhimin
 * Date: 2016/3/27
 * Time: 16:03
 */
public class HDLog {
    private final static String TAG = "HandsomeLog";
    /**
     * 采用Log.i()打印日志
     * @param param1
     * 标签
     * @param param2
     * 打印的内容
     */
    public static void i(String param1,String param2) {
        if (HandsomeApplication.OPEN_LOG) {
            if(param1!=null && param2!=null && !param1.equals("") && !param2.equals("")){
                Log.i(param1, param2);
            }else{
                Log.e(TAG, "Param can not be null or\"\"");
            }
        }
    }
    /**
     * 采用Log.d()打印日志
     * @param param1
     * 标签
     * @param param2
     * 打印的内容
     */
    public static void d(String param1,String param2) {
        if (HandsomeApplication.OPEN_LOG) {
            if(param1!=null && param2!=null && !param1.equals("") && !param2.equals("")){
                Log.i(param1, param2);
            }else{
                Log.e(TAG, "Param can not be null or\"\"");
            }
        }
    }
    /**
     * 采用Log.e()打印日志
     * @param param1
     * 标签
     * @param param2
     * 打印的内容
     */
    public static void e(String param1,String param2) {
        if (HandsomeApplication.OPEN_LOG) {
            if(param1!=null && param2!=null && !param1.equals("") && !param2.equals("")){
                Log.e(param1, param2);
            }else{
                Log.e(TAG, "Param can not be null or\"\"");
            }
        }
    }
    /**
     * 采用Log.v()打印日志
     * @param param1
     * 标签
     * @param param2
     * 打印的内容
     */
    public static void v(String param1,String param2) {
        if (HandsomeApplication.OPEN_LOG) {
            if(param1!=null && param2!=null && !param1.equals("") && !param2.equals("")){
                Log.v(param1, param2);
            }else{
                Log.e(TAG, "Param can not be null or\"\"");
            }
        }
    }
    /**
     * 采用Log.w()打印日志
     * @param param1
     * 标签
     * @param param2
     * 打印的内容
     */
    public static void w(String param1,String param2){
        if (HandsomeApplication.OPEN_LOG) {
            if(param1!=null && param2!=null && !param1.equals("") && !param2.equals("")){
                Log.w(param1, param2);
            }else{
                Log.e(TAG, "Param can not be null or\"\"");
            }
        }
    }
}
