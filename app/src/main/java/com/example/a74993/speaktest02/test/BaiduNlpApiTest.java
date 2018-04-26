package com.example.a74993.speaktest02.test;

import com.example.a74993.speaktest02.net.volley.CommonVolleyClient;
import com.example.a74993.speaktest02.net.volley.utils.VolleyHandler;
import com.example.a74993.speaktest02.utils.LogInfo;
import com.example.a74993.speaktest02.utils.constant.NormalConstant;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2018/4/20.
 */

public class BaiduNlpApiTest {
    public static void testLexer() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("text","我想吃东西");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        VolleyHandler<JSONObject> volleyHandler = new VolleyHandler<JSONObject>() {
            @Override
            public void reqSuccess(JSONObject response) {
                try {
//                    LogInfo.e("调用百度词汇解析返回参数",response.getString("text"));
                    LogInfo.e("调用百度词汇解析返回参数",response.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void reqError(String Error) {
                LogInfo.e("调用百度词汇解析返回参数","调用错误");
            }
        };
        CommonVolleyClient.JsonObject_Request(NormalConstant.LEXER_URL+"?access_token=24.a2f7fca6a5988c77c90a0c3fdd5423c8.2592000.1526799580.282335-11129394?charset=GBK",jsonObject,volleyHandler);
    }
}
