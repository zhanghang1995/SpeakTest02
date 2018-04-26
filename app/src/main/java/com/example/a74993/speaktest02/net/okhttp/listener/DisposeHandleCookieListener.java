package com.example.a74993.speaktest02.net.okhttp.listener;

import java.util.ArrayList;

/**
 * 当需要专门处理Cookie时创建此回掉方法
 * Created by Administrator on 2018/4/14.
 */

public interface DisposeHandleCookieListener extends DisposeDataListener
{
    public void onCookie(ArrayList<String> cookieStrLists);
}