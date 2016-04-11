package com.handsome.frame.android.permission;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.handsome.frame.android.R;
import com.handsome.frame.android.permission.impl.PermissionImpl;
import com.handsome.frame.android.permission.impl.PermissionRequestCode;
import com.handsome.frame.android.utils.log.HDLog;

/**
 * Created with Andrid Studio.
 * User:shuaizhimin
 * Date:16/4/11
 * Time:下午1:48
 */
public class PermissionControl {
    public final String TAG="PermissionControl";
    private Activity activity;
    private Fragment fragment;
    private PermissionImpl call;

    public PermissionControl(Activity activity) {
        this.activity = activity;
    }

    public PermissionControl(Fragment fragment) {
        this.fragment = fragment;
    }


    public void requestPermission(String[] permissions, int requestCode) {
        if (PermissionUtil.hasPermissions(activity, permissions)) {
            HDLog.e(TAG,"已经开启权限");
            if(call!=null) {
                call.alreadyhasPermission();
            }
        } else {
            HDLog.e(TAG,"未开启权限");
            if (PermissionUtil.shouldShowRequestPermissionRationale(activity, permissions)) {
                HDLog.e(TAG,"需要开启权限");
            }
            PermissionUtil.requestPermission(activity, permissions, requestCode);
        }

    }


    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults) {
        switch (requestCode){
            case PermissionRequestCode.CAMERA_REQUEST_C0DE:
                HDLog.e(TAG,"onRequestPermissionsResult:camera_request");
                if(PermissionUtil.verifyPermissions(grantResults)){
                    HDLog.e(TAG,"onRequestPermissionsResult:camera_request success");
                    if(call!=null){
                        call.alreadyhasPermission();
                    }
                }
                break;
        }
    }


    public PermissionImpl getCall() {
        return call;
    }

    public void setCall(PermissionImpl call) {
        this.call = call;
    }


}
