package com.example.a74993.speaktest02.utils;

import android.util.Log;

/**
 * 打印日志错误信息
 * Created by 74993 on 2018/3/15.
 */

public class LogInfo {
    private static final String TAG = "";
    private static boolean DEBUG = true;

    public static void e(String message){
        if(DEBUG)
            Log.e(TAG,message);
    }
}
