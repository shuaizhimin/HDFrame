package com.handsome.frame.android.test.suanfa.twoday;

import com.handsome.frame.android.utils.sort.HDInsertSort;

import java.util.Scanner;

/**
 * Created with Andrid Studio.
 * User:shuaizhimin
 * Date:16/4/30
 * Time:下午3:17
 */
public class TowDay {
    public static void main(String args[]){
//        Scanner sc = new Scanner(System.in);
//        System.out.println("请输入你的姓名：");
//        String name=sc.nextLine();
//        System.out.println("姓名:"+name);
//
        Comparable[] a= new Comparable[]{10,1,11,3,4,20,45};

        System.out.println(HDInsertSort.selectSort(a));


    }


}
