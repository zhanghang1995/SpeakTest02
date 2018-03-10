package com.example.a74993.speaktest02.net;

import android.graphics.Bitmap;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.a74993.speaktest02.utils.BitmapCache;
import com.example.a74993.speaktest02.utils.VolleyApplication;

import org.json.JSONObject;

/**
 * 定义volley框架用户语言识别少量文字的上传与下载
 * volley框架多并发效果性好
 * 但是对于大文件的解决办法目前使用 AsyncHttp 解决或者使用  OkHttp进行大文件解决
 * Created by 74993 on 2018/3/10.
 */

public class VolleyMethod {
    //用于JsonObjectPost的进行请求
    public void jsonPost(String url){
        //null 处存放JsonObject对象
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				// 此处返回Json对象，对response进行处理
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				// 错误时候的回掉方法
			}
		});
        VolleyApplication.getRequestQueue().add(jsonObjectRequest);
    }

    //对StringRequset的请求
    public void stringPost(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // 此处返回String对象，对response进行处理
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // 错误时候的回掉方法
            }
        });
        VolleyApplication.getRequestQueue().add(stringRequest);
    }

    //用于对轻量级的图片文件加载，加载时结合Bitmap缓存(暂定)
    public void imageLoad(String url){
        ImageLoader imageLoader = new ImageLoader(VolleyApplication.getRequestQueue(),new BitmapCache());
        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(null,0,0);
        imageLoader.get(url,imageListener);

        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                //此处用于图片的存储处理
            }}, 0,0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                //0，0表示图片按照原来大小显示
                @Override
                public void onErrorResponse(VolleyError error) {
                    //错误的回掉方法
                }
            });
    }

}
