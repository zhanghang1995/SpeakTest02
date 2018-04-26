package com.example.a74993.speaktest02.net.okhttp.listener;

/**
 * 数据请求成功与失败的回掉方法
 * Created by Administrator on 2018/4/14.
 */

public interface DisposeDataListener {

    public void onSuccess(Object response);

    public void onFailure(Object reason);
}
