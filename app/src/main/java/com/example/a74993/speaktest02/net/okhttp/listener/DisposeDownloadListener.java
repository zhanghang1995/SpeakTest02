package com.example.a74993.speaktest02.net.okhttp.listener;

/**
 * 用于监听文件加载的进度信息
 * Created by Administrator on 2018/4/14.
 */

public interface DisposeDownloadListener extends DisposeDataListener{
    public void onProgress(int progress);
}
