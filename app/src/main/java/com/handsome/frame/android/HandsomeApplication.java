package com.handsome.frame.android;

import android.app.Application;
import android.content.Context;

import com.handsome.frame.android.volley.Network;
import com.handsome.frame.android.volley.RequestQueue;
import com.handsome.frame.android.volley.cache.DiskCache;
import com.handsome.frame.android.volley.http.HDFileDownloader;
import com.handsome.frame.android.volley.stack.HurlStack;
import com.handsome.frame.android.volley.toolbox.BasicNetwork;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import org.apache.http.protocol.HTTP;

import java.io.File;
import java.net.HttpCookie;
import java.util.HashMap;

/**
 * Created with Android Studio.
 * User: shuaizhimin
 * Date: 2016/3/27
 * Time: 16:03
 */
public class HandsomeApplication extends Application{
    /**the tag of log**/
    public static boolean OPEN_LOG=true;
    /**cache fileName**/
    public static String FOLDER_NAME = "/hdcache";
    /**app debug**/
    public static boolean DEBUG=false;
    /**remote request url **/
    public static String remoteURL;
    public static Context mContext;
    /**
     * cookie
     */
    private static HashMap<String, HttpCookie> cookies = new HashMap<String, HttpCookie>();
    /**
     * 请求队列，一般不操作此类
     */
    public static RequestQueue mQueue;
    /**
     * 请求线程池大小
     */
    public static int THREADPOOLSIZE=8;

    /**
     * 可自定义缓存目录，默认context.getCacheDir()+"/http"目录
     */
    public static File mCacheFile;
    /**
     * 请求缓存大小，默认10M
     */
    public static int FILECACHESIZE = 10 * 1024 * 1024;
    /**
     * 请求缓存过期时间，单位：分钟
     */
    public static int FILE_CACHEEXPIRETIME=20;

    /**
     * 图片缓存大小，默认10M
     */
    public static int IMAGECACHESIZE = 10 * 1024 * 1024;
    /**
     * 图片缓存过期时间，单位：分钟
     */
    public static int IMAGE_CACHEEXPIRETIME=20;
    /**
     * 下载线程数，最好不超过3
     */
    public static int DOWNTHREADSIZE=3;
    public static HashMap<String,String> httpPublicMap;//公共部分的请求参数
    /**
     * 文件下载工具
     */
    private static HDFileDownloader mDownloader;
    /**LeakCanary watcher**/
    private RefWatcher refWatcher;
    //在自己的Application中添加如下代码
    public static RefWatcher getRefWatcher(Context context) {
        HandsomeApplication application = (HandsomeApplication) context
                .getApplicationContext();
        return application.refWatcher;
    }




    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader(getApplicationContext());
        mContext=getApplicationContext();
        Network network = new BasicNetwork(new HurlStack(), HTTP.UTF_8);
        if(mCacheFile==null){
            mCacheFile = mContext.getCacheDir();
        }
        //设置缓冲池、缓存目录、缓存大小，待确认是否可行
        mQueue = new RequestQueue(network, THREADPOOLSIZE,new DiskCache(mCacheFile, FILECACHESIZE));
        // start and waiting requests.
        mQueue.start();

        refWatcher= LeakCanary.install(this);
    }

    public static String getCookies(){
        StringBuilder sb = new StringBuilder();
        //TODO 目前未判断Cookie的有效期，待确定是否需要判断。若要判断cookie有效期，则需要确定Cookie存放位置：内存 or 硬盘
        for (String string : HandsomeApplication.cookies.keySet()) {
            sb.append(HandsomeApplication.cookies.get(string).getName()+"="+HandsomeApplication.cookies.get(string).getValue()+";");
        }
        if(sb.length()>1){
            return sb.substring(0, sb.toString().length()-1);
        }else{
            return null;
        }
    }
    public static HashMap<String, HttpCookie> getMapCookies(){
        return cookies;
    }

    public static void setCookies(HashMap<String, HttpCookie> c){
        for (String string : c.keySet()) {
            if(HandsomeApplication.cookies.containsKey(string)){
                HandsomeApplication.cookies.remove(string);
            }
            HandsomeApplication.cookies.put(string, c.get(string));
        }
    }

    public static void clearCookies(){
        cookies.clear();
    }


    public static HDFileDownloader getFileDownloader(){
        if(mDownloader==null){
            mDownloader = new HDFileDownloader(HandsomeApplication.mQueue, DOWNTHREADSIZE);
        }
        return mDownloader;
    }


    public void initImageLoader(Context context){
        File cacheDir = StorageUtils.getOwnCacheDirectory(context, FOLDER_NAME);
        //这个配置方法是自定义的，你可以自己调优这个配置，也可以调用默认的配置  在这个方法 ImageLoaderConfiguration.createDefault(this);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPoolSize(3)
                .threadPriority(Thread.NORM_PRIORITY - 2)//线程的优先级数
                .denyCacheImageMultipleSizesInMemory()//默认情况下是允许多个图片存在在缓存中，调用这个方法将否认这一做法。(个人理解，具体看源码)
                .discCache(new UnlimitedDiskCache(cacheDir))
                .discCacheFileNameGenerator(new Md5FileNameGenerator())//用于磁盘缓存，生成文件名
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())//将保存的时候的URI名称用HASHCODE加密
                .tasksProcessingOrder(QueueProcessingType.LIFO)//线程队列的加载方式，LIFO last in first out 后进先出，FIOF first in first out 先进先出
                .build();//构建
        //需要初始化ImageLoader这个配置
        ImageLoader.getInstance().init(config);
    }
}
