package com.example.a74993.speaktest02.model;

/**
 * Created by Administrator on 2018/4/8.
 * 定义用户的早饭，午饭，晚饭时间信息
 */

public class UserTime {
    private String getup_time;

    public String getGetup_time() {
        return getup_time;
    }

    public void setGetup_time(String getup_time) {
        this.getup_time = getup_time;
    }

    private String break_time;
    private String lunch_time;
    private String dinner_time;

    public String getBreak_time() {
        return break_time;
    }

    public void setBreak_time(String break_time) {
        this.break_time = break_time;
    }

    public String getLunch_time() {
        return lunch_time;
    }

    public void setLunch_time(String lunch_time) {
        this.lunch_time = lunch_time;
    }

    public String getDinner_time() {
        return dinner_time;
    }

    public void setDinner_time(String dinner_time) {
        this.dinner_time = dinner_time;
    }
}
