package com.bw.dliao.base;

import com.bw.dliao.dao.DaoMaster;
import com.bw.dliao.dao.DaoSession;
import com.bw.dliao.utils.AMapUtils;
import com.mob.MobApplication;
import com.squareup.leakcanary.LeakCanary;

import okhttp3.OkHttpClient;


/**
 * Created by muhanxi on 17/6/19.
 */

public class IApplication extends MobApplication {


    public static IApplication application ;

    public static DaoSession daoSession ;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;


        initJNI();
        aMap();
        initGreendao();

        LeakCanary.install(this);


    }




    public static IApplication getApplication(){
        if(application == null){
            application = getApplication() ;
        }
        return application;
    }



    private void initJNI(){
        System.loadLibrary("core");
    }

    public void aMap(){
        AMapUtils.getInstance().startUtils(this);
    }



    public void initGreendao(){


        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"dliao.db");
        DaoMaster master = new DaoMaster(helper.getWritableDatabase());
//         加密
//        DaoMaster master = new DaoMaster(helper.getEncryptedWritableDb("1111"));

        daoSession = master.newSession() ;

    }


}
