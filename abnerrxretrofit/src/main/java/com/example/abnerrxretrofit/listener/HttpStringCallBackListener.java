package com.example.abnerrxretrofit.listener;

/**
 * Created by Administrator on 2018/1/2.
 * 请求返回字符串回调
 */

public interface HttpStringCallBackListener {
    void success(String message);
    void failure(String error);
}
