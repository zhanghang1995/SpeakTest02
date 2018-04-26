package com.example.a74993.speaktest02.test;

import android.content.Context;
import android.widget.Toast;

import com.example.a74993.speaktest02.net.volley.CommonVolleyClient;
import com.example.a74993.speaktest02.net.volley.utils.VolleyHandler;
import com.example.a74993.speaktest02.utils.constant.NormalConstant;
import com.example.a74993.speaktest02.utils.constant.UrlConstant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Administrator on 2018/4/18.
 */

public class VolleyTest extends Exception {
    public static void upload(final Context context) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userwords", "起床");
            jsonObject.put("type", 0);
            jsonObject.put("hasOrder", 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        VolleyHandler<JSONObject> volleyHandler = new VolleyHandler<JSONObject>() {
            @Override
            public void reqSuccess(JSONObject response) {
                try {
                    Toast.makeText(context, response.getString("returnData"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void reqError(String Error) {
                Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show();
            }
        };

        CommonVolleyClient.JsonObject_Request(NormalConstant.SPEECH_UPLOAD, jsonObject, volleyHandler);

    }
}
