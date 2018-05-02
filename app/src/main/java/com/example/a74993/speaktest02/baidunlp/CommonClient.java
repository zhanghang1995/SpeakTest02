package com.example.a74993.speaktest02.baidunlp;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.a74993.speaktest02.baidunlp.exception.UnitError;
import com.example.a74993.speaktest02.baidunlp.listener.OnResultListener;
import com.example.a74993.speaktest02.baidunlp.model.AccessToken;
import com.example.a74993.speaktest02.baidunlp.model.CommunicateResponse;
import com.example.a74993.speaktest02.baidunlp.model.Scene;
import com.example.a74993.speaktest02.model.Hint;
import com.example.a74993.speaktest02.model.Message;
import com.example.a74993.speaktest02.model.User;
import com.example.a74993.speaktest02.utils.constant.NormalConstant;

import org.apache.log4j.spi.NOPLogger;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 此处提供各种用于百度云SDK服务的接口
 * Created by Administrator on 2018/4/20.
 */

public class CommonClient {
    public CommonClient(Context context) {
        this.context = context;
    }

    /**
     * 定义信息发送的实体
     */
    private Context context;
    private Scene curScene;
    /*标记一次会话，新建一个会话时，session_id可不传或置空；服务端会返回唯一id。如需保持多轮会话，再次请求时传入该id
    请求示例代码*/
    private String sessionId = "";
    private int id = 0;
    private String accessToken;
    private User sender;
    private User cs;

    private ListView dataListview;
    private List<Scene> dataList;
    private List<Message> waitList = new ArrayList<>();
    private Map<Integer, String> sceneMap = new HashMap<Integer, String>();


    public void initData() {
        sender = new User("0", "king", "", true);
        cs = new User("01", "客服", "", true);
        sceneMap.put(NormalConstant.SCENE_PLAY_BOT, "智能指令识别");

        dataList = new ArrayList<Scene>();
        dataList.add(new Scene(NormalConstant.SCENE_PLAY_BOT, "智能指令识别"));
    }

    /**
     * 出于安全考虑，此处获取access_token
     */
    public void initAccessToken() {
        APIService.getInstance().init(context);
        APIService.getInstance().initAccessToken(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken result) {
                accessToken = result.getAccessToken();
                Log.i("MainActivity", "AccessToken->" + result.getAccessToken());
                if (!TextUtils.isEmpty(accessToken)) {
                    resendWaitList();
                }
            }

            @Override
            public void onError(UnitError unitError) {

            }
        }, "", "");
    }

    /**
     * 重新发送未发送的消息
     */

    private void resendWaitList() {
        for (Message message : waitList) {
            sendMessage(message);
        }
    }

    /**
     * 发送消息
     */
    private void sendMessage(Message message) {
        if (TextUtils.isEmpty(accessToken)) {
            waitList.add(message);
            return;
        }

        APIService.getInstance().communicate(new OnResultListener<CommunicateResponse>() {
            @Override
            public void onResult(CommunicateResponse result) {
                handleResponce(result);
            }

            @Override
            public void onError(UnitError unitError) {

            }
        }, curScene.getId(), message.getText(), sessionId);
    }

    private void handleResponce(CommunicateResponse result) {
        if (result != null) {
            //获取当前的当前会话的ID
            sessionId = result.sessionId;
            List<CommunicateResponse.Action> actionList = result.actionList;
            if (actionList.size() > 1) {
                Message message = new Message(String.valueOf(id++), cs, "", new Date());
                for (CommunicateResponse.Action action : actionList) {
                    if (!TextUtils.isEmpty(action.say)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(action.say);

                        Message actionMessage = new Message("", cs, sb.toString(), new Date());

                        for (String hintText : action.hintList) {

                            actionMessage.getHintList().add(new Hint(hintText));
                        }
                        message.getComplexMessage().add(actionMessage);
                    }
                }
            } else if (actionList.size() == 1) {
                CommunicateResponse.Action action = actionList.get(0);
                if (!TextUtils.isEmpty(action.say)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(action.say);

                    Message message = new Message(String.valueOf(id++), cs, sb.toString(), new Date());
                    for (String hintText : action.hintList) {
                        message.getHintList().add(new Hint(hintText));
                    }

                }

                //此处根据actoin的actionId进行判断得出，相应的操作
                // 执行自己的业务逻辑
                if ("start_work_satisfy".equals(action.actionId)) {
                    Log.i("wtf", "开始扫地");
                } else if ("stop_work_satisfy".equals(action.actionId)) {
                    Log.i("wtf", "停止工作");
                } else if ("move_action_satisfy".equals(action.actionId)) {
                    Log.i("wtf", "移动");
                } else if ("timed_charge_satisfy".equals(action.actionId)) {
                    Log.i("wtf", "定时充电");
                } else if ("timed_task_satisfy".equals(action.actionId)) {
                    Log.i("wtf", "定时扫地");
                } else if ("sing_song_satisfy".equals(action.actionId)) {
                    Log.i("wtf", "唱歌");
                }

                if (!TextUtils.isEmpty(action.mainExe)) {
                    //执行相应的函数模块
                }
            }

        }
    }
}
