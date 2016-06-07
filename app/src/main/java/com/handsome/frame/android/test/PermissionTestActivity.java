package com.handsome.frame.android.test;
import android.Manifest;
import android.os.Bundle;
import android.view.View;

import com.handsome.frame.android.R;
import com.handsome.frame.android.base.HDBaseActivity;
import com.handsome.frame.android.permission.PermissionControl;
import com.handsome.frame.android.permission.impl.PermissionImpl;
import com.handsome.frame.android.permission.impl.PermissionRequestCode;
import com.handsome.frame.android.utils.log.HDLog;

public class PermissionTestActivity extends HDBaseActivity {
//    private PermissionControl control;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_permission_test);
//        control=new PermissionControl(this);
//        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                control.requestPermission(new String[]{Manifest.permission.CAMERA}, PermissionRequestCode.CAMERA_REQUEST_C0DE);
//            }
//        });
//        control.setCall(new PermissionImpl() {
//            @Override
//            public void alreadyhasPermission() {
//
//            }
//        });
//    }
//
////    @Override
////    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
////        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
////        HDLog.e(TAG,"onRequestPermissionsResult");
////        control.onRequestPermissionsResult(requestCode,permissions,grantResults);
////    }

}
