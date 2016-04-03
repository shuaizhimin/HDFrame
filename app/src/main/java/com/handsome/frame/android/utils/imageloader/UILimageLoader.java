package com.handsome.frame.android.utils.imageloader;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import com.handsome.frame.android.utils.log.HDLog;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * uil imageLoader
 * Created with Android Studio.
 * User: shuaizhimin
 * Date: 2016/3/28
 * Time: 21:22
 */
public class UILImageLoader {
        private final static String TAG = "UILImageLoader";
        private int thread_flag = 0;
        //SoftReference是软引用，是为了更好的为了系统回收变量,内存不够时,自动回收
        private HashMap<String, SoftReference<Drawable>> imageCache;
        private boolean SDCard = false;
        //构造器
        public UILImageLoader() {
            imageCache = new HashMap<String, SoftReference<Drawable>>();
        }
        public UILImageLoader(boolean sdcard) {
            imageCache = new HashMap<String, SoftReference<Drawable>>();
            this.SDCard = sdcard;
        }
    /**
     * 定义接口,用于图片加载完后,怎样处理的回调函数
     * @author yangming
     *
     */
    public interface ImageCallback {
        public void imageLoaded(Drawable imageDrawable, String imageUrl );
    }

    /** 不需要创建新的线程，从网络获得图片直接调用该方法即可.
     * @param imagePath    需要加载图片的URL
     * @param imageView    需要显示图片的控件
     * @param defaultIco   默认显示的图片id
     * @param urlIsNull    URL为空显示的图片id
     * @param loadFail     加载失败显示的图片id
     */
    public static  void  displayImage(String imagePath, ImageView imageView, int defaultIco, int urlIsNull, int loadFail){
        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder()
                .showStubImage(defaultIco)//设置默认图片
                .showImageForEmptyUri(urlIsNull)//设置uri为空的图片
                .showImageOnFail(loadFail)//设置加载失败图片
                .cacheInMemory()// 加载的图片将会被缓存到内存中
                .cacheOnDisc()//加载的图片将会被缓存到磁盘
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片质量
                .build();//实例化displayImageOption

        imageLoader.displayImage(imagePath, imageView, displayImageOptions);
    }
    /** 不需要创建新的线程，从网络获得图片直接调用该方法即可.
     * @param imagePath    需要加载图片的URL
     * @param imageView    需要显示图片的控件
     * @param defaultIco   默认显示的图片id
     * @param urlIsNull    URL为空显示的图片id
     * @param loadFail     加载失败显示的图片id
     */
    public static  void  displayImage(String imagePath,ImageView imageView, int defaultIco,int urlIsNull,int loadFail,Bitmap.Config config){
        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder()
                .showStubImage(defaultIco)//设置默认图片
                .showImageForEmptyUri(urlIsNull)//设置uri为空的图片
                .showImageOnFail(loadFail)//设置加载失败图片
                .cacheInMemory()// 加载的图片将会被缓存到内存中
                .cacheOnDisc()//加载的图片将会被缓存到磁盘
                .bitmapConfig(config)//设置图片质量
                .build();//实例化displayImageOption

        imageLoader.displayImage(imagePath, imageView, displayImageOptions);
    }
    /**
     * 是否使用缓存
     * @param imagePath
     * @param imageView
     * @param isCache
     * @param defaultIco
     * @param urlIsNull
     * @param loadFail
     */
    public static  void  displayImage(String imagePath,ImageView imageView,boolean isCache, int defaultIco,int urlIsNull,int loadFail){
        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions.Builder builder=new DisplayImageOptions.Builder();
        builder.showStubImage(defaultIco)//设置默认图片
                .showImageForEmptyUri(urlIsNull)//设置uri为空的图片
                .showImageOnFail(loadFail)//设置加载失败图片
                .bitmapConfig(Bitmap.Config.RGB_565);//设置图片质量
        if(isCache) {
            builder.cacheInMemory();
            builder.cacheOnDisc();//加载的图片将会被缓存到磁盘
        }
        DisplayImageOptions displayImageOptions=builder.build();
        imageLoader.displayImage(imagePath, imageView, displayImageOptions);
    }
    public static  void  displayImage(String imagePath,ImageView imageView,boolean isCache, int defaultIco,int urlIsNull,int loadFail,Bitmap.Config config){
        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions.Builder builder=new DisplayImageOptions.Builder();
        builder.showStubImage(defaultIco)//设置默认图片
                .showImageForEmptyUri(urlIsNull)//设置uri为空的图片
                .showImageOnFail(loadFail)//设置加载失败图片
                .bitmapConfig(config);//设置图片质量
        if(isCache) {
            builder.cacheInMemory();
            builder.cacheOnDisc();//加载的图片将会被缓存到磁盘
        }
        DisplayImageOptions displayImageOptions=builder.build();
        imageLoader.displayImage(imagePath, imageView, displayImageOptions);
    }
}
