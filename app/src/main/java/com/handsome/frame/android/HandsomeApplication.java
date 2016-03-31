package com.handsome.frame.android;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created with Android Studio.
 * User: shuaizhimin
 * Date: 2016/3/27
 * Time: 16:03
 */
public class HandsomeApplication extends Application{
    public static boolean OPEN_LOG=true;
    public static String FOLDER_NAME = "/hdcache";

    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader(getApplicationContext());
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
