package com.example.dllo.yuliaoapp.tools;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by dllo on 16/10/24.
 * 封装的OkHttp
 * @author 赵玲琳
 */
public class Z_OkHttpClientManagerUtils {

    private static Z_OkHttpClientManagerUtils instance;
    private OkHttpClient okHttpClient;
    private Handler handler;

    private static final String OKHTTP = "Z_OkHttpClientManagerUtils";

    // 私有化构造方法
    private Z_OkHttpClientManagerUtils(){
        okHttpClient = new OkHttpClient();
        handler = new Handler(Looper.getMainLooper());
    }

    // 单例
    public static Z_OkHttpClientManagerUtils getInstance() {
        // 双重检验锁
        if (instance == null) {
            // 同步代码块
            synchronized (Z_OkHttpClientManagerUtils.class) {
                if (instance == null) {
                    instance = new Z_OkHttpClientManagerUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 同步get请求
     * @param url
     * @return response
     * @throws IOException
     */
    private Response getAsyn(String url) throws IOException{
        final Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        return response;
    }

    /**
     * 同步get请求
     * @param url
     * @return  字符串
     * @throws IOException
     */
    private String getAsynString(String url) throws IOException{
        Response response = getAsyn(url);
        return response.body().string();
    }

    /**
     * 异步get请求
     * @param url
     * @param callback
     */
    private void getAsyn(String url, final ResultCallback callback){
        final Request request = new Request.Builder().url(url).build();

        deliveryResult(callback,request);
    }

    /**
     * 同步Post请求
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    private Response post(String url ,Param[] params)throws  IOException{
        Request request = buildPostRequest(url,params);
        Response response = okHttpClient.newCall(request).execute();
        return response;
    }

    /**
     * 同步post请求
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    private String postAsString(String url , Param[] params )throws IOException{
        Response response = post(url,params);
        return response.body().string();
    }

    /**
     * 异步post请求
     * @param url
     * @param callback
     * @param params
     */
    private void postAsyn(String url,final ResultCallback callback,Param[] params){
        Request request = buildPostRequest(url,params);
        deliveryResult(callback,request);
    }

    /**
     * 异步post请求
     * @param url
     * @param callback
     * @param params
     */
    private void postAsyn(String url, final ResultCallback callback, Map<String,String> params){
        Param[] paramsArr = map2Params(params);
        Request request = buildPostRequest(url,paramsArr);
        deliveryResult(callback,request);
    }


    /**
     * 对外公布的方法
     */
    public static Response getRequestAsyn(String url) throws  IOException{
        return getInstance().getAsyn(url);
    }

    public static String getRequestAsString(String url)throws IOException{
        return getInstance().getAsynString(url);
    }

    public static void getRequestAsyn(String url,ResultCallback callback)throws IOException{
        getInstance().getAsyn(url,callback);
    }

    public static Response postRequest(String url,Param[] params)throws IOException{
        return getInstance().post(url,params);
    }

    public static String postRequestAsString(String url,Param[] params)throws IOException{
        return getInstance().postAsString(url,params);
    }

    public static void postRequestAsyn(String url,final ResultCallback callback,Param[] params)throws IOException{
        getInstance().postAsyn(url,callback,params);
    }

    public static void postRequestAsyn(String url,final ResultCallback callback,Map<String,String> params)throws IOException{
        getInstance().postAsyn(url,callback,params);
    }



    private Param[] map2Params(Map<String, String> params) {

        if (params == null){
            return new Param[0];
        }
        int size = params.size();
        Param[] res = new Param[size];
        Set<Map.Entry<String,String>> entrise = params.entrySet();
        int i = 0;
        for (Map.Entry<String,String> entry : entrise){
            res[i++] = new Param(entry.getKey(),entry.getValue());

        }
        return res;


    }

    private Request buildPostRequest(String url, Param[] params) {

        if (params == null ){
            params = new Param[0];
        }
        FormBody.Builder builder = new FormBody.Builder();
        for (Param param : params){
            builder.add(param.key,param.value);
        }
        RequestBody requestBody = builder.build();
        return new Request.Builder().url(url).post(requestBody).build();

    }


    private void deliveryResult(final ResultCallback callback, Request request) {
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("OkHttpClientManager", "获取网络数据错误");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                    sendSuccessResultCallback(string,callback);
            }
        });
    }

    private void sendSuccessResultCallback(final Object object, final ResultCallback callback) {

        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null){
                    callback.onResponse(object);
                }
            }
        });
    }


    public static abstract class ResultCallback<T> {

        public abstract void onError(Request request, Exception e);

        public abstract void onResponse(T response);
    }

    public static class Param {
        public Param() {
        }

        public Param(String key, String value) {
            this.key = key;
            this.value = value;
        }
        String key;
        String value;
    }
}
