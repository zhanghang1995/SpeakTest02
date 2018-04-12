package com.example.a74993.speaktest02.speak;

import android.content.Context;
import android.os.Bundle;


import com.example.a74993.speaktest02.utils.Constant;
import com.example.a74993.speaktest02.utils.ToastUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;

/**
 * 用于用户语音转化的类
 * Created by king on 18/3/8.
 */

public class TextConvery {

    private Context context_this;
    private int TYPE;
    private boolean ISEND;
    private boolean HASORDER;
    private SpeakModel speak_model = new SpeakModel();

    //type表示用户语言种类，0第一次使用服务，1问候 2服务
    public void speakText(String speaktext, Context context,int type,boolean isend,boolean hasOrder){
//        Toast.makeText(this,"speak",Toast.LENGTH_SHORT).show();
        //本地合成时，设置传入参数 InitListener
        context_this = context;
        TYPE = type;
        ISEND = isend;
        HASORDER = hasOrder;
        SpeechSynthesizer speechSynthesizer = SpeechSynthesizer.createSynthesizer(context_this,null);
        speechSynthesizer.setParameter(SpeechConstant.VOICE_NAME,"xiaopin");
        speechSynthesizer.setParameter(SpeechConstant.SPEED,"40");
        speechSynthesizer.setParameter(SpeechConstant.VOLUME,"80");
        speechSynthesizer.setParameter(SpeechConstant.ENGINE_TYPE,SpeechConstant.TYPE_CLOUD);
        //设置合成音频的保存位置，保存在"./sdcard/iflytek.pcm"  the file type can only be set to pcm or wav
        //speechSynthesizer.setParameter(SpeechConstant.TTS_AUDIO_PATH,"./sdcard/iflytek.pcm");
        //start to compose
        speechSynthesizer.startSpeaking(speaktext,new MySyntheszierListener());
    }


    class MySyntheszierListener implements SynthesizerListener {
        @Override
        public void onSpeakBegin() {

        }

        @Override
        public void onSpeakPaused() {

        }

        @Override
        public void onSpeakResumed() {

        }

        @Override
        public void onBufferProgress(int i, int i1, int i2, String s) {

        }

        @Override
        public void onSpeakProgress(int i, int i1, int i2) {

        }

        @Override
        public void onCompleted(SpeechError speechError) {
            if (speechError==null&&!ISEND){
                speak_model.startSpeech(context_this, TYPE,HASORDER);
            }else if(speechError!=null){
                ToastUtils.ShowToast("讲完了",context_this);
            }
        }

        @Override
        public void onEvent(int eventType, int i1, int i2, Bundle bundle) {
            // 以下代码用于获取与云端的会话 id，当业务出错时将会话 id提供给技术支持人员，可用于查询会话日志，定位出错原因
            // 若使用本地能力，会话 id为null
            //if (SpeechEvent.EVENT_SESSION_ID == eventType) {
            //     String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
            //     Log.d(TAG, "session id =" + sid);
            //}
        }
    }

}
