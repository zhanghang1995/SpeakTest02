package com.example.a74993.speaktest02.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.a74993.speaktest02.model.User;
import com.example.a74993.speaktest02.utils.Constant;
import com.example.a74993.speaktest02.utils.LogInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/1.
 */

public class DbManager {
    private DbHelper helper;
    private SQLiteDatabase db;

    public DbManager(Context context)
    {
        LogInfo.e("SQLITE","DBManager --> Constructor");
        helper = new DbHelper(context);
        // 因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0,
        // mFactory);
        // 所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
        db = helper.getWritableDatabase();
    }

    /**
     * add persons
     *
     * @param user
     */
    public void add(List<User> user)
    {
        LogInfo.e("SQLITE","DBManager --> add");
        // 采用事务处理，确保数据完整性
        db.beginTransaction(); // 开始事务
        try
        {
            for (User person : user)
            {
                db.execSQL("INSERT INTO " + Constant.USERTABLE
                        + " VALUES(null, ?, ?, ?)", new Object[] { person.username,
                        person.password, person.gender });
                // 带两个参数的execSQL()方法，采用占位符参数？，把参数值放在后面，顺序对应
                // 一个参数的execSQL()方法中，用户输入特殊字符时需要转义
                // 使用占位符有效区分了这种情况
            }
            db.setTransactionSuccessful(); // 设置事务成功完成
        }
        finally
        {
            db.endTransaction(); // 结束事务
        }
    }

    /**
     * update person's age
     *
     * @param user
     */
    public void updateAge(User user)
    {
        LogInfo.e("SQLITE","DBManager --> updateAge");
        ContentValues cv = new ContentValues();
        cv.put("username", user.username);
        db.update(Constant.USERTABLE, cv, "name = ?",
                new String[] { user.username });
    }

    /**
     * delete old person
     *
     * @param user
     */
    public void deleteOldPerson(User user)
    {
        LogInfo.e("SQLITE","DBManager --> deleteOldPerson");
        db.delete(Constant.USERTABLE, "username == ?",
                new String[] { String.valueOf(user.username) });
    }

    /**
     * query all persons, return list
     *
     * @return List<Person>
     */
    public List<User> query()
    {
        LogInfo.e("SQLITE","DBManager --> query");
        ArrayList<User> persons = new ArrayList<User>();
        Cursor c = queryTheCursor();
        while (c.moveToNext())
        {
            User user = new User();
            user.username = c.getString(c.getColumnIndex("name"));
            user.password = c.getString(c.getColumnIndex("age"));
            user.gender = c.getInt(c.getColumnIndex("info"));
            persons.add(user);
        }
        c.close();
        return persons;
    }

    /**
     * query all persons, return cursor
     *
     * @return Cursor
     */
    public Cursor queryTheCursor()
    {
        LogInfo.e("SQLITE","DBManager --> queryTheCursor");
        Cursor c = db.rawQuery("SELECT * FROM " + DbHelper.TABLE_NAME,
                null);
        return c;
    }

    /**
     * close database
     */
    public void closeDB()
    {
        LogInfo.e("SQLITE","DBManager --> closeDB");
        // 释放数据库资源
        db.close();
    }
}
