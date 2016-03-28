package com.handsome.frame.android.utils.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Bitmap处理类
 * Created with Android Studio.
 * User: shuaizhimin
 * Date: 2016/3/26
 * Time: 17:54
 */
public class HDBitmapUtil {
    /**
     * Bitmap 转换成byte[]
     * @param bm 图片
     * @param quality 质量
     * @return
     */
    public static byte[] bitmap2Byte(Bitmap bm, int quality) {
        if (bm == null)
            return null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, quality, baos);
        return baos.toByteArray();
    }

    /**
     * byte[] 转换成Bitmap
     * @param b  byte[]
     * @return
     */
    public static Bitmap bytes2Bimap(byte[] b) {
        if (b == null || b.length == 0)
            return null;
        try {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取拍照的图片
     *
     * @param filePath 图片所在路径
     * @param width    宽
     * @param height   高
     * @return
     */
    public static Bitmap decodeCameraImage(String filePath, int width, int height) {
        BitmapFactory.Options bfOptions = new BitmapFactory.Options();
        bfOptions.inJustDecodeBounds = true;//decodeFile 不分配空间，不占用内存
        BitmapFactory.decodeFile(filePath, bfOptions);
        //insampleSize(采样率) 效率高 ,解析速度快，安卓源码提供的computeSampleSize,计算方法
        bfOptions.inSampleSize = computeSampleSize(bfOptions, -1, width * height);
        bfOptions.inJustDecodeBounds = false;
        bfOptions.inDither = false;
        bfOptions.inPurgeable = true;
        bfOptions.inInputShareable = true;
        bfOptions.inTempStorage = new byte[12 * 1024];
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(filePath);
            return rotaingImage(readPictureDegree(filePath),
                    BitmapFactory.decodeFileDescriptor(fs.getFD(), null,
                            bfOptions));
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        } finally {
            if (fs != null) {
                try {
                    fs.close();
                } catch (IOException e) {

                }
            }
        }
        return null;
    }

    /**
     * 旋转图片角度
     *
     * @param angle  旋转的角度
     * @param bitmap 图片
     * @return
     */
    public static Bitmap rotaingImage(int angle, Bitmap bitmap) {
        // 旋转图片 动作
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }

    /**
     * 获取图片的角度
     *
     * @param path   读取角度的图片路径
     * @return
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {

        }
        return degree;
    }

    private static int computeSampleSize(BitmapFactory.Options options,
                                         int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength,
                maxNumOfPixels);
        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }
        return roundedSize;
    }

    private static int computeInitialSampleSize(BitmapFactory.Options options,
                                                int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;

        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
                .sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
                Math.floor(w / minSideLength), Math.floor(h / minSideLength));

        if (upperBound < lowerBound) {
            return lowerBound;
        }

        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }

}
