package com.example.a74993.speaktest02.net.okhttp.exception;

/**
 * 用于生成相关的异常信息
 * Created by Administrator on 2018/4/14.
 */

public class OkHttpException extends Exception {
    private static final long serialVersionUID = 1L;
    /**
     * the server return code
     */
    private int ecode;
    /**
     * the server return erro message
     */
    private Object emsg;

    public OkHttpException(int ecode,Object emsg){
        this.ecode = ecode;
        this.emsg = emsg;
    }

    public int getEcode() {
        return ecode;
    }

    public Object getEmsg() {
        return emsg;
    }
}
