package com.handsome.frame.android.web;

import android.content.Context;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.handsome.frame.android.utils.log.HDLog;
import com.handsome.frame.android.utils.storage.HDStorageUtil;

import java.util.HashMap;

/**
 * Android 引用js支持
 * Created By shuaizhimin
 * Date:16/1/2
 * Time:下午3:40
 */
public class HSJavaScriptInterface {
    private static final String TAG=HSJavaScriptInterface.class.getSimpleName();
    private WebView mWebView;
    private Context mContext;
    private HashMap<String,String> map;//android 传入参数
    private String jsKey="android";//默认jskey
    private String url;

    public HSJavaScriptInterface(Context context, WebView webView) {
        new HSJavaScriptInterface(context,webView,null,jsKey);
    }
    public HSJavaScriptInterface(Context context, WebView webView, String jsKey) {
        new HSJavaScriptInterface(context,webView,null,jsKey);
    }
    public HSJavaScriptInterface(Context context, WebView webView, HashMap<String,String> map, String jsKey) {
        this.mWebView=webView;
        this.mContext=context;
        this.map=map;
        this.jsKey=jsKey;
        init();
    }
    private void init(){
        if (mWebView == null) {
            HDLog.d(TAG, "webView 初始化失败");
            return;
        }
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(false);
        mWebView.addJavascriptInterface(new HSJavaScriptObject(mContext,mWebView,map),jsKey);
    }

    /**
     * 记载asset目录下的url
     * @param URL
     */
    public void loadAssetUrl(String URL) {
        // 判断文件是否存在
        if (HDStorageUtil.iSExitAssetsFile(URL)) {
            mWebView.loadUrl("file:///android_asset/" + URL);
        } else {
            mWebView.loadUrl("file:///android_asset/" + "html/404.html");
        }
    }

    /**
     * 加载url
     * @param URL
     */
    public void loadUrl(String URL) {
        mWebView.loadUrl(URL);
    }
    public void setWebChromeClient(WebChromeClient chromeClient) {
        mWebView.setWebChromeClient(chromeClient);
    }

}
