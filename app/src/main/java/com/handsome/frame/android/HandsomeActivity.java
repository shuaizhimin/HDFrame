package com.handsome.frame.android;

import android.app.Activity;
import android.os.Bundle;
import com.android.debug.hv.ViewServer;
import com.handsome.frame.android.base.HDBaseActivity;
import com.handsome.frame.android.utils.log.HDLog;
import com.handsome.frame.android.utils.toast.HDToast;
import com.handsome.frame.android.volley.NetroidError;
import com.handsome.frame.android.volley.NetworkError;
import com.handsome.frame.android.volley.NoConnectionError;
import com.handsome.frame.android.volley.ServerError;
import com.handsome.frame.android.volley.TimeoutError;
import com.handsome.frame.android.volley.http.HandleRequestError;
import com.squareup.leakcanary.RefWatcher;

public class HandsomeActivity extends HDBaseActivity {
    private HandleRequestError handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handsome);
        if(HandsomeApplication.DEBUG){
            /**add ViewServer**/
            ViewServer.get(this).addWindow(this);
            /**add LeakCanary refwatcher**/
            RefWatcher refWatcher = HandsomeApplication.getRefWatcher(this);
            refWatcher.watch(this);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(HandsomeApplication.DEBUG){
            /**remove ViewServer**/
            ViewServer.get(this).removeWindow(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(HandsomeApplication.DEBUG) {
            /**foucus ViewServer**/
            ViewServer.get(this).setFocusedWindow(this);
        }
    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }

    public void handleRequestError(NetroidError error) {
        HDLog.e("handleRequestError:","handleRequestError:"+error);
        if(error instanceof TimeoutError){
            if(handler!=null){
                handler.timeOutError();
            }else{
                new HDToast().noRepeatShow(this,"连接超时");
            }
        }else if(error instanceof NoConnectionError){
            if(handler!=null){
                handler.notConnetedError();
            }else{
                new HDToast().noRepeatShow(this,"连接超时");
            }
        }else if(error instanceof ServerError){
            if(handler!=null){
                handler.serverError();
            }else{
                new HDToast().noRepeatShow(this,"连接超时");
            }
        }else if(error instanceof NetworkError){
            if(handler!=null){
                handler.networkError();
            }else{
                new HDToast().noRepeatShow(this,"连接超时");
            }
        }else{
            if(handler!=null){
                handler.otherError();
            }else{
                new HDToast().noRepeatShow(this,"连接超时");
            }
        }
    }

    public void setHandleRequestError(HandleRequestError handler){
        this.handler = handler;
    }

}
