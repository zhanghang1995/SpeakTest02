package com.example.a74993.speaktest02.baidunlp.model;

import java.io.File;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/26.
 */

public class DynamicParams implements RequestParams {

    @Override
    public Map<String, File> getFileParams() {
        return null;
    }

    @Override
    public Map<String, String> getStringParams() {
        return null;
    }
}
