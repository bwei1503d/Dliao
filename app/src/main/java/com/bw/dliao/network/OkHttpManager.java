package com.bw.dliao.network;

import com.bw.dliao.base.IApplication;
import com.bw.dliao.network.cookie.CookiesManager;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by muhanxi on 17/8/30.
 */

public class OkHttpManager {




    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .cookieJar(new CookiesManager(IApplication.application))
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20,TimeUnit.SECONDS)
            .writeTimeout(20,TimeUnit.SECONDS)
            .addNetworkInterceptor(new LoggingInterceptor())
            .build();

    private static volatile OkHttpManager instance = null ;

    private OkHttpManager(){

    }

    public static OkHttpManager getInstance(){
        if(instance == null){
            synchronized (OkHttpManager.class){
                if(instance == null){
                    instance = new OkHttpManager();
                }
            }
        }
        return instance;
    }

}
