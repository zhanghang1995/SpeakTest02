package com.example.a74993.speaktest02.net.okhttp.listener;

/**
 * 处理数据回掉接口
 * Created by Administrator on 2018/4/14.
 */

public class DisposeDataHandler {

    public DisposeDataListener disposeDataListener = null;
    public Class<?> mClass = null;
    public String mSource = null;

    public DisposeDataHandler(DisposeDataListener listener){
        this.disposeDataListener = listener;
    }

    public DisposeDataHandler(DisposeDataListener listener, Class<?> aClass) {
        this.disposeDataListener = listener;
        this.mClass = aClass;
    }

    public DisposeDataHandler(DisposeDataListener listener,String source){
        this.disposeDataListener = listener;
        this.mSource = source;
    }
}
