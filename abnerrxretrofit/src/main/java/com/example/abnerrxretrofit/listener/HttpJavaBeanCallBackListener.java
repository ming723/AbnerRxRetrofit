package com.example.abnerrxretrofit.listener;


import com.example.abnerrxretrofit.model.IModel;

/**
 * Created by AbenrMing on 2018/1/2.
 * 请求返回JavaBean回调
 */

public interface HttpJavaBeanCallBackListener<D extends IModel> {
    void success(D d);
    void failure(String error);
}
