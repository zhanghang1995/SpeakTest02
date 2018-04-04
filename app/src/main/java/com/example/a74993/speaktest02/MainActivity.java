package com.example.a74993.speaktest02;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.a74993.speaktest02.net.VolleyMethod;
import com.example.a74993.speaktest02.speak.SpeakModel;
import com.example.a74993.speaktest02.speak.TextConvery;
import com.example.a74993.speaktest02.speak.speak_tackle.SpeechUpload;
import com.example.a74993.speaktest02.utils.Constant;
import com.example.a74993.speaktest02.utils.LogInfo;
import com.example.a74993.speaktest02.utils.TimeUtils;
import com.example.a74993.speaktest02.utils.ToastUtils;
import com.example.a74993.speaktest02.utils.Utils;
import com.example.a74993.speaktest02.utils.VolleyApplication;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ThreadUtils threadUtils;
    private Handler handler;
    private String resultword;
    private EditText edit_input;
    private Button speak;
    private Button text;
    private HashMap<String,String> speekResult = new HashMap<String, String>();
    private SpeakModel speak_model = new SpeakModel();
    private TextConvery text_convery = new TextConvery();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initview();
        initspeech();
        initHandler();
    }

    private void initHandler() {
        /**
         * handler类处理线程的消息
         */
        //在这个线程中创建一个handler对象
          handler=new Handler(){
            public void handleMessage(Message msg){
                switch (msg.arg1) {
                    case 1:
                        threadUtils.stop();
                        break;
                    default:
                        break;
                }
            }
        };
    }

    private void initview(){
        setContentView(R.layout.activity_main);
        edit_input = findViewById(R.id.input_text);
        speak = findViewById(R.id.speak_text);
        text = findViewById(R.id.text_speech);
        speak.setOnClickListener(this);
        text.setOnClickListener(this);
        if(Utils.hasNetWork(this)){
            ToastUtils.ShowToast("有网络",this);
        }else{
            ToastUtils.ShowToast("请检查您的网络连接",this);
        }
    }

    private void initspeech(){
        SpeechUtility.createUtility(this, SpeechConstant.APPID+"=57bc2bb9");
        threadUtils = new ThreadUtils(this);
        threadUtils.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.speak_text:
                //startSpeechDialog();
                //ToastUtils.ShowToast(Utils.getUserDeviceID(this),this);
                speak_model.startSpeech(getApplicationContext(),1);
//                VolleyMethod volleyMethod = new VolleyMethod(getApplicationContext());
//                volleyMethod.teststringPost(Constant.SPEECH_UPLOAD);
                break;
            case R.id.text_speech:
                text_convery.speakText(edit_input.getText().toString(),getApplicationContext(),1);
                break;
        }
    }



    public class ThreadUtils extends Thread{
        private Context context_this;
        public ThreadUtils(Context context){
            context_this = context;
        }
        @Override
        public void run() {
            /**
             * 用于循环轮播判断
             */
            while(true) {
                if ((TimeUtils.getSystemClockUpper().toString()).equals("17:33")) {
                    SpeechUpload.upload("晚饭提醒", context_this, 0);
                    LogInfo.e("当前系统时间", (TimeUtils.getSystemClockUpper().toString().trim()));
                    LogInfo.e("Thread", "提醒服务");
                    handler.sendEmptyMessage(1);
                    break;
                }else{
                    LogInfo.e("当前系统时间", (TimeUtils.getSystemClockUpper().toString().trim()));
                }
            }
        }
    }


}
