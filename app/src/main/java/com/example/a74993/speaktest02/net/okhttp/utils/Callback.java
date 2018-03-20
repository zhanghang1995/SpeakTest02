package com.example.a74993.speaktest02.net.okhttp.utils;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by king on 18/3/20.
 */
public abstract class Callback implements okhttp3.Callback {
    public void onStart(){

    }
    public void onFinish(){

    }

    public abstract void onFailure(Call call, IOException e);

    public abstract void onResponse(Call call, Response response) throws IOException;
}
