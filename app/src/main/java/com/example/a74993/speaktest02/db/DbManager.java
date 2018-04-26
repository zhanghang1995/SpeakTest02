package com.example.a74993.speaktest02.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.a74993.speaktest02.utils.constant.NormalConstant;
import com.example.a74993.speaktest02.utils.LogInfo;
import com.example.a74993.speaktest02.utils.ToolsUtils;

/**
 * 用于数据库的管理类，数据库的增、删、改、查
 * Created by Administrator on 2018/4/1.
 */

public class DbManager {
    private DbHelper helper;
    private SQLiteDatabase db;

    public DbManager(Context context) {
        LogInfo.e("SQLITE", "DBManager --> Constructor");
        helper = new DbHelper(context);
        // 因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0,
        // mFactory);
        // 所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
        db = helper.getWritableDatabase();
    }

    /**
     * 向数据库中添加新的成员信息，当用户一次是使用时候,进行添加
     * 进行用户值得初始化操作
     */
    public void addUser(String deviceId, String username, String getuptime, String breaktime, String lunchtime, String dinnertime) {
        LogInfo.e("SQLITE", "数据库成员添加---》");
        // 采用事务处理，确保数据完整性
        db.beginTransaction(); // 开始事务
        try {
            db.execSQL("INSERT INTO " + NormalConstant.USERTABLE + "(username,deviceId,getuptime,breaktime,lunchtime,dinnertime)"
                    + " VALUES(" + "'" + username + "'" + "," + "'" + deviceId + "'" + "," + "'" + getuptime + "'" + "," + "'" + breaktime + "'" + "," + "'" + lunchtime + "'" + "," + "'" + dinnertime + "'" + ")");
            // 带两个参数的execSQL()方法，采用占位符参数？，把参数值放在后面，顺序对应
            // 一个参数的execSQL()方法中，用户输入特殊字符时需要转义
            // 使用占位符有效区分了这种情况
            LogInfo.e("SQLITE", "数据库成员添加->>> 成功");
            db.setTransactionSuccessful(); // 设置事务成功完成
        } finally {
            db.endTransaction(); // 结束事务
        }
    }

    /**
     * add time where user == username
     * type:表示插入数据表的列名 value表示插入对应数据的value值
     */
    public void updateTime(String type, String value, String device_id, Context context) {
        LogInfo.e("SQLITE", "DBManager --> add");
        // 采用事务处理，确保数据完整性
        db.beginTransaction(); // 开始事务
        try {
            db.execSQL("UPDATE " + NormalConstant.USERTABLE + " SET" + "'" + type + "'" + "=" + "'" + value + "'" + " WHERE deviceId = " + "'" + ToolsUtils.getUserDeviceID(context) + "'");
            // 带两个参数的execSQL()方法，采用占位符参数？，把参数值放在后面，顺序对应
            // 一个参数的execSQL()方法中，用户输入特殊字符时需要转义
            // 使用占位符有效区分了这种情况
            LogInfo.e("SQLITE", "数据库成员用户时间添加->>> 成功");
            db.setTransactionSuccessful(); // 设置事务成功完成
        } finally {
            db.endTransaction(); // 结束事务
        }
    }

    /**
     * update person's dishtype
     * type:表示插入数据库表中的列名 value表示插入对应数据的value值
     */
    public void updateType(String type, String value, String device_id, Context context) {
        LogInfo.e("SQLITE", "DBManager --> add");
        // 采用事务处理，确保数据完整性
        db.beginTransaction(); // 开始事务
        try {
            db.execSQL("UPDATE " + NormalConstant.USERTABLE + " SET" + "'" + type + "'" + "=" + "'" + value + "'" + " WHERE deviceId = " + "'" + ToolsUtils.getUserDeviceID(context) + "'");
            // 带两个参数的execSQL()方法，采用占位符参数？，把参数值放在后面，顺序对应
            // 一个参数的execSQL()方法中，用户输入特殊字符时需要转义
            // 使用占位符有效区分了这种情况
            LogInfo.e("SQLITE", "数据库成员菜名添加->>> 成功");
            db.setTransactionSuccessful(); // 设置事务成功完成
        } finally {
            db.endTransaction(); // 结束事务
        }
    }

    /**
     * 查询用户的预设提醒时间
     * 每次用户进行APP刷新时，从缓存中读入用户的时间信息进行更新
     *
     * @return Cursor
     */
    public String queryTheTime(Context context, int type) {
        LogInfo.e("SQLITE", "DBManager --> queryTheCursor");
        Cursor c = db.query(DbHelper.TABLE_NAME, null,
                "deviceId =" + "'" + ToolsUtils.getUserDeviceID(context) + "'", null, null, null, null);
        while (c.moveToNext()) {
            if (type == 1) {
                int breaktime = c.getColumnIndex("breaktime");
                String time = c.getString(breaktime);
                return time;
            } else if (type == 2) {
                int lunch_time = c.getColumnIndex("lunchtime");
                String lunchtime = c.getString(lunch_time);
                return lunchtime;
            } else if (type == 3) {
                int dinner_time = c.getColumnIndex("dinnertime");
                String dinnertime = c.getString(dinner_time);
                return dinnertime;
            } else if (type == 4) {
                int getup_time = c.getColumnIndex("getuptime");
                String getuptime = c.getString(getup_time);
                return getuptime;
            } else {
                return null;
            }
        }
        c.close();
        return null;
    }


    /**
     * 查询用户的预设提醒菜名
     * 每次用户进行APP刷新时，从缓存中读入用户的菜名信息进行更新
     *
     * @return Cursor
     */
    public boolean queryTheType(Context context, String type) {
        String result = null;
        LogInfo.e("SQLITE", "DBManager --> queryTheCursor");
        Cursor c = db.query(DbHelper.TABLE_NAME, null,
                "deviceId =" + "'" + ToolsUtils.getUserDeviceID(context) + "'", null, null, null, null);
        if (c.moveToNext()) {
            int breaktype = c.getColumnIndex(type);
            String data = c.getString(breaktype);
            result = data;
        }
        c.close();
        return result == null ? false : true;
    }


    /**
     * 用于判断数据库中是否存在此用户
     *
     * @return Cursor
     */
    public boolean queryUser(Context context) {
        boolean flag = false;
        LogInfo.e("SQLITE", "DBManager --> queryTheCursor");
        Cursor c = db.query(DbHelper.TABLE_NAME, null,
                "deviceId =" + "'" + ToolsUtils.getUserDeviceID(context) + "'", null, null, null, null);
        while (c.moveToNext()) {
            flag = true;
        }
        c.close();
        return flag;
    }

    /**
     * close database
     */
    public void closeDB() {
        LogInfo.e("SQLITE", "DBManager --> closeDB");
        // 释放数据库资源
        db.close();
    }
}
