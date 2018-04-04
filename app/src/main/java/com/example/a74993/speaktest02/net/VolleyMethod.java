package com.example.a74993.speaktest02.net;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.a74993.speaktest02.speak.TextConvery;
import com.example.a74993.speaktest02.utils.BitmapCache;
import com.example.a74993.speaktest02.utils.Constant;
import com.example.a74993.speaktest02.utils.LogInfo;
import com.example.a74993.speaktest02.utils.ToastUtils;
import com.example.a74993.speaktest02.utils.VolleyApplication;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * 定义volley框架用户语言识别少量文字的上传与下载
 * volley框架多并发效果性好
 * 但是对于大文件的解决办法目前使用 AsyncHttp 解决或者使用  OkHttp进行大文件解决
 * Created by 74993 on 2018/3/10.
 */

public class VolleyMethod {
    private TextConvery text_convery = new TextConvery();
    private Context context_this;
    private RequestQueue requestQueue;
    public String resultword = "未找到信息!";
    public VolleyMethod(Context context)
    {
        context_this = context;
        requestQueue = Volley.newRequestQueue(context);
    }
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
    public String  stringPost(String url, String speakwords, final int type){
        final String words = speakwords.trim();
        final String  wordsType = Integer.toString(type);
        //断点测试
        LogInfo.e(Constant.TAG,words);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // 此处返回String对象，对response进行处理
                resultword = response.toString();
                text_convery.speakText(resultword,context_this,type);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // 错误时候的回掉方法
                LogInfo.e(Constant.TAG,"访问错误");
                ToastUtils.ShowToast("ERROR",context_this);
            }
        })
        {
            //参数携带，当发出POST请求的时候，volley会尝试调用StringRequest的父类
            //Request中的getParams()方法来获取POST参数
            @Override
            protected HashMap<String,String> getParams()
                    throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<String,String>();
                hashMap.put("userwords",words);
                hashMap.put("type",wordsType);
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);
        return  resultword;
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
