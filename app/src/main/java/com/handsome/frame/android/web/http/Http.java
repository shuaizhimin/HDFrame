package com.handsome.frame.android.web.http;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.handsome.frame.android.volley.http.HDRequest;
import com.handsome.frame.android.volley.http.HDRequestListener;
import com.handsome.frame.android.web.BaseFrame;
import com.handsome.frame.android.web.model.HttpParam;

import java.util.HashMap;

/**
 * Created By shuaizhimin
 * Date:16/1/2
 * Time:下午4:29
 */
public class Http extends BaseFrame {
    private Context mContext;
    private HttpParam httpParam;

    public Http(Context mContext, String param) {
        this.mContext = mContext;
        httpParam = (HttpParam) JSON.parseObject(param, HttpParam.class);
    }

    /**
     * js发送post请求
     */
    public void post() {
        HDRequest handsomeRequest = new HDRequest(new HDRequestListener<String>(mContext) {
            @Override
            public void onSuccess(String response) {
                if (!TextUtils.isEmpty(response)) {
                    getmOnJsCallBack().onLoad(httpParam.success, response);
                }
            }
        });
        if (httpParam == null || httpParam.data == null) {
            httpParam.data = new HashMap<String, String>();
        }

        handsomeRequest.doPost(httpParam.url, httpParam.data, false);
    }

    public void get() {
        HDRequest handsomeRequest = new HDRequest(new HDRequestListener<String>(mContext) {
            @Override
            public void onSuccess(String response) {
                if (!TextUtils.isEmpty(response)) {
                    getmOnJsCallBack().onLoad(httpParam.success, response);
                }
            }
        });
        if (httpParam == null || httpParam.data == null) {
            httpParam.data = new HashMap<String, String>();
        }
        handsomeRequest.doGet(httpParam.url, httpParam.data, false);
    }
}
