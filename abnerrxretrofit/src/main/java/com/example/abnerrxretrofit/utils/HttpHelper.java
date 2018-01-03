package com.example.abnerrxretrofit.utils;

import android.app.Activity;
import android.util.Log;

import com.example.abnerrxretrofit.listener.HttpJavaBeanCallBackListener;
import com.example.abnerrxretrofit.listener.HttpStringCallBackListener;
import com.example.abnerrxretrofit.model.IModel;
import com.example.abnerrxretrofit.service.RetrofitService;
import com.google.gson.Gson;
import com.hss01248.dialog.DialogAssigner;
import com.hss01248.dialog.StyledDialog;

import java.io.IOException;
import java.util.Map;

import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by AbenrMing on 2018/1/2.
 * 获取字符串或者JavaBean类
 */

public class HttpHelper {
    private RetrofitService mRetrofitService;
    private boolean isShow;
    private Activity activity;
    /**
     * isShow为true，展示加载框
     * */
    public HttpHelper(Map<String, String> map,boolean isShow,Activity activity) {
        mRetrofitService = HttpUtils.getmHttpUtils().getRetrofit(map).create(RetrofitService.class);
        this.isShow=isShow;
        this.activity=activity;
    }

    /**
     * get请求，返回字符串
     */
    public void getString(String url, Map<String, String> map, final HttpStringCallBackListener listener) {
        mRetrofitService.getRxRequest(url, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        if(isShow){
                            show();
                        }
                        Log.i("HttpHelper","onStart");
                    }

                    @Override
                    public void onCompleted() {
                        //关闭加载动画
                        dismiss();
                        Log.i("HttpHelper","onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.failure(e.getMessage());
                        Log.i("HttpHelper","onError");
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Log.i("HttpHelper","onNext");
                        try {
                            listener.success(responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * post请求，返回字符串
     */
    public void postString(String url, Map<String, String> map, final HttpStringCallBackListener listener) {
        mRetrofitService.postRxRequest(url, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        //开启加载动画
                        if(isShow){
                            show();
                        }
                    }

                    @Override
                    public void onCompleted() {
                        //关闭加载动画
                        dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.failure(e.getMessage());
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            listener.success(responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
    /**
     * get请求，返回JavaBean
     */
    public <D extends IModel> void getJavaBean(final Class mClass, String url, Map<String, String> map, final HttpJavaBeanCallBackListener listener) {
        mRetrofitService.getRxRequest(url, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        //开启加载动画
                        if(isShow){
                            show();
                        }
                    }

                    @Override
                    public void onCompleted() {
                        //关闭加载动画
                        dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.failure(e.getMessage());
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String json=responseBody.string();
                            D clss = (D) new Gson().fromJson(json, mClass);
                            listener.success(clss);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * post请求，返回JavaBean
     */
    public <D extends IModel> void postJavaBean(final Class mClass, String url, Map<String, String> map, final HttpJavaBeanCallBackListener listener) {
        mRetrofitService.postRxRequest(url, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        //开启加载动画
                        if(isShow){
                            show();
                        }
                    }

                    @Override
                    public void onCompleted() {
                        //关闭加载动画
                        dismiss();
                        Log.i("HttpHelper","onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.failure(e.getMessage());
                        Log.i("HttpHelper","onError");
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Log.i("HttpHelper","onNext");
                        try {
                            String json=responseBody.string();
                            D clss = (D) new Gson().fromJson(json, mClass);
                            listener.success(clss);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void show(){
        DialogAssigner.getInstance().assignLoading(activity,"正在加载……",true,false).show();

    }

    private void dismiss(){
        StyledDialog.dismissLoading();
    }

}
