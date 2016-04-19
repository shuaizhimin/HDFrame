package com.handsome.frame.android.web.action;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.handsome.frame.android.web.model.RedirectParam;

import java.util.HashMap;
import java.util.Set;

/**
 * Created By shuaizhimin
 * Date:16/1/2
 * Time:下午5:16
 */
public class Redirect {
    private Context mContext;
    private RedirectParam redirectParam;
    private HashMap<String,String> map;

    public Redirect(Context mContext, String param) {
        this.mContext = mContext;
        redirectParam = (RedirectParam) JSON.parseObject(param,
                RedirectParam.class);
    }
    public Redirect(Context mContext, String param, HashMap<String,String> map) {
        this.mContext = mContext;
        redirectParam = (RedirectParam) JSON.parseObject(param,
                RedirectParam.class);
        this.map=map;
    }
    public void startActivityClear() {
        if(!TextUtils.isEmpty(redirectParam.action)){
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setAction(redirectParam.action);
            if(redirectParam.data!=null){
                Set<String> keys = redirectParam.data.keySet();
                for (String string : keys) {
                    intent.putExtra(string, String.valueOf(redirectParam.data.get(string)));
                }
            }
            if(map!=null){
                Set<String> keys = map.keySet();
                for (String string : keys) {
                    intent.putExtra(string, String.valueOf(map.get(string)));
                }
            }
            try {
                mContext.startActivity(intent);
                ((Activity) mContext).finish();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void startActivity() {
        if(!TextUtils.isEmpty(redirectParam.action)){
            Intent intent = new Intent();
            intent.setAction(redirectParam.action);
            if(redirectParam.data!=null){
                Set<String> keys = redirectParam.data.keySet();
                for (String string : keys) {
                    intent.putExtra(string, String.valueOf(redirectParam.data.get(string)));
                }
            }
            intent.setFlags(redirectParam.flag);
            if(map!=null){
                Set<String> keys = map.keySet();
                for (String string : keys) {
                    intent.putExtra(string, String.valueOf(map.get(string)));
                }
            }
            try {
                mContext.startActivity(intent);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
