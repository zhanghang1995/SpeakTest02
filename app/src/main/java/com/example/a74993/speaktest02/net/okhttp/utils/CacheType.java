package com.example.a74993.speaktest02.net.okhttp.utils;

import android.support.annotation.IntDef;

/**
 * Created by king on 18/3/20.
 */
@IntDef({CacheType.ONLY_NETWORK,CacheType.ONLY_CACHED,CacheType.CACHED_ELSE_NETWORK,CacheType.NETWORK_ELSE_CACHED})
public @interface CacheType {
    int ONLY_NETWORK = 0;
    int ONLY_CACHED = 1;
    int CACHED_ELSE_NETWORK =2;
    int NETWORK_ELSE_CACHED = 3;


}
