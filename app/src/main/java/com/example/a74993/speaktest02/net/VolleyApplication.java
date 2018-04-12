package com.example.a74993.speaktest02.net;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * 用于Volley请求的全局队列
 * Created by 74993 on 2018/3/10.
 */

public class VolleyApplication extends Application{
    public static RequestQueue requestQueue;//建立请求队列

    @Override
    public void onCreate() {
        super.onCreate();
        if(requestQueue!=null)
            requestQueue = Volley.newRequestQueue(getApplicationContext());//实例化请求队列
    }
    public static RequestQueue getRequestQueue(){
        return requestQueue;//返回一个请求队列
    }
}
