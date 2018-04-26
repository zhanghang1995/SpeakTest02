package com.example.a74993.speaktest02.baidunlp.model;

/**
 * 用于定义回应格式
 * Created by Administrator on 2018/4/25.
 */

public class ResponseResult {
    private long logId;

    public String jsonRes;

    public long getLogId() {
        return logId;
    }

    public void setLogId(long logId) {
        this.logId = logId;
    }

    public String getJsonRes() {
        return jsonRes;
    }

    public void setJsonRes(String jsonRes) {
        this.jsonRes = jsonRes;
    }
}
