/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.example.a74993.speaktest02.baidunlp.parser;

import android.util.Log;


import com.example.a74993.speaktest02.baidunlp.exception.UnitError;
import com.example.a74993.speaktest02.baidunlp.model.ResponseResult;

import org.json.JSONException;
import org.json.JSONObject;

public class DefaultParser implements Parser<ResponseResult> {

    @Override
    public ResponseResult parse(String json) throws UnitError {
        Log.e("xx", "DefaultParser:" + json);
        try {
            JSONObject jsonObject = new JSONObject(json);

            if (jsonObject.has("error_code")) {
                UnitError error = new UnitError(jsonObject.optInt("error_code"), jsonObject.optString("error_msg"));
                throw error;
            }

            ResponseResult result = new ResponseResult();
            result.setLogId(jsonObject.optLong("log_id"));
            result.setJsonRes(json);

            return result;
        } catch (JSONException e) {
            e.printStackTrace();
            UnitError error = new UnitError(UnitError.ErrorCode.JSON_PARSE_ERROR, "Json parse error:" + json, e);
            throw error;
        }
    }
}
