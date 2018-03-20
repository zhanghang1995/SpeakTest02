package com.example.a74993.speaktest02.net.okhttp.utils;

import okhttp3.*;

/**
 * Created by king on 18/3/20.
 */
public interface UploadListener extends okhttp3.Callback{
    void onProgress(long totalBytes, long remainingBytes);
}
