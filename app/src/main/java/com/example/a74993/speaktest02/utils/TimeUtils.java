package com.example.a74993.speaktest02.utils;

import java.text.SimpleDateFormat;

/**
 * 获取系统的时间操作
 * Created by Administrator on 2018/4/3.
 */

public class TimeUtils {

    //12进制系统时间 12:20
    public static String getSystemClockLower(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
        return simpleDateFormat.format(new java.util.Date());
    }
    //24进制系统时间 15:20
    public static String getSystemClockUpper(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        return simpleDateFormat.format(new java.util.Date());
    }
}
