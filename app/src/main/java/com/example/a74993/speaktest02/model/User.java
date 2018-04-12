package com.example.a74993.speaktest02.model;

import com.example.a74993.speaktest02.db.DbManager;

/**
 * 用户类模块，定义用户的各种信息
 * Created by 74993 on 2018/3/9.
 */

public class User {
    //用户姓名
    public  String username = "";
    //用户性别
    public  int gender = 0;
    //用户密码
    public  String password = "";
    //用户起床时间
    public String getup = "";
    //用户早餐类型
    public String breakfast = "";
    //用户读报
    public String readnews = "";
    //新闻推送类型
    public String newspush = "";
    //用户备忘信息
    public String cheat = "";
    //用户上班路上
    public String gowork = "";
    //用户工作节点
    public String work = "";
    //用户午餐
    public String lunch = "";
    //用户午餐类型
    public String lunchType = "";
    //用户工作安排
    public String workArrangement = "";
    //用户工作总结
    public String workSummary = "";
    //用户晚餐定制
    public String dinner = "";
    //用户晚上活动安排
    public String eveActivities = "";
    //用户睡眠时间
    public String sleep = "";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGetup() {
        return getup;
    }

    public void setGetup(String getup) {
        this.getup = getup;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getReadnews() {
        return readnews;
    }

    public void setReadnews(String readnews) {
        this.readnews = readnews;
    }

    public String getNewspush() {
        return newspush;
    }

    public void setNewspush(String newspush) {
        this.newspush = newspush;
    }

    public String getCheat() {
        return cheat;
    }

    public void setCheat(String cheat) {
        this.cheat = cheat;
    }

    public String getGowork() {
        return gowork;
    }

    public void setGowork(String gowork) {
        this.gowork = gowork;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getLunchType() {
        return lunchType;
    }

    public void setLunchType(String lunchType) {
        this.lunchType = lunchType;
    }

    public String getWorkArrangement() {
        return workArrangement;
    }

    public void setWorkArrangement(String workArrangement) {
        this.workArrangement = workArrangement;
    }

    public String getWorkSummary() {
        return workSummary;
    }

    public void setWorkSummary(String workSummary) {
        this.workSummary = workSummary;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }

    public String getEveActivities() {
        return eveActivities;
    }

    public void setEveActivities(String eveActivities) {
        this.eveActivities = eveActivities;
    }

    public String getSleep() {
        return sleep;
    }

    public void setSleep(String sleep) {
        this.sleep = sleep;
    }
}