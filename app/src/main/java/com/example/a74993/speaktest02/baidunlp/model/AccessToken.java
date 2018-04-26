package com.example.a74993.speaktest02.baidunlp.model;

/**
 * 用于记录百度UNIT接入相关权限
 * Created by Administrator on 2018/4/25.
 */

public class AccessToken {
    /**
     * 通过APP_KEY以及Secret_Key进行获得
     */
    private String accessToken;
    /**
     * access_token失效日期
     */
    private int expiresIn;
    private String json;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
