package com.example.a74993.speaktest02.baidunlp.utils;

import com.baidu.aip.nlp.AipNlp;
import com.example.a74993.speaktest02.utils.constant.NormalConstant;

/**
 * 向外提供统一的调用百度自然语言处理接口
 * Created by Administrator on 2018/4/21.
 */

public class InitialAipNlp {
    private static AipNlp client;
    private static InitialAipNlp initialAipNlp;
    /**
     * 单列模式，懒汉模式
     * 定义全局唯一的AipNlp连接对象
     */

    private InitialAipNlp(){
        //1.初始化一个AipNlp
        client = new AipNlp(NormalConstant.API_ID,NormalConstant.API_KEY,NormalConstant.SECRET_KEY);
        //2.设置网络连接参数，建立连接的超时时间 通过打开的连接传输数据的超时时间
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(6000);

        //可选:设置代理服务器地址，http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host",);
//        client.setSocketProxy("proxy_host",);

        //可选，设置log4j日志输出模式，若不设置，则使用默认配置，也可以直接通过jvm启动参数设置此环境变量
//        System.setProperties("api.log4j.conf","pa");
    }

    public static AipNlp getInstance(){
        if (client==null){
            initialAipNlp = new InitialAipNlp();
        }
        return client;
    }

}
