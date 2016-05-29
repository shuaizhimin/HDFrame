package com.handsome.frame.android.permission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import com.handsome.frame.android.utils.log.HDLog;

/**
 * Created with Andrid Studio.
 * User:shuaizhimin
 * Date:16/4/11
 * Time:上午11:11
 */

public class PermissionUtil {
    public static String TAG="PermissionUtil";
    public static boolean hasPermissions(Context context, String... permissions) {
        HDLog.e(TAG,""+Build.VERSION.SDK_INT);
        if (Build.VERSION.SDK_INT >=23) {
            for (String permission : permissions) {
                if (!hasSelfPermission(context, permission)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean hasSelfPermission(Context context, String permission) {
//        try {
//            return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
//        } catch (RuntimeException t) {
//            return false;
//        }
        return false;
    }

    /**
     * access permissions
     * @param grantResults
     * @return
     */
    public static boolean verifyPermissions(int... grantResults) {
        if (grantResults.length == 0) {
            return false;
        }
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public static void requestPermission(Activity activity, String[] permissions, int requestCode) {
        //ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }

    /**
     * 检测是否需要权限
     * @param activity
     * @param permissions
     * @return
     */
    public static boolean shouldShowRequestPermissionRationale(Activity activity, String... permissions) {
//        for (String permission : permissions) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
//                return true;
//            }
//        }
        return false;
    }

}
