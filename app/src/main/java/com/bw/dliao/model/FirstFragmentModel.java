package com.bw.dliao.model;

import com.bw.dliao.bean.IndexBean;

/**
 * Created by muhanxi on 17/7/8.
 */

public interface FirstFragmentModel {


    public void getData(long currenttimer,DataListener dataListener);




    public interface DataListener{
        public void onSuccess(IndexBean indexBean,long currenttimer);
        public void onFailed(int code,long currenttimer);
    }

}
