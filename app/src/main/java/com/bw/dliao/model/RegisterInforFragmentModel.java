package com.bw.dliao.model;

import com.bw.dliao.bean.RegisterBean;

/**
 * Created by muhanxi on 17/7/5.
 */

public interface RegisterInforFragmentModel {

    public void getData(String phone,String nickname,String sex,String age,String area,String introduce,String password,RegisterInforFragmentDataListener listener);




    public interface RegisterInforFragmentDataListener {


        public void onSuccess(RegisterBean registerBean);

        public void onFailed(int code);

    }

}
