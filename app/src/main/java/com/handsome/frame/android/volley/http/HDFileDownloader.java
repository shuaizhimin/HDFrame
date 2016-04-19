package com.handsome.frame.android.volley.http;

import com.handsome.frame.android.HandsomeApplication;
import com.handsome.frame.android.volley.RequestQueue;
import com.handsome.frame.android.volley.toolbox.FileDownloader;

/**
 * Created with IntelliJ IDEA.
 * User: shuaizhimin
 * Date: 2015/4/10
 * Time: 22:39
 */
public class HDFileDownloader extends FileDownloader {

    private static DownloadController mController;

    public HDFileDownloader(RequestQueue queue, int parallelTaskCount) {
        super(queue, parallelTaskCount);
    }
    public static DownloadController load(String storeFilePath, String url, HDFileDownloaderListener<Void> listener) {
        mController = HandsomeApplication.getFileDownloader().add(storeFilePath, url, listener);
        return mController;
    }
}
