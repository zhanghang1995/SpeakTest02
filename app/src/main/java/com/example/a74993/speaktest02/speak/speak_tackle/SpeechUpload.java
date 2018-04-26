package com.example.a74993.speaktest02.speak.speak_tackle;

import android.content.Context;

import com.example.a74993.speaktest02.db.DbManager;
import com.example.a74993.speaktest02.net.volley.CommonVolleyClient;
import com.example.a74993.speaktest02.net.volley.utils.VolleyHandler;
import com.example.a74993.speaktest02.speak.TextSpeak;
import com.example.a74993.speaktest02.utils.LogInfo;
import com.example.a74993.speaktest02.utils.ToastUtils;
import com.example.a74993.speaktest02.utils.ToolsUtils;
import com.example.a74993.speaktest02.utils.constant.NormalConstant;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 定义volley框架用户语言识别少量文字的上传与下载
 * volley框架多并发效果性好
 * 但是对于大文件的解决办法目前使用 AsyncHttp 解决或者使用  OkHttp进行大文件解决
 * Created by 74993 on 2018/3/10.
 */


public class SpeechUpload {
    private static DbManager dbManager;
    private static TextSpeak text_convery = new TextSpeak();
    /**
     * 后台返回数据的存放
     * returnData：播放文本信息
     * dishType：菜名或时间
     * isEnd: 上下文对话是否结束
     * type1：标识返回的类型
     * type2：标识返回的dishType的种类。1：表示菜名；2：表示时间
     * order：用户是否进行点餐的操作；0：不进行；1：进行
     */
    private static String returnData;
    private static String dishType;
    private static boolean isEnd;
    private static String type1;
    private static int type2;
    private static int order;

    public static void upload(String speakwords, final Context context, final int type, final int hasOrder) {
        dbManager = new DbManager(context);
        //断点测试
        LogInfo.e(NormalConstant.TAG, speakwords);
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userwords", speakwords);
            jsonObject.put("type", type);
            jsonObject.put("hasOrder", hasOrder);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final VolleyHandler<JSONObject> volleyHandler = new VolleyHandler<JSONObject>() {
            @Override
            public void reqSuccess(JSONObject response) {
                try {
                    returnData = response.getString("returnData");
                    dishType = response.getString("dishType");
                    isEnd = response.getBoolean("isEnd");
                    type1 = response.getString("type");
                    type2 = response.getInt("type2");
                    order = response.getInt("order");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                /**
                 * 如果用户订餐，那么进行订餐服务，具体订餐信息：时间，地点，菜名
                 */
                if (order == 1) {
                    ToastUtils.ShowToast("直接下单" + returnData, context);
                }
                /**
                 * 判断用户当前是否有需要存储信息
                 */
                if (type2 == 1) {
                    //此时将用户的菜名dishType进行存储，并根据type进行更新 key: cloums value:dishType
                    if (type == 1) {
//                            添加早饭
                        dbManager.updateType("breaktype", dishType, ToolsUtils.getUserDeviceID(context), context);
                    } else if (type == 3) {
//                            添加中饭
                        dbManager.updateType("lunchtype", dishType, ToolsUtils.getUserDeviceID(context), context);
                    } else if (type == 5) {
//                            添加晚饭
                        dbManager.updateType("dinnertype", dishType, ToolsUtils.getUserDeviceID(context), context);
                    } else {
                    }
                } else if (type2 == 2) {
                    //此时将用户的时间dishType进行存储，并根据type进行更新
                    if (type == 1) {
//                            添加早饭时间
                        dbManager.updateTime("breaktime", dishType, ToolsUtils.getUserDeviceID(context), context);
                    } else if (type == 3) {
//                            添加中饭时间
                        dbManager.updateTime("lunchtime", dishType, ToolsUtils.getUserDeviceID(context), context);
                    } else if (type == 5) {
//                            添加晚饭时间
                        dbManager.updateTime("dinner", dishType, ToolsUtils.getUserDeviceID(context), context);
                    } else {

                    }
                } else {

                }

                if (!isEnd) {
                    text_convery.speakText(returnData, context, type, false, hasOrder);

                } else {
                    text_convery.speakText(returnData, context, Integer.parseInt(type1), true, hasOrder);
                }
            }

            @Override
            public void reqError(String Error) {
                // 错误时候的回掉方法
                LogInfo.e(NormalConstant.TAG, "访问错误");
                ToastUtils.ShowToast("ERROR", context);
            }
        };

        CommonVolleyClient.JsonObject_Request(NormalConstant.SPEECH_UPLOAD, jsonObject, volleyHandler);
    }

}

