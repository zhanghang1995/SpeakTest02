package com.example.a74993.speaktest02.utils.time;

import android.content.Context;

import com.example.a74993.speaktest02.speak.speak_tackle.SpeechUpload;
import com.example.a74993.speaktest02.utils.LogInfo;
import com.example.a74993.speaktest02.utils.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimerTask;

/**
 * 用于进行定时任务调度的处理类，
 * Created by Administrator on 2018/4/8.
 */

public class MyTimerTask extends TimerTask {
    private String name;
    private Context context_this;
    //计数器记录每次执行的次数
    private Integer count = 0;
    public MyTimerTask(String inputname,Context context) {
        context_this = context;
        name = inputname;
    }
    @Override
    public void run() {
        /**
         * 此处通过name的不同分别执行不同的Task任务
         */
        if (name.equals("起床")&&(TimeUtils.getSystemClockUpper().toString()).equals("07:00")) {
            SpeechUpload.upload("起床", context_this, 0);
            LogInfo.e("当前系统时间", (TimeUtils.getSystemClockUpper().toString().trim()));
            LogInfo.e("Thread", "起床服务");
        }else if((TimeUtils.getSystemClockUpper().toString()).equals("07:30")){  //早饭服务
            SpeechUpload.upload("早饭服务", context_this, 1);
            LogInfo.e("当前系统时间", (TimeUtils.getSystemClockUpper().toString().trim()));
            LogInfo.e("Thread", "早饭服务");
        }else if((TimeUtils.getSystemClockUpper().toString()).equals("11:30")){   //日程安排
            SpeechUpload.upload("日程安排", context_this, 2);
            LogInfo.e("当前系统时间", (TimeUtils.getSystemClockUpper().toString().trim()));
            LogInfo.e("Thread", "日程安排服务");
        }else if((TimeUtils.getSystemClockUpper().toString()).equals("12:00")){   //午饭服务
            SpeechUpload.upload("午饭服务", context_this, 3);
            LogInfo.e("当前系统时间", (TimeUtils.getSystemClockUpper().toString().trim()));
            LogInfo.e("Thread", "午饭服务");
        }else if((TimeUtils.getSystemClockUpper().toString()).equals("1:00")){   //午觉提醒
            SpeechUpload.upload("午觉提醒", context_this, 4);
            LogInfo.e("当前系统时间", (TimeUtils.getSystemClockUpper().toString().trim()));
            LogInfo.e("Thread", "午觉提醒服务");
        }else if((TimeUtils.getSystemClockUpper().toString()).equals("17:33")){   //晚饭服务
            SpeechUpload.upload("晚饭服务", context_this, 5);
            LogInfo.e("当前系统时间", (TimeUtils.getSystemClockUpper().toString().trim()));
            LogInfo.e("Thread", "晚饭服务");
        }else{
            LogInfo.e("当前系统时间", (TimeUtils.getSystemClockUpper().toString().trim())+"提醒进程在运行中！");
        }

    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
