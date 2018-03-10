package com.example.a74993.speaktest02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.example.a74993.speaktest02.speak.SpeakModel;
import com.example.a74993.speaktest02.speak.TextConvery;
import com.example.a74993.speaktest02.utils.ToastUtils;
import com.example.a74993.speaktest02.utils.Utils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.speak_text:
                //startSpeechDialog();
                ToastUtils.ShowToast(Utils.getUserDeviceID(this),this);
                speak_model.startSpeech(getApplicationContext());
                break;
            case R.id.text_speech:
                text_convery.speakText(edit_input.getText().toString(),getApplicationContext());
                break;
        }
    }
}
