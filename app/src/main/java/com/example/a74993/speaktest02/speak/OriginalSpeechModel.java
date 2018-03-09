package com.example.a74993.speaktest02.speak;

import android.content.Context;
import android.widget.EditText;


import com.example.a74993.speaktest02.utils.JsonParser;
import com.example.a74993.speaktest02.utils.ToastUtils;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by king on 18/3/8.
 */

public class OriginalSpeechModel {
    //speak to text
    private Context context_this;
    private EditText edit_input;
    private HashMap<String,String> speekResult = new HashMap<String, String>();
    private void startSpeechDialog(EditText edit, Context context){
        context_this = context;
        edit_input = edit;
        RecognizerDialog recognizerDialog = new RecognizerDialog(context,new MyInitListener());
        recognizerDialog.setParameter(SpeechConstant.LANGUAGE,"zh_cn");
        recognizerDialog.setParameter(SpeechConstant.ACCENT,"mandarin");
        // 若要将UI控件用于语义理解，必须添加以下参数设置，设置之后 onResult回调返回将是语义理解
        // 结果
        // mDialog.setParameter("asr_sch", "1");
        // mDialog.setParameter("nlp_version", "2.0");
        //设置回调接口
        recognizerDialog.setListener(new MyRecognizerDialogListener());
        //显示dialog用于语音输入
        recognizerDialog.show();
    }

    class MyInitListener implements InitListener{
        @Override
        public void onInit(int i) {
            if(i!= ErrorCode.SUCCESS){
                ToastUtils.ShowToast("初始化失败",context_this);
            }
        }
    }

    class MyRecognizerDialogListener implements RecognizerDialogListener {
        /**
         * @param recognizerResult
         * @param isLast whether is end
         */
        @Override
        public void onResult(RecognizerResult recognizerResult, boolean isLast) {

            String result = recognizerResult.getResultString();// undealed
            ToastUtils.ShowToast(result,context_this);
            System.out.println("undealed"+result);

            String text = JsonParser.parseIatResult(result);
            System.out.println("dealed"+text);

            String sn = null;
            try{
                JSONObject resultJson = new JSONObject(recognizerResult.getResultString());
                sn = resultJson.optString("cn");

            }catch (JSONException e){
                e.printStackTrace();
            }

            speekResult.put(sn,text);//每得到一句话，就添加到hashmap中
            StringBuffer resultbuffer = new StringBuffer();
            for (String key:speekResult.keySet()){
                resultbuffer.append(speekResult.get(key));
            }
            edit_input.setText(resultbuffer.toString());
            edit_input.setSelection(edit_input.length());
        }

        @Override
        public void onError(SpeechError speechError) {
            //错误调用接口
        }
    }
}
