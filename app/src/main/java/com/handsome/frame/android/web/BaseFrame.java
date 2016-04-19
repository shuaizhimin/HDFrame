package com.handsome.frame.android.web;

import com.handsome.frame.android.web.interf.OnJsCallBack;

/**
 * Created By shuaizhimin
 * Date:16/1/2
 * Time:下午4:30
 */
public class BaseFrame {
    private OnJsCallBack mOnJsCallBack;

    public void setOnJsCallBack(OnJsCallBack onJsCallBack) {
        this.mOnJsCallBack = onJsCallBack;
    }

    public OnJsCallBack getmOnJsCallBack() {
        return mOnJsCallBack;
    }
}
