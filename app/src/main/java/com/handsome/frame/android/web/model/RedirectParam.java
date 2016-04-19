package com.handsome.frame.android.web.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 跳转model
 * Created By shuaizhimin
 * Date:16/1/2
 * Time:下午5:18
 */
public class RedirectParam {
    /**
     * 跳转目的，需要告知前端人员
     */
    public String action;
    /**
     * 跳转标记，需要统一
     */
    public int flag;
    /**
     * 跳转携带参数
     */
    public HashMap<String, Serializable> data;


}
