package com.handsome.frame.android.volley.http;

import android.content.Context;

import com.handsome.frame.android.HandsomeActivity;
import com.handsome.frame.android.utils.log.HDLog;
import com.handsome.frame.android.volley.Listener;
import com.handsome.frame.android.volley.NetroidError;

/**
 * Created with IntelliJ IDEA.
 * User: shuaizhimin
 * Date: 2015/4/10
 * Time: 22:46
 */
public abstract class HDRequestListener<T> extends Listener<T> {

    private Context mContext;
    public HDRequestListener(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onError(NetroidError error) {
        super.onError(error);
        if(error!=null)
            HDLog.e("NetroidError:",""+error);
        if(mContext==null) return;
        ((HandsomeActivity)mContext).handleRequestError(error);
    }
    @Override
    public void onPreExecute() {
        super.onPreExecute();
        if(mContext==null)return;
    }

    @Override
    public void onFinish() {
        super.onFinish();
        if(mContext==null)return;
    }

}
