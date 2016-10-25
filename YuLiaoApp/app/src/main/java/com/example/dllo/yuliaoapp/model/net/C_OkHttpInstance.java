package com.example.dllo.yuliaoapp.model.net;

import okhttp3.OkHttpClient;

/**
 * Created by dllo on 16/10/21.
 * @author 陈思宇
 * OkHttp3的帮助类 (网络请求)
 * 单例 双重校验锁
 */
public class C_OkHttpInstance {
    private static C_OkHttpInstance instance;
    private OkHttpClient okHttpClient;
    //私有化构造方法
    private C_OkHttpInstance(){
        okHttpClient=new OkHttpClient();
    }
    //双重校验锁模式的单例
    public static C_OkHttpInstance getInstance(){
        //如果instance为空
        if (instance==null){
            synchronized (C_OkHttpInstance.class){
                if (instance==null){
                    instance=new C_OkHttpInstance();
                }
            }
        }
        return instance;
    }
    public void doSyncGet(String url){

    }

}
