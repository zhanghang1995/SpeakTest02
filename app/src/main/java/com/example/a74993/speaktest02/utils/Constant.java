package com.example.a74993.speaktest02.utils;

/**
 * 定义所有参数的常量
 * Created by king on 18/3/8.
 */

public class Constant {
    public static int SERVICE_TYPE = 2;
    //用于标识当前为第一次使用模式
    public static String RESTOYES = "知道了主人";
    //定义每次的肯定的回复
    public static String USERTABLE = "person";
    //本地SQLITE数据库名称
    public static final String TAG = "log_i";
    //定义网络文件下载的请求
    public static String FILE_DOWNLOAD = "";
    //定义网络文件上传的请求
    public static String FILE_UPLOAD = "";
    //定义图片文件下载的请求
    public static String _DOWNLOAD = "";
    //定义图片文件上传的请求
    public static String IMAGE_UPLOAD = "";
    //定义全局的URL
    public static String URL = "";
    //定义声音文件处理的URL
    public static final String SPEECH_UPLOAD= "http://192.168.0.121:5000/speak/deal";
    //定义声音文件处理结果的URL
    public static final String SPEECH_DOWNLOAD = "";
    //语音系统完成语言输入
    public static final String FINISHE_WORD = "讲完啦";
    //语言系统用于转换用户的输入
    public static final String FINISHE_SPEAK = "换完了";
    //第一次使用问候语
    public static final String HELLO = "您好，SIRI";
    /**
     * 问答模块
     */
    //第一次使用问候语
    public static final String Name = "主人，以后我如何称呼您？";

    //用户请求时的TYPE类型请求
    public static int TYPE_MORNING = 0; //早饭问候
    public static int TYPE_MORNING_TIME = 1; //早饭时间
    public static int TYPE_LUNCH = 2; //午饭问候
    public static int TTYPE_LUNCH_TIME = 3; //午饭时间
    public static int TYPE_DINNER = 4; //晚饭问候
    public static int TYPE_DINNER_TIME = 5; //晚饭时间
}
