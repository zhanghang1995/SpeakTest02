package com.example.a74993.speaktest02.baidunlp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 定义一般对话请求类
 * Created by Administrator on 2018/4/26.
 */

public class CommunicateResponse extends ResponseResult {
    //动作列表
    public List<CommunicateResponse.Action> actionList = new ArrayList<>();

    //解析的schema，解析意图、词槽结果都从这里获取
    public Schema schema;

    public String sessionId;


    public static class Action {
        //动作名称
        public String actionId;
        //动作详细
        public ActionType actionType;
        //参数，只当作action是做意图或词槽澄清的时候，这里才会赋值。
        public List argList = new ArrayList<>();
        //         public CodeAction codeAction;
        //结果置信度
        public int confidence;
        //保留字段
        public List exeStatusList = new ArrayList<>();
        //动作的引导选项。如果当前动作有引导，该域存在，faq问答中存在多个question需要进一步澄清时，也在此存放
        public List<String> hintList = new ArrayList<String>();
        //执行主函数
        public String mainExe;
        //返回的话术
        public String say;
    }

    public static class ActionType {
        /**
         * 动作的目标，取值intent/slot/slot_type，仅当act_type==clarify时有值；其他情况为空
         * intent： 意图澄清时
         * slot：对某个词槽，用户期望有值但返回的结果中没有填值 or 返回多个置信度接近的意图候选，且意图名相同，且出现同个词槽填了不同的值
         * slot_type：返回多个置信度接近的意图候选，且意图名相同，且出现同个值填到了不同的词槽
         */
        public String target;
        //动作目标详细内容，仅当act_type==clarify且act_target==slot时填词槽名称；其他情况为空
        public String targetDetail;
        /**
         * 动作类型，取值clarify/satisfy/guide
         * clarify： 澄清
         * satisfy： 满足
         * guide： 引导
         * faqguide： faq引导
         */
        public String type;
        //默认空值，动作类型的详细内容。
        public String typeDetail;
    }

    // public static class CodeAction {}

    public static class Schema {
        //词槽的列表
        public List botMergedSlots = new ArrayList();
        //定义当前的意图
        public String currentQueryInent;
        //意图的置信度
        public int intentConfidence;
    }
}
