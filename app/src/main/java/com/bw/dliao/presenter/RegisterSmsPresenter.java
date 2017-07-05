package com.bw.dliao.presenter;

import android.text.TextUtils;

import com.bw.dliao.base.BasePresenter;
import com.bw.dliao.model.RegisterSmsModelImpl;
import com.bw.dliao.view.RegisterSmsView;
import com.bw.dliao.utils.PhoneCheckUtils;

/**
 * Created by muhanxi on 17/7/5.
 */

public class RegisterSmsPresenter extends BasePresenter<RegisterSmsView> {


    private RegisterSmsModelImpl registerSmsModel ;

    public RegisterSmsPresenter(){
        registerSmsModel = new RegisterSmsModelImpl();
    }


    public void getVerificationCode(String phone){


        if(TextUtils.isEmpty(phone)){

            view.phoneError(1);
            return;
        }



        if(!PhoneCheckUtils.isChinaPhoneLegal(phone)){
            view.phoneError(2);
            return;
        }

        registerSmsModel.getVerificationCode(phone);


        view.showTimer();

    }



    public void nextStep(String phone,String sms){

        if(TextUtils.isEmpty(phone)){

            view.phoneError(1);
            return;
        }



        if(!PhoneCheckUtils.isChinaPhoneLegal(phone)){
            view.phoneError(2);
            return;
        }




        //判断验证码是否合法  验证码是4为数字 \\d{4} sms 非空

        view.toNextPage();

    }


}
