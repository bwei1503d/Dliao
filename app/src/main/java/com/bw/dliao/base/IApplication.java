package com.bw.dliao.base;

import android.app.Application;


/**
 * Created by muhanxi on 17/6/19.
 */

public class IApplication extends Application {


    public static IApplication application ;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;





    }
}
