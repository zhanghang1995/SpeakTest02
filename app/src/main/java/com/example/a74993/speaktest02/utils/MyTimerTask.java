package com.example.a74993.speaktest02.utils;

import android.content.Context;

import com.example.a74993.speaktest02.db.DbManager;
import com.example.a74993.speaktest02.model.UserTime;
import com.example.a74993.speaktest02.speak.speak_tackle.SpeechUpload;
import com.example.a74993.speaktest02.utils.LogInfo;
import com.example.a74993.speaktest02.utils.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimerTask;

/**
 * 用于进行定时任务调度的处理类，用于提醒用户的日程安排
 * Created by Administrator on 2018/4/8.
 */

public class MyTimerTask extends TimerTask {
    /**
     * hasOrder:标识当前用户是否已经定过餐，如订购，第二次直接推送
     * False:表示没有（default）
     * True：表示有
     */
    private boolean hasOrder = false;
    private String name;
    private Context context_this;
    private UserTime userTime;
    private DbManager dbManager;
    public MyTimerTask(String inputname, Context context, UserTime time, DbManager db) {
        context_this = context;
        name = inputname;
        userTime = time;
        dbManager = db;
    }
    @Override
    public void run() {
        /**
         * 此处通过name的不同分别执行不同的Task任务
         * 所有的时间数据实时在数据库中进行更新
         */
        updateAllTime();
        if (name.equals("起床")&&(TimeUtils.getSystemClockUpper().toString()).equals(userTime.getGetup_time())) {
            SpeechUpload.upload("起床", context_this, 0,false);
            LogInfo.e("当前系统时间", (TimeUtils.getSystemClockUpper().toString().trim()));
            LogInfo.e("Thread", "起床服务");
        }else if((TimeUtils.getSystemClockUpper().toString()).equals(userTime.getBreak_time())){  //早饭服务
            //此处在数据库进行查找操作，如果数据库中存在用户的对应菜名直接提取进行推送服务，不在需要问用户信息
            if(hasOrder){
                SpeechUpload.upload("早饭服务", context_this, 1,true);
            }else{
                SpeechUpload.upload("早饭服务", context_this, 1,false);
            }
            LogInfo.e("当前系统时间", (TimeUtils.getSystemClockUpper().toString().trim()));
            LogInfo.e("Thread", "早饭服务");
        }else if((TimeUtils.getSystemClockUpper().toString()).equals("12:01")){   //日程安排
            SpeechUpload.upload("日程安排", context_this, 2,false);
            LogInfo.e("当前系统时间", (TimeUtils.getSystemClockUpper().toString().trim()));
            LogInfo.e("Thread", "日程安排服务");
        }else if((TimeUtils.getSystemClockUpper().toString()).equals(userTime.getLunch_time())){   //午饭服务
            //此处在数据库进行查找操作，如果数据库中存在用户的对应菜名直接提取进行推送服务，不在需要问用户信息
            if (hasOrder){
                SpeechUpload.upload("午饭服务", context_this, 3,true);
            }else{
                SpeechUpload.upload("午饭服务", context_this, 3,false);
            }
            LogInfo.e("当前系统时间", (TimeUtils.getSystemClockUpper().toString().trim()));
            LogInfo.e("Thread", "午饭服务");
        }else if((TimeUtils.getSystemClockUpper().toString()).equals("12:03")){   //午觉提醒
            SpeechUpload.upload("午觉提醒", context_this, 4,false);
            LogInfo.e("当前系统时间", (TimeUtils.getSystemClockUpper().toString().trim()));
            LogInfo.e("Thread", "午觉提醒服务");
        }else if((TimeUtils.getSystemClockUpper().toString()).equals(userTime.getDinner_time())){   //晚饭服务
            //此处在数据库进行查找操作，如果数据库中存在用户的对应菜名直接提取进行推送服务，不在需要问用户信息
            if (hasOrder) {
                SpeechUpload.upload("晚饭服务", context_this, 5,true);
            }else {
                SpeechUpload.upload("晚饭服务", context_this, 5,false);
            }
            LogInfo.e("当前系统时间", (TimeUtils.getSystemClockUpper().toString().trim()));
            LogInfo.e("Thread", "晚饭服务");
        }else{
            LogInfo.e("当前系统时间", (TimeUtils.getSystemClockUpper().toString().trim())+"提醒进程在运行中！");
        }

    }

    private void updateAllTime() {
        //用于实时更新数据库中的所有信息
        userTime.setBreak_time(dbManager.queryTheTime(context_this,1));
        userTime.setLunch_time(dbManager.queryTheTime(context_this,2));
        userTime.setDinner_time(dbManager.queryTheTime(context_this,3));
        userTime.setGetup_time(dbManager.queryTheTime(context_this,4));
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
