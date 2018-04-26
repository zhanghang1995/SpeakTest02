package com.example.a74993.speaktest02.net.volley;

import android.graphics.Bitmap;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.a74993.speaktest02.net.volley.utils.VolleyApplication;
import com.example.a74993.speaktest02.net.volley.utils.VolleyBitmapCache;
import com.example.a74993.speaktest02.net.volley.utils.VolleyHandler;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/4/18.
 */

public class CommonVolleyClient {

    /** 1.
     * StringRequest GET方式
     * @param url 地址
     * @param volleyRequest 回调函数
     */
    public static void String_request(String url,VolleyHandler<String> volleyRequest){
        Volley_StringRequest(Request.Method.GET, url,null, volleyRequest);
    }

    /** 1.
     * StringRequset POST方式
     * @param url 地址
     * @param map 参数
     * @param volleyRequest 回调函数
     */
    public static void String_request(String url, final HashMap<String,String> map, VolleyHandler<String> volleyRequest){
        Volley_StringRequest(Request.Method.POST,url,map,volleyRequest);
    }

    /**1.
     * 封装 StringRequest 数据请求
     * @param method 方式
     * @param url 地址
     * @param params 参数
     * @param volleyRequest 回调对象
     */
    private static void Volley_StringRequest(int method,String url,final HashMap<String,String> params,VolleyHandler<String> volleyRequest){
        StringRequest stringrequest=new StringRequest(method, url,volleyRequest.responceList,volleyRequest.responceError){
            @Override
            protected HashMap<String, String> getParams() throws AuthFailureError {
                // TODO Auto-generated method stub
                return params;
            }
        };
        //通过设置tag可以取消当前的请求队列
        stringrequest.setTag("stringrequest");
        VolleyApplication.getRequestQueue().add(stringrequest);
    }


    /**2.
     * JsonObjectRequest GET 请求
     * @param url 请求地址
     * @param volleyRequest  回调函数对象
     */
    public static void JsonObject_Request(String url,VolleyHandler<JSONObject> volleyRequest){
        Volley_JsonObjectRequest(Request.Method.GET,url,null,volleyRequest);
    }

    /**2.
     * JsonObjectRequest POST 请求
     * @param url 请求地址
     * @param jsonObject 请求参数
     * @param volleyRequest 回调函数对象
     */
    public static void JsonObject_Request(String url,JSONObject jsonObject,VolleyHandler<JSONObject> volleyRequest){
        Volley_JsonObjectRequest(Request.Method.POST,url,jsonObject,volleyRequest);
    }

    /**2.
     * 封装 JsonObjectRequest 请求方法
     * @param method 方式
     * @param url 地址
     * @param jsonObject  参数
     * @param volleyRequest  回调函数对象
     */
    private static void Volley_JsonObjectRequest(int method,String url,JSONObject jsonObject,VolleyHandler<JSONObject> volleyRequest){
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(method,url,jsonObject,volleyRequest.responceList,volleyRequest.responceError);
        jsonObjectRequest.setTag("jsonObjectRequest");
        VolleyApplication.getRequestQueue().add(jsonObjectRequest);
    }


    /**3.
     * ImageRequest 默认大小 原图不变
     * @param url 地址
     * @param volleyRequest 回调函数
     */
    public static void Image_request(String url,VolleyHandler<Bitmap> volleyRequest){
        Volley_ImageRequest(url, 0, 0, volleyRequest);
    }

    /**3.
     * ImageRequest 自定义的缩放
     * @param url 地址
     * @param maxWidth 最大宽度
     * @param maxHeight 最大高度
     * @param volleyRequest 回调函数
     */
    public static void Image_request(String url,int maxWidth,int maxHeight,VolleyHandler<Bitmap> volleyRequest){
        Volley_ImageRequest(url, maxWidth, maxHeight, volleyRequest);
    }


    /**3.
     * 封装 ImageRequest 请求方法
     * @param url 地址
     * @param maxWidth 最大宽度
     * @param maxHeight 最大高度
     * @param volleyRequest 回调函数对象
     */
    private static void Volley_ImageRequest(String url,int maxWidth,int maxHeight,VolleyHandler<Bitmap> volleyRequest){
        ImageRequest imageRequest=new ImageRequest(url,volleyRequest.responceList, maxWidth, maxHeight, Bitmap.Config.RGB_565,volleyRequest.responceError);
        imageRequest.setTag("imageRequest");
        VolleyApplication.getRequestQueue().add(imageRequest);
    }


    /**
     * 4.
     * 自定义图片的宽度值
     * @param url
     * @param imageListener
     * @param maxWidth
     * @param maxHidth
     */
    public static void Image_Loader(String url, ImageLoader.ImageListener imageListener, int maxWidth, int maxHidth){
        Volley_ImageLoader(url, imageListener, maxWidth, maxHidth);
    }


    /** 4.
     * 默认值，原始比例
     * @param url 地址
     * @param imageListener 图片监听
     */
    public static void Image_Loader(String url,ImageLoader.ImageListener imageListener){
        Volley_ImageLoader(url,imageListener,0,0);
    }


    /** 4.
     * 封装 ImageLoader 方法
     * @param url 地址
     * @param imageListener 图片监听
     * @param maxWidth
     * @param maxHidth
     */
    private static void Volley_ImageLoader(String url, ImageLoader.ImageListener imageListener, int maxWidth, int maxHidth){
        // 设置 图片缓存 :体现 imageLoader的优势
        // 使用 LruBitmap + ImageCache 实现
        // 实例化对象
        ImageLoader imageLoader = new ImageLoader(VolleyApplication.getRequestQueue(),
                new VolleyBitmapCache());
        // 加载图片 图片监听 （默认图片，错误图片） 和 imageView
        imageLoader.get(url, imageListener,maxWidth,maxHidth);
    }

}
