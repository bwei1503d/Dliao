package com.bw.dliao.model;

import com.bw.dliao.bean.IndexBean;

/**
 * Created by muhanxi on 17/7/8.
 */

public interface FirstFragmentModel {


    public void getData(int page,DataListener dataListener);




    public interface DataListener{
        public void onSuccess(IndexBean indexBean,int page);
        public void onFailed(int code,int page);
    }

}
