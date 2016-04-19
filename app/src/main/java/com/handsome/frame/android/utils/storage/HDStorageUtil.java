package com.handsome.frame.android.utils.storage;

import android.content.res.AssetManager;
import android.os.Environment;

import com.handsome.frame.android.HandsomeApplication;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created By shuaizhimin
 * Date:16/4/19
 * Time:下午4:16
 */
public class HDStorageUtil {
    /**
     * 判断Asset文件是否存在
     * @param fileName
     * @return
     */
    public static boolean iSExitAssetsFile(String fileName) {
        AssetManager manager = HandsomeApplication.mContext.getAssets();
        InputStream ins = null;
        try {
            ins = manager.open(fileName);
            byte[] buffer = new byte[1024];
            if (ins.read(buffer) > 0) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }


    // 是否有SDCARD
    public static  boolean hasSDCard() {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }
}
