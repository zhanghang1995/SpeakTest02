package com.example.a74993.speaktest02.utils;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

/**\
 * 用于给bitmap或者当前应用操作的缓存分配
 * Created by 74993 on 2018/3/10.
 */

public class KaleApplicatipon extends Application{
    /**
     * @description
     * @return 得到当前需要分配的缓存的大小，这里使用八分之一进行分配
     */
    public int getMemoryCacheSize(){
        //Get memory class of this device ,exceeding this amount will throw an OutOfMemory exception
        final int memoryClass = ((ActivityManager)getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
        return 1024*1024*memoryClass/8;
    }
}
