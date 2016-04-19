package com.handsome.frame.android.volley.http;

/**
 * Created By shuaizhimin
 * Date:16/2/18
 * Time:上午10:17
 */
public interface HandleRequestError {
    void notConnetedError();
    void timeOutError();
    void serverError();
    void networkError();
    void otherError();
}
