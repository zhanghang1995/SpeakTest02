package com.example.a74993.speaktest02.utils;

import android.content.Context;

import com.example.a74993.speaktest02.speak.speak_tackle.SpeechUpload;

/**
 * Created by Administrator on 2018/4/3.
 */

public class ThreadUtils extends Thread{
    private Context context_this;
    public ThreadUtils(Context context){
        context_this = context;
    }
    @Override
    public void run() {
        /**
         * 用于循环轮播判断
         */
        if(TimeUtils.getSystemClockUpper().equals("15:50:40")){
            SpeechUpload.upload("早饭提醒",context_this,0);
        }
    }
}
