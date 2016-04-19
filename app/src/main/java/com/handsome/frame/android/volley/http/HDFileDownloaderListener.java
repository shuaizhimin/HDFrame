package com.handsome.frame.android.volley.http;

import android.app.ProgressDialog;

import com.handsome.frame.android.HandsomeActivity;
import com.handsome.frame.android.HandsomeApplication;
import com.handsome.frame.android.volley.Listener;
import com.handsome.frame.android.volley.NetroidError;

/**
 * Created with IntelliJ IDEA.
 * User: shuaizhimin
 * Date: 2015/4/10
 * Time: 22:44
 */
public abstract class HDFileDownloaderListener<T> extends Listener<T> {

    @Override
    public void onError(NetroidError error) {
        super.onError(error);
        ((HandsomeActivity) HandsomeApplication.mContext).handleRequestError(error);
        if(HandsomeApplication.OPEN_LOG){
            error.printStackTrace();
        }
    }

    private ProgressDialog eDialog;

    @Override
    public void onPreExecute() {
        super.onPreExecute();
        if(eDialog==null){
            eDialog = new ProgressDialog(HandsomeApplication.mContext);
            eDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            eDialog.setCanceledOnTouchOutside(false);
        }
        eDialog.show();
    }
    @Override
    public void onSuccess(T response) {
        if(eDialog.isShowing()){
            eDialog.dismiss();
        }
        eDialog=null;
    }
    @Override
    public void onFinish() {
        super.onFinish();
        if(eDialog!=null){
            eDialog.dismiss();
        }
    }

    @Override
    public void onProgressChange(long fileSize, long downloadedSize) {
        super.onProgressChange(fileSize, downloadedSize);
        eDialog.setMax((int)fileSize);
        eDialog.setProgress((int)downloadedSize);
    }
}
