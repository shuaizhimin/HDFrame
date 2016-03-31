package com.handsome.frame.android.utils.sort;

/**
 * Created By shuaizhimin
 * Date:16/3/31
 * Time:下午11:22
 */
public class HDInsertSort {
    /**
     * 直接插入排序
     *
     * @param a
     * @return
     */
    public static int[] directInsertSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int temp = a[i];
            int j;
            for (j = i - 1; j >= 0 && a[j] > temp; j--) {
                a[j + 1] = a[j];
            }
            a[j + 1] = temp;
        }
        return a;
    }

    /**
     * 二分插入排序
     *
     * @param a
     * @return
     */
    private static int[] binaryInsertSort(int[] a) {

        for (int i = 0; i < a.length; i++) {
            int temp = a[i];
            int left=0;
            int right=i-1;
            int middle=0;
            while (left<=right){
                middle=(left+right)/2;
                if(temp<a[middle]){
                    //right=middle-1;
                }else {
                    left=middle+1;
                }
            }
            for(int j=i-1;j>=left;j--){
                 a[j+1]=a[j];
            }
            if(left!=i){
                a[left]=temp;
            }

        }
        return a;

    }

    public static void main(String args[]) {
        int[] a = {49, 38, 65, 97,12,76, 13, 27, 49, 78, 34, 12, 64, 1};
        System.out.println("排序之前:");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
        System.out.println("直接插入排序之后:");
        int[] b = directInsertSort(a);
        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i] + " ");
        }
        System.out.println();
        System.out.println("二分插入排序之后:");
        int[] c = directInsertSort(a);
        for (int i = 0; i < c.length; i++) {
            System.out.print(c[i] + " ");
        }
    }
}
