package com.example.a74993.speaktest02.baidunlp.model;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/26.
 */

public class DynamicParams implements RequestParams {
    private Map<String, String> params = new HashMap<>();
    private Map<String, File> fileMap = new HashMap<>();

    @Override
    public Map<String, File> getFileParams() {
        return fileMap;
    }

    @Override
    public Map<String, String> getStringParams() {
        return params;
    }

    public void putParam(String key, String value) {
        if (value != null) {
            params.put(key, value);
        }
    }

    public void putParam(String key, int value) {

        params.put(key, String.valueOf(value));
    }

    public void putParam(String key, boolean value) {
        if (value) {
            putParam(key, "true");
        } else {
            putParam(key, "false");
        }
    }

    public void putFile(String key, File file) {
        fileMap.put(key, file);
    }
}
