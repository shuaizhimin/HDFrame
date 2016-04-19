package com.handsome.frame.android.web;

import android.app.Activity;
import android.content.Context;
import android.webkit.WebView;

import com.handsome.frame.android.HandsomeApplication;
import com.handsome.frame.android.web.action.Redirect;
import com.handsome.frame.android.web.http.Http;
import com.handsome.frame.android.web.interf.OnJsCallBack;

import java.util.HashMap;

/**
 * Created By shuaizhimin
 * Date:16/1/2
 * Time:下午4:10
 */
public class HSJavaScriptObject implements OnJsCallBack {
    private Context context;
    private WebView webView;
    private HashMap<String,String> map;//android 传入参数
    public HSJavaScriptObject(Context context, WebView webView, HashMap<String,String> map) {
        this.webView=webView;
        this.context=context;
        this.map=map;
        if (HandsomeApplication.httpPublicMap != null) {
            this.map.putAll(HandsomeApplication.httpPublicMap);
        }
    }
    /**
     * js 回调
     * @param callBackName
     * @param data
     */
    @Override
    public void onLoad(final String callBackName, final String data) {
        ((Activity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("javascript:" + callBackName + "('" + data
                        + "')");
            }
        });
    }

    public void callBack(final String functionName, final String arg) {
        ((Activity) context).runOnUiThread(new Runnable() {

            @Override
            public void run() {
                webView.loadUrl("javascript:" + functionName + "('" + arg
                        + "')");
            }
        });
    }

    /**
     * js 跳转
     * @param param
     */
    public void redirect(String param) {
        Redirect redirect = new Redirect(context, param,map);
        redirect.startActivity();
    }
    /**
     * js startActivity ClearTop
     * @param param
     */
    public void redirectClearTop(String param) {
        Redirect redirect = new Redirect(context, param,map);
        redirect.startActivityClear();
    }

    /**
     * js 发送原生post请求
     * @param param
     */
    public void post(final String param) {
        ((Activity) context).runOnUiThread(new Runnable() {
            public void run() {
                Http http = new Http(context, param);
                http.setOnJsCallBack(HSJavaScriptObject.this);
                http.post();
            }
        });
    }

    /**
     * js 发送原生get请求
     * @param param
     */
    public void get(final String param) {
        ((Activity) context).runOnUiThread(new Runnable() {
            public void run() {
                Http http = new Http(context, param);
                http.setOnJsCallBack(HSJavaScriptObject.this);
                http.get();
            }
        });
    }

}
