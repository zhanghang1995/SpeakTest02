package com.example.a74993.speaktest02.baidunlp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 定义一般对话请求类
 * Created by Administrator on 2018/4/26.
 */

public class CommunicateResponse extends ResponseResult{

    public List<CommunicateResponse.Action> actionList = new ArrayList<>();

    public Schema schema;

    public String sessionId;


    public static class Action {
        public String actionId;
        public ActionType actionType;
        public List argList = new ArrayList<>();
        // public CodeAction codeAction;
        public int confidence;
        public List exeStatusList = new ArrayList<>();
        public List<String> hintList = new ArrayList<String>();
        public String mainExe;
        public String say;
    }

    public static class ActionType {
        public String target;
        public String targetDetail;
        public String type;
        public String typeDetail;
    }

    // public static class CodeAction {}

    public static class Schema {
        public List botMergedSlots = new ArrayList();
        public String currentQueryInent;
        public int intentConfidence;
    }
}
