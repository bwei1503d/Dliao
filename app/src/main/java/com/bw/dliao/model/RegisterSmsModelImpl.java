package com.bw.dliao.model;

import cn.smssdk.SMSSDK;

/**
 * Created by muhanxi on 17/7/5.
 */

public class RegisterSmsModelImpl implements RegisterSmsModel {



    public void getVerificationCode(String phone){

        SMSSDK.getVerificationCode("86", phone);

    }



}
