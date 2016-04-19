package com.handsome.frame.android.volley.http;

import android.annotation.TargetApi;
import android.os.Build;

import com.handsome.frame.android.HandsomeApplication;
import com.handsome.frame.android.volley.request.StringRequest;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: shuaizhimin
 * Date: 2015/4/10
 * Time: 22:48
 */
public class HDStringRequest extends StringRequest {

    /**
     *
     * @param method 请求类型，GET or POST or other. See {@link Method}
     * @param url 请求地址
     * @param listener 请求回调
     */
    public HDStringRequest(int method, String url, HDRequestListener<String> listener) {
        super(method, url, listener);
    }

    public HDStringRequest(String url, HDRequestListener<String> listener) {
        this(Method.GET, url, listener);
    }

    /**
     * 将当前请求添加到请求队列等待执行.
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public void start(){
        this.setCacheExpireTime(TimeUnit.MINUTES, HandsomeApplication.FILE_CACHEEXPIRETIME);
        HandsomeApplication.mQueue.add(this);
    }

}
