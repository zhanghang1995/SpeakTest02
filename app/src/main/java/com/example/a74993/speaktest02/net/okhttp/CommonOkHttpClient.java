package com.example.a74993.speaktest02.net.okhttp;


import com.example.a74993.speaktest02.net.okhttp.https.HttpsUtils;
import com.example.a74993.speaktest02.net.okhttp.listener.DisposeDataHandler;
import com.example.a74993.speaktest02.net.okhttp.response.CommonFileCallback;
import com.example.a74993.speaktest02.net.okhttp.response.CommonJsonCallback;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Administrator on 2018/4/14.
 * @function 用来发送get，post请求的工具类，包括设置一些请求的共用参数
 */

public class CommonOkHttpClient {
    /**
     * 请求的超时时间，定义为30秒
     */
    private static final int TIME_OUT = 30;
    private static OkHttpClient mOkHttpClient;

    static {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        okHttpBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(TIME_OUT,TimeUnit.SECONDS);
        okHttpBuilder.followRedirects(true);
//        okHttpBuilder.sslSocketFactory(HttpsUtils.getSslSocketFactory());

        mOkHttpClient = okHttpBuilder.build();
    }

    /**
     * 指定cilent信任指定证书
     *
     * @param certificates
     */
    public static void setCertificates(InputStream... certificates)
    {
        mOkHttpClient.newBuilder().sslSocketFactory(HttpsUtils.getSslSocketFactory(certificates, null, null)).build();
    }

    /**
     * 指定client信任所有证书
     */
    public static void setCertificates()
    {
        mOkHttpClient.newBuilder().sslSocketFactory(HttpsUtils.getSslSocketFactory());
    }

    /**
     * POST请求
     * @param request
     * @param handler
     */
    public static void post(Request request, DisposeDataHandler handler){
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handler));
    }

    /**
     * GET请求
     * @param request
     * @param handler
     */
    public static void get(Request request, DisposeDataHandler handler){
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handler));
    }

    /**
     * 文件下载
     */
    public  static void downloadFile(Request request,DisposeDataHandler handler){
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonFileCallback(handler));
    }

}
