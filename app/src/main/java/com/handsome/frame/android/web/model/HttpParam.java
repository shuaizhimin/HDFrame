package com.handsome.frame.android.web.model;

import java.util.HashMap;

/**
 * Created By shuaizhimin
 * Date:16/1/2
 * Time:下午4:33
 */
public class HttpParam {
    /**
     * 请求相对地址
     */
    public String url;
    /**
     * 请求参数,
     */
    public HashMap<String, String> data;
    /**
     * 成功回调
     */
    public String success;
    /**
     * 失败回调
     */
    public String fail;
}
