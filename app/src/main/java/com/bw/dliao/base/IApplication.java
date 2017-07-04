package com.bw.dliao.base;

import android.app.Application;

import com.mob.MobApplication;
import com.mob.MobSDK;
import com.mob.commons.SMSSDK;


/**
 * Created by muhanxi on 17/6/19.
 */

public class IApplication extends MobApplication {


    public static IApplication application ;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;





    }
}
