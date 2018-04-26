package com.example.a74993.speaktest02.baidunlp.listener;

import com.example.a74993.speaktest02.baidunlp.exception.UnitError;

/**
 * 请求百度UNIT返回接口的回掉
 * Created by Administrator on 2018/4/26.
 */

public interface OnResultListener<T> {
    void onResult(T result);

    void onError(UnitError unitError);
}
