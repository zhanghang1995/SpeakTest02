package com.example.a74993.speaktest02.net.volley.utils;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * 用于进行Android的成功的监听以及失败的监听
 * 抽象类
 * Created by Administrator on 2018/4/18.
 */

public abstract class VolleyHandler<T> {


    public Response.Listener<T> responceList;
    public Response.ErrorListener responceError;

    public VolleyHandler(){
        //初始化回掉变量
        responceList = new reqListener();
        responceError = new reqErrorListener();
    }

    public abstract void reqSuccess(T response);
    public abstract void reqError(String Error);

    private class reqListener implements Response.Listener<T> {
        @Override
        public void onResponse(T t) {
            reqSuccess(t);
        }
    }

    private class reqErrorListener implements Response.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            reqError(volleyError.getMessage());
        }
    }
}
