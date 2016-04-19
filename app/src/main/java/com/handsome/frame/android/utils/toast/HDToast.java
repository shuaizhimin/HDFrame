package com.handsome.frame.android.utils.toast;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.handsome.frame.android.HandsomeApplication;

/**
 * Created By shuaizhimin
 * Date:16/4/19
 * Time:下午4:08
 */
public class HDToast {
    private Toast toast;

    /**
     * 防止重复提交
     * @param params
     */
    public void noRepeatShow(Context context, String params) {
        if (!TextUtils.isEmpty(params)) {
            if (toast == null) {
                toast = Toast.makeText(context, params, Toast.LENGTH_SHORT);
            } else {
                toast.setText(params);
            }
            toast.show();
        }

    }

    /**
     * 默认提示
     *
     * @param params
     */
    public static void show(String params) {
        if (!TextUtils.isEmpty(params)) {
            Toast.makeText(HandsomeApplication.mContext, params, Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 提示
     *
     * @param params   提示内容
     * @param duration 提示时间
     */
    public static void show(String params, int duration) {
        if (!TextUtils.isEmpty(params)) {
            Toast.makeText(HandsomeApplication.mContext, params, duration).show();
        }
    }

    /**
     * 提示位置
     *
     * @param params
     * @param duration
     * @param gravity
     */
    public static void show(String params, int duration, int gravity) {
        if (!TextUtils.isEmpty(params)) {
            Toast toast = Toast.makeText(HandsomeApplication.mContext, params, duration);
            toast.setGravity(gravity, 0, 0);
            toast.show();
        }
    }
}
