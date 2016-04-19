package com.handsome.frame.android;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.handsome.frame.android.utils.log.HDLog;
import com.handsome.frame.android.utils.toast.HDToast;
import com.handsome.frame.android.volley.NetroidError;
import com.handsome.frame.android.volley.NetworkError;
import com.handsome.frame.android.volley.NoConnectionError;
import com.handsome.frame.android.volley.ServerError;
import com.handsome.frame.android.volley.TimeoutError;
import com.handsome.frame.android.volley.http.HandleRequestError;

public class HandsomeActivity extends AppCompatActivity {
    private HandleRequestError handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handsome);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

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
