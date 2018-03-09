package com.example.a74993.speaktest02.model;

/**
 * 用户类模块
 * Created by 74993 on 2018/3/9.
 */

public class User {
    private static String username = "";
    private static String password = "";
    private static int gender = 0;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        User.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        User.password = password;
    }

    public static int getGender() {
        return gender;
    }

    public static void setGender(int gender) {
        User.gender = gender;
    }
}
