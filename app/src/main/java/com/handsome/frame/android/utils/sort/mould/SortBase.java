package com.handsome.frame.android.utils.sort.mould;

/**
 * 排序模板
 * Created with Andrid Studio.
 * User:shuaizhimin
 * Date:16/4/30
 * Time:下午3:41
 */
public class SortBase {
    /**
     * 比较大小
     * @param v
     * @param w
     * @return
     */
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * 交换
     * @param a
     * @param i
     * @param j
     */
    public static void exch(Comparable[] a, int i, int j) {
        Comparable p = a[i];
        a[i] = a[j];
        a[j] = p;
    }

}
