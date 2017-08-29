package com.bw.dliao.activitys;

import android.os.Handler;

/**
 * Created by muhanxi on 17/7/25.
 */

public class CRun implements Runnable {

    Handler mHandler ;

    public CRun(Handler handler){
        this.mHandler = handler ;
    }


    public void run() {

        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mHandler.sendEmptyMessage(1);
        }
    }
}
