package com.handsome.frame.android.volley.http;


import com.handsome.frame.android.volley.AuthFailureError;
import com.handsome.frame.android.volley.Request;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: shuaizhimin
 * Date: 2015/4/10
 * Time: 22:45
 */
public class HDRequest {
    private HDRequestListener<String> mListener;
    /**
     * listener，作为回调，必传，且不能为空
     *
     * @param listener
     */
    public HDRequest(HDRequestListener<String> listener) {
        if (listener == null) {
            listener = new HDRequestListener<String>(null) {

                @Override
                public void onSuccess(String response) {
                }
            };
        }
        this.mListener = listener;
    }



    /**
     * 发送GET请求，默认不使用缓存
     *
     * @param url
     * @param cache
     *            是否使用缓存
     */
    public void doGet(String url, boolean cache) {
        HDStringRequest sr = new HDStringRequest(Request.Method.GET, url, mListener);
        sr.setForceUpdate(!cache);
        sr.start();
    }

    /**
     * 发送GET请求，默认不使用缓存
     *
     * @param url
     * @param cache
     *            是否使用缓存
     */
    public void doGet(String url, HashMap<String, String> params, boolean cache) {

        StringBuilder encodedParams = new StringBuilder();
        if(params!=null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                encodedParams.append(entry.getKey());
                encodedParams.append('=');
                encodedParams.append(entry.getValue());
                encodedParams.append('&');
            }
        }

        HDStringRequest sr = new HDStringRequest(Request.Method.GET, url+"?"+encodedParams.toString(), mListener);
        sr.setForceUpdate(!cache);
        sr.start();
    }

    /**
     * 发送POST请求，默认不使用缓存
     *
     * @param url
     * @param params
     * @param cache
     *            是否使用缓存
     */
    public void doPost(String url, final HashMap<String, String> params,
                       boolean cache) {
        HDStringRequest sr = new HDStringRequest(Request.Method.POST, url, mListener) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        sr.setForceUpdate(!cache);
        sr.start();
    }

    /**
     * 发送POST请求，包括字符串和文件，默认不使用缓存
     *
     * @param url
     * @param params
     * @param files
     * @param cache
     */
    public void doPost(String url, final HashMap<String, String> params,
                       final HashMap<String, File> files, boolean cache) {
        HDMultiPartRequest sr = new HDMultiPartRequest(Request.Method.POST, url,
                mListener) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                return params;
            }

            @Override
            public Map<String, File> getFiles() {
                return files;
            }
        };
        sr.setForceUpdate(!cache);
        sr.start();
    }


}
