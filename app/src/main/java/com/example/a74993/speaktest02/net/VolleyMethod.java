package com.example.a74993.speaktest02.net;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.a74993.speaktest02.db.DbManager;
import com.example.a74993.speaktest02.speak.TextConvery;
import com.example.a74993.speaktest02.utils.BitmapCache;
import com.example.a74993.speaktest02.utils.Constant;
import com.example.a74993.speaktest02.utils.LogInfo;
import com.example.a74993.speaktest02.utils.ToastUtils;
import com.example.a74993.speaktest02.utils.ToolsUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 定义volley框架用户语言识别少量文字的上传与下载
 * volley框架多并发效果性好
 * 但是对于大文件的解决办法目前使用 AsyncHttp 解决或者使用  OkHttp进行大文件解决
 * Created by 74993 on 2018/3/10.
 */

public class VolleyMethod {
    private DbManager dbManager;
    private TextConvery text_convery = new TextConvery();
    private Context context_this;
    private RequestQueue requestQueue;
    private String resultword = "未找到信息!";
    /**
     *     后台返回数据的存放
     *     returnData：播放文本信息
     *     dishType：菜名或时间
     *     isEnd: 上下文对话是否结束
     *     type1：标识返回的类型
     *     type2：标识返回的dishType的种类。1：表示菜名；2：表示时间
     *     order：用户是否进行点餐的操作；0：不进行；1：进行
     */
    private String  returnData;
    private String dishType;
    private boolean isEnd;
    private String type1;
    private int type2;
    private int order;
    public VolleyMethod(Context context)
    {
        context_this = context;
        requestQueue = Volley.newRequestQueue(context);
    }
    //用于JsonObjectPost的进行请求
    public void stringPost(String url, String speakwords, final int type, final boolean hasOrder){
        final String words = speakwords.trim();
        final String  wordsType = Integer.toString(type);
        dbManager = new DbManager(context_this);
        //断点测试
        LogInfo.e(Constant.TAG,words);
        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				// 此处返回Json对象，对response进行处理
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    returnData = jsonObject.getString("returnData");
                    dishType = jsonObject.getString("dishType");
                    isEnd = jsonObject.getBoolean("isEnd");
                    type1 = jsonObject.getString("type");
                    type2 = jsonObject.getInt("type2");
                    order = jsonObject.getInt("order");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(!isEnd) {
                    if(order == 1){
                        ToastUtils.ShowToast("直接下单",context_this);
                    }
                    text_convery.speakText(returnData, context_this, type, false,hasOrder);
                    if (type2 == 1) {
                        //此时将用户的菜名dishType进行存储，并根据type进行更新 key: cloums value:dishType
                        if (type == 1) {
//                            添加早饭
                            dbManager.updateType("breaktype",dishType, ToolsUtils.getUserDeviceID(context_this),context_this);
                            ToastUtils.ShowToast(dishType,context_this);
                        } else if (type == 3) {
//                            添加中饭
                            dbManager.updateType("lunchtype",dishType,ToolsUtils.getUserDeviceID(context_this),context_this);
                            ToastUtils.ShowToast(dishType,context_this);
                        } else if (type == 5) {
//                            添加晚饭
                            dbManager.updateType("dinnertype",dishType,ToolsUtils.getUserDeviceID(context_this),context_this);
                            ToastUtils.ShowToast(dishType,context_this);
                        } else {
                        }
                    } else if (type2 == 2) {
                        //此时将用户的时间dishType进行存储，并根据type进行更新
                        if (type == 1) {
//                            添加早饭时间
                            dbManager.updateTime("breaktime",dishType,ToolsUtils.getUserDeviceID(context_this), context_this);
                            ToastUtils.ShowToast(dishType,context_this);
                        } else if (type == 3) {
//                            添加中饭时间
                            dbManager.updateTime("lunchtime",dishType,ToolsUtils.getUserDeviceID(context_this),context_this);
                            ToastUtils.ShowToast(dishType,context_this);
                        } else if (type == 5) {
//                            添加晚饭时间
                            dbManager.updateTime("dinner",dishType,ToolsUtils.getUserDeviceID(context_this),context_this);
                            ToastUtils.ShowToast(dishType,context_this);
                        } else {
                        }
                    }
                }else{
                    if(order == 1){
                        ToastUtils.ShowToast("直接下单"+returnData,context_this);
                    }
                    if (type2 == 1) {
                        //此时将用户的菜名dishType进行存储，并根据type进行更新
                        if (type == 1) {
//                            添加早饭
                            dbManager.updateType("breaktype",dishType,ToolsUtils.getUserDeviceID(context_this),context_this);
                        } else if (type == 3) {
//                            添加中饭
                            dbManager.updateType("luntype",dishType,ToolsUtils.getUserDeviceID(context_this),context_this);
                            ToastUtils.ShowToast(dishType,context_this);
                        } else if (type == 5) {
//                            添加午饭
                            dbManager.updateType("dinnertype",dishType,ToolsUtils.getUserDeviceID(context_this),context_this);
                        } else {
                        }
                    } else if (type2 == 2) {
                        //此时将用户的时间dishType进行存储，并根据type进行更新
                        if (type == 1) {
//                            添加早饭时间
                            dbManager.updateTime("breaktime",dishType,ToolsUtils.getUserDeviceID(context_this), context_this);
                        } else if (type == 3) {
//                            添加中饭时间
                            dbManager.updateTime("lunchtime",dishType,ToolsUtils.getUserDeviceID(context_this),context_this);
                            ToastUtils.ShowToast(dishType,context_this);
                        } else if (type == 5) {
//                            添加午饭时间
                            dbManager.updateTime("dinner",dishType,ToolsUtils.getUserDeviceID(context_this),context_this);
                        } else {
                        }
                    }
                    text_convery.speakText(returnData,context_this,Integer.parseInt(type1),true,hasOrder);
                }

            }
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				// 错误时候的回掉方法
                // 错误时候的回掉方法
                LogInfo.e(Constant.TAG,"访问错误");
                ToastUtils.ShowToast("ERROR",context_this);
			}
		}) {
            //参数携带，当发出POST请求的时候，volley会尝试调用StringRequest的父类
            //Request中的getParams()方法来获取POST参数
            @Override
            protected HashMap<String, String> getParams()
                    throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("userwords", words);
                hashMap.put("type", wordsType);
                hashMap.put("hasOrder", String.valueOf(hasOrder));
                return hashMap;
            }
        };
        requestQueue.add(jsonObjectRequest);
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
