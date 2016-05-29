package com.handsome.frame.android.test.suanfa;

import java.io.Serializable;

/**
 * Created with Andrid Studio.
 * User:shuaizhimin
 * Date:16/4/29
 * Time:下午2:26
 */
public class People implements Serializable{
    public int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "People{" +
                "age=" + age +
                '}';
    }
}
