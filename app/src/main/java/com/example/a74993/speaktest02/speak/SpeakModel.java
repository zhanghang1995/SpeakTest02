package com.example.a74993.speaktest02.speak;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;


import com.example.a74993.speaktest02.MainActivity;
import com.example.a74993.speaktest02.speak.speak_tackle.SpeechUpload;
import com.example.a74993.speaktest02.utils.Constant;
import com.example.a74993.speaktest02.utils.JsonParser;
import com.example.a74993.speaktest02.utils.ToastUtils;
import com.example.a74993.speaktest02.utils.Utils;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;

/**
 * 用于用户语音识别的类
 * Created by king on 18/3/8.
 */

public class SpeakModel {
    private String speakresult = "";
    private Context context_this;
    private int TYPE;
    /**
     *语音识别
     */
    public void startSpeech(Context context,int type){
        TYPE = type;
        context_this  = context;
        //1. 创建SpeechRecognizer对象，第二个参数： 本地识别时传 InitListener
        SpeechRecognizer speechRecognizer = SpeechRecognizer.createRecognizer( context, null); //语音识别器
        //2. 设置听写参数，详见《 MSC Reference Manual》 SpeechConstant类
        speechRecognizer.setParameter(SpeechConstant. DOMAIN, "iat" );// 短信和日常用语： iat (默认)
        speechRecognizer.setParameter(SpeechConstant. LANGUAGE, "zh_cn" );// 设置中文
        speechRecognizer.setParameter(SpeechConstant. ACCENT, "mandarin" );// 设置普通话
        speechRecognizer.setParameter(SpeechConstant.RESULT_TYPE,"json");//返回格式
        speechRecognizer.setParameter(SpeechConstant.ENGINE_TYPE,SpeechConstant.TYPE_CLOUD);//听写引擎
        speechRecognizer.setParameter(SpeechConstant.VAD_BOS,"3000");//设置多久不说话，当作超时处理,语音前
        speechRecognizer.setParameter(SpeechConstant.VAD_EOS,"1");//设置说话后多久不说话，自动停止
        speechRecognizer.setParameter(SpeechConstant.ASR_PTT, "0");//是否动态修正结果， 1为动态
        speechRecognizer.setParameter(SpeechConstant.ASR_DWA, "0");
        //3. 开始听写
        speechRecognizer.startListening( mRecoListener);
    }

    // 听写监听器
    private RecognizerListener mRecoListener = new RecognizerListener() {
        // 听写结果回调接口 (返回Json 格式结果，用户可参见附录 13.1)；
        //一般情况下会通过onResults接口多次返回结果，完整的识别内容是多次结果的累加；
        //关于解析Json的代码可参见 Demo中JsonParser 类；
        //isLast等于true 时会话结束。
        public void onResult(RecognizerResult results, boolean isLast) {

            speakresult = speakresult + JsonParser.parseIatResult(results.getResultString());
            if(isLast&&TYPE==0){
                /**
                 * 用户语音的逻辑判断,在后台判断并且返回
                 */
                SpeechUpload.upload(speakresult,context_this,TYPE);
                speakresult = "";
//                ToastUtils.ShowToast("手机识别"+speakresult,context_this);
//                System.out.println(Utils.getDataTime(20180806));
//               用于调用手机的电话    Utils.intentCallTel(context_this,speakresult);
            }
        }

        // 会话发生错误回调接口
        public void onError(SpeechError error) {
            ToastUtils.ShowToast(error.getPlainDescription(true),context_this);
            // 获取错误码描述
            Log. e(Constant.TAG, "error.getPlainDescription(true)==" + error.getPlainDescription(true ));
        }

        // 开始录音
        public void onBeginOfSpeech() {
            //showTip(" 开始录音 ");
        }

        //volume 音量值0~30， data音频数据
        public void onVolumeChanged(int volume, byte[] data) {
            //showTip(" 声音改变了 ");
        }

        // 结束录音
        public void onEndOfSpeech() {
            //showTip(" 结束录音 ");
        }

        // 扩展用接口
        public void onEvent(int eventType, int arg1 , int arg2, Bundle obj) {

        }
    };

}
