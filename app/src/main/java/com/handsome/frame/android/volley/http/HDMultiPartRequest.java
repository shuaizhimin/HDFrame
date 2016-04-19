package com.handsome.frame.android.volley.http;

import com.handsome.frame.android.HandsomeApplication;
import com.handsome.frame.android.volley.request.MultiPartStringRequest;

/**
 * Created with IntelliJ IDEA.
 * User: shuaizhimin
 * Date: 2015/4/10
 * Time: 22:49
 */
public class HDMultiPartRequest extends MultiPartStringRequest {
    /**
     *
     * @param method
     * @param url
     * @param listener
     */
    public HDMultiPartRequest(int method, String url, HDRequestListener<String> listener) {
        super(method, url, listener);
    }
    /**
     * 将当前请求添加到请求队列等待执行.
     */
    public void start(){
        HandsomeApplication.mQueue.add(this);
    }
}
