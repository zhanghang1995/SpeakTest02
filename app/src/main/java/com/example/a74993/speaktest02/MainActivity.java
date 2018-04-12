package com.example.a74993.speaktest02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.example.a74993.speaktest02.db.DbManager;
import com.example.a74993.speaktest02.model.UserTime;
import com.example.a74993.speaktest02.speak.TextConvery;
import com.example.a74993.speaktest02.utils.LogInfo;
import com.example.a74993.speaktest02.utils.ToastUtils;
import com.example.a74993.speaktest02.utils.ToolsUtils;
import com.example.a74993.speaktest02.utils.MyTimerTask;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private UserTime userTime = new UserTime();
    private DbManager dbManager;
    private EditText edit_input;
    private Button speak;
    private Button text;
    //用于数据库的连接测试,可以去掉
    private Button add_break;
    private Button add_lunch;
    private Button add_dinner;
    private TextConvery text_convery = new TextConvery();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initspeech();
        initdata();
        initview();
    }

    private void initdata() {
        //判断初始化时，是否存在当前的用户
        dbManager = new DbManager(this);
        if(!dbManager.queryUser(this)){
            dbManager.addUser( ToolsUtils.getUserDeviceID(this),"king","8:00","9:00","12:00","18:00");
        }
        //用户值初始化
        LogInfo.e("SQLITE","插入新的人员成功");
    }

    private void initschedule() {
        Timer timer = new Timer();
        MyTimerTask myTimerTask = new MyTimerTask("default",this,userTime,dbManager);
        myTimerTask.setName("起床");
        /**
         * 获取当前时间，并设置为当前时间三秒之后的时间
         *
         */
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        System.out.println("Current exec time is:"+simpleDateFormat.format(calendar.getTime()));
		calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE),7,0,0);
        timer.schedule(myTimerTask,calendar.getTime(),60000);
    }


    private void initview(){
        setContentView(R.layout.activity_main);
        edit_input = findViewById(R.id.input_text);
        speak = findViewById(R.id.speak_text);
        text = findViewById(R.id.text_speech);
        add_break = findViewById(R.id.add_break);
        add_lunch = findViewById(R.id.add_lunch);
        add_dinner = findViewById(R.id.add_dinner);
        speak.setOnClickListener(this);
        text.setOnClickListener(this);
        add_break.setOnClickListener(this);
        add_lunch.setOnClickListener(this);
        add_dinner.setOnClickListener(this);
        if(ToolsUtils.hasNetWork(this)){
            //检查用户的网络情况
            ToastUtils.ShowToast("有网络",this);
        }else{
            ToastUtils.ShowToast("请检查您的网络连接",this);
        }
        initspeech();
    }

    private void initspeech(){
        SpeechUtility.createUtility(this, SpeechConstant.APPID+"=57bc2bb9");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.speak_text:

                break;
            case R.id.text_speech:
                text_convery.speakText(edit_input.getText().toString(),getApplicationContext(),1,true,false);
                break;
            case R.id.add_break:
                userTime.setBreak_time(edit_input.getText().toString());
                break;
            case R.id.add_lunch:
                userTime.setLunch_time(edit_input.getText().toString());
                break;
            case R.id.add_dinner:
                userTime.setDinner_time(edit_input.getText().toString());
                ToastUtils.ShowToast(edit_input.getText().toString(),this);
                break;
        }
    }
}
