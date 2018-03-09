package com.example.a74993.speaktest02.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 用于用户自定义toast对话形式
 *
 * Created by king on 18/3/8.
 */

public class ToastUtils {
    public static void ShowToast(String data, Context context){
        Toast.makeText(context,data, Toast.LENGTH_SHORT).show();
    }
}
