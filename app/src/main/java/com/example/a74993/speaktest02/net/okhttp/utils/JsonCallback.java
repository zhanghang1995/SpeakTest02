package com.example.a74993.speaktest02.net.okhttp.utils;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;

/**
 * Created by king on 18/3/10.
 */
public abstract class JsonCallback<T> {
    public abstract void onFailure(Call call, Exception e);
    public abstract void onResponse(Call call,T object) throws IOException;

    public void onStart(){

    }
    public void onFinish(){

    }

    Type getType(){
        Type type = ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        if(type instanceof Class){
            return type;
        }else{
            return new TypeToken<T>(){}.getType();
        }
    }
}
