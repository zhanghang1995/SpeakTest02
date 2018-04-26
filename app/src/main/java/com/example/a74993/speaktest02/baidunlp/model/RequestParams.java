package com.example.a74993.speaktest02.baidunlp.model;

import java.io.File;
import java.util.Map;

/**
 * 定义request请求的参数接口
 * Created by Administrator on 2018/4/26.
 */

public interface RequestParams {
    Map<String,File>  getFileParams();
    Map<String,String> getStringParams();
}
