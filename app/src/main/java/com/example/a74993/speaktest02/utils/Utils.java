package com.example.a74993.speaktest02.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.telephony.TelephonyManager;

import java.io.File;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 工具类，用于相关信息的处理
 * Created by 74993 on 2018/3/9.
 */

public class Utils {

    //用于判断当前的输入的字符串是否为空
    public static boolean isStringEmpty(String input) {
        if (input == null || "".equals(input))
            return true;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    //对当前的数据（密码）的MD5 加密
    public static String MD5(String s) {
        char hexDidits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] byteInput = s.getBytes();
            //获得MD5摘要算法的MessageDigest对象
            MessageDigest messageDigest = MessageDigest.getInstance("Md5");
            // 使用指定的字节更新摘要
            messageDigest.update(byteInput);
            // 获得密文
            byte[] md = messageDigest.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDidits[byte0 >>> 4 & 0xf];
                str[k++] = hexDidits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //文件存储在SD上通过该方法获取SD卡的路径  地址信息：/storage/emulated/0
    public static String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();//获取外存的目录
        }
        return sdDir.toString();
    }

    //获取当地的时间戳              时间信息：08-22 09:46:46
    public static String getDataTime(long timestamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd hh:mm:ss", Locale.CHINA);
        String date = simpleDateFormat.format(new Date(timestamp * 1000));
        return date;
    }

    //获取用户电话后直接进行打电话
    public static void intentCallTel(Context context, String phoneNUmber) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNUmber));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    //判断当前网络是否可用
    public static boolean hasNetWork(Activity activity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getApplication().getSystemService(
                Context.CONNECTIVITY_SERVICE); //判断当前是否存在网络服务
        if (connectivityManager == null) {
            return false;
        }

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo==null||!networkInfo.isAvailable()){
            return false;
        }
        return true;
    }

    //获取用户的设备信息
    public static String getUserDeviceID(Context context){
        String imeistring = null;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        try{
            if(true) {
                imeistring = telephonyManager.getDeviceId();
            }else{
                ToastUtils.ShowToast("未获取IMEI权限",context);
            }
        }catch ( SecurityException e){
            e.printStackTrace();
        }
        return imeistring;
    }
}
