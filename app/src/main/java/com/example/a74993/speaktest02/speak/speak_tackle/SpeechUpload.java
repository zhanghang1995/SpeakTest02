package com.example.a74993.speaktest02.speak.speak_tackle;

import android.content.Context;

import com.example.a74993.speaktest02.MainActivity;
import com.example.a74993.speaktest02.net.VolleyMethod;
import com.example.a74993.speaktest02.utils.Constant;

/**
 * 用户语言信息处理调用接口
 * Created by 74993 on 2018/3/9.
 */

public class SpeechUpload {
    public static void upload(String speakwords,Context context,int type,boolean hasOrder)
    {
        VolleyMethod volleyMethod = new VolleyMethod(context);
        volleyMethod.stringPost(Constant.SPEECH_UPLOAD,speakwords,type,hasOrder);
    }
}
