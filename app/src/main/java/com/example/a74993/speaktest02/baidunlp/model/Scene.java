package com.example.a74993.speaktest02.baidunlp.model;

/**
 * 定义当前回话场景的信息
 * Created by Administrator on 2018/4/25.
 */

public class Scene {
    /**
     * 场景编号
     */
    private int id;
    /**
     * 场景名称
     */
    private String name;

    public Scene(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
