package com.example.a74993.speaktest02.net.okhttp.response;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;


import com.example.a74993.speaktest02.net.okhttp.exception.OkHttpException;
import com.example.a74993.speaktest02.net.okhttp.listener.DisposeDataHandler;
import com.example.a74993.speaktest02.net.okhttp.listener.DisposeDataListener;
import com.example.a74993.speaktest02.net.okhttp.listener.DisposeHandleCookieListener;
import com.example.a74993.speaktest02.net.okhttp.utils.ResponseEntityToModule;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Response;

/**
 * 专门处理JSON回掉方法
 * Created by Administrator on 2018/4/14.
 */
public class CommonJsonCallback implements Callback {

    /**
     * the logic layer exception, may alter in different app
     */
    protected final String RESULT_CODE = "ecode"; // 有返回则对于http请求来说是成功的，但还有可能是业务逻辑上的错误
    protected final int RESULT_CODE_VALUE = 0;
    protected final String ERROR_MSG = "emsg";
    protected final String EMPTY_MSG = "";
    protected final String COOKIE_STORE = "Set-Cookie"; // decide the server it
    // can has the value of
    // set-cookie2

    /**
     * the java layer exception, do not same to the logic error
     */
    protected final int NETWORK_ERROR = -1; // the network relative error
    protected final int JSON_ERROR = -2; // the JSON relative error
    protected final int OTHER_ERROR = -3; // the unknow error

    /**
     * 将其它线程的数据转发到UI线程
     */
    private Handler mDeliveryHandler;
    private DisposeDataListener mListener;
    private Class<?> mClass;

    public CommonJsonCallback(DisposeDataHandler handle) {
        this.mListener = handle.disposeDataListener;
        this.mClass = handle.mClass;
        this.mDeliveryHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onFailure(final Call call, final IOException ioexception) {
        /**
         * 此时还在非UI线程，因此要转发
         */
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFailure(new OkHttpException(NETWORK_ERROR, ioexception));
            }
        });
    }

    @Override
    public void onResponse(final Call call, final Response response) throws IOException {
        final String result = response.body().string();
        final ArrayList<String> cookieLists = handleCookie(response.headers());
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                handleResponse(result);
                /**
                 * handle the cookie
                 */
                if (mListener instanceof DisposeHandleCookieListener) {
                    ((DisposeHandleCookieListener) mListener).onCookie(cookieLists);
                }
            }
        });
    }

    private ArrayList<String> handleCookie(Headers headers) {
        ArrayList<String> tempList = new ArrayList<String>();
        for (int i = 0; i < headers.size(); i++) {
            if (headers.name(i).equalsIgnoreCase(COOKIE_STORE)) {
                tempList.add(headers.value(i));
            }
        }
        return tempList;
    }

    private void handleResponse(Object responseObj) {
        if (responseObj == null) {
            mListener.onFailure(new OkHttpException(NETWORK_ERROR, EMPTY_MSG));
            return;
        }

        try {
            JSONObject result = new JSONObject(responseObj.toString());
            Log.i("返回消息",responseObj.toString());
            if (result.has(RESULT_CODE)) {
                if (result.optInt(RESULT_CODE) == RESULT_CODE_VALUE) {
                    if (mClass == null) {
                        mListener.onSuccess(result);
                    } else {
                        Object obj = ResponseEntityToModule.parseJsonObjectToModule(result, mClass);
                        if (obj != null) {
                            //走到此处，我们的响应才可以真正的处理，并且直接返回了实体对象
                            mListener.onSuccess(obj);
                        } else {
                            mListener.onFailure(new OkHttpException(JSON_ERROR, EMPTY_MSG));
                        }
                    }
                } else {
                    if (result.has(ERROR_MSG)) {
                        mListener.onFailure(
                                new OkHttpException(result.optInt(RESULT_CODE), result.optString(ERROR_MSG)));
                    } else {
                        mListener.onFailure(new OkHttpException(result.optInt(RESULT_CODE), EMPTY_MSG));
                    }
                }
            } else {
                if (result.has(ERROR_MSG)) {
                    mListener.onFailure(new OkHttpException(OTHER_ERROR, result.optString(ERROR_MSG)));
                }
            }
        } catch (Exception e) {
            mListener.onFailure(new OkHttpException(OTHER_ERROR, e.getMessage()));
            e.printStackTrace();
        }
    }
}