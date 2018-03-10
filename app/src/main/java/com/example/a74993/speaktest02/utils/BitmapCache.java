package com.example.a74993.speaktest02.utils;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by 74993 on 2018/3/10.
 *
 */

public class BitmapCache implements ImageLoader.ImageCache {
    private KaleApplicatipon kaleApplicatipon = new KaleApplicatipon();
    //初始化LruCache 定义cache的大小，官方推荐的cache大小为当前app可用内存的八分之一
    //final int memoryClass = ((ActivityManager)getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass(); 获取当前app的内存

    private LruCache<String,Bitmap> cache; //内存缓存初始化，针对hashMap Key-Value
    public BitmapCache(){
        cache = new LruCache<String,Bitmap>(kaleApplicatipon.getMemoryCacheSize()){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                Log.d("ApplicationMemorySize","cache size = "+kaleApplicatipon.getMemoryCacheSize()/1024/1024+"M");
                return value.getRowBytes()*value.getHeight();
            }
        };
    }
    @Override
    public Bitmap getBitmap(String s) {
        return cache.get(s);
    }

    @Override
    public void putBitmap(String s, Bitmap bitmap) {
        cache.put(s,bitmap);
    }

    //添加Bitmap缓存对象
    public void addBitmapToMemoryCache(String key,Bitmap bitmap){
        if (getBitmapFromMemoryCache(key) == null){
            cache.put(key,bitmap);
        }
    }

    private Bitmap getBitmapFromMemoryCache(String key){
        return cache.get(key);
    }
    //删除Bitmap缓存对象
    public void deleteBitmapToMemoryCache(String key){
        if(getBitmapFromMemoryCache(key) != null){
            cache.remove(key);
        }
    }
}