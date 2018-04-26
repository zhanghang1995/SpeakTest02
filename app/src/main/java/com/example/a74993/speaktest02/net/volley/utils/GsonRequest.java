package com.example.a74993.speaktest02.net.volley.utils;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;

import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * 自定义Gson的请求方法，也可以自定义其他的请求方法
 * Created by Administrator on 2018/4/18.
 */

public class GsonRequest<T> extends Request<T>{

    private Response.Listener<T> tlistener;

    private Gson gson;

    private Class<T> gClass;

    public GsonRequest(int method, String url, Class<T> tClass, Response.Listener<T> listener, Response.ErrorListener errorListener){
        super(method,url,errorListener);
        gson = new Gson();
        gClass = tClass;
        listener = listener;
    }


    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            //将字符流转成字符串，并且设置 字符编码 ，来自响应信息的报文都不信息
            String jsonString=new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));

            //返回信息 使用 gson 直接转 对象，第二个参数 设置编码
            return Response.success(gson.fromJson(jsonString, gClass),HttpHeaderParser.parseCacheHeaders(networkResponse));


        } catch (UnsupportedEncodingException e) {
            // 出错的时候，将错误信息重新调出
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T t) {
        tlistener.onResponse(t);
    }
}
