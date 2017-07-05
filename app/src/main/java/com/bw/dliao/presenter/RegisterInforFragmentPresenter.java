package com.bw.dliao.presenter;

import com.bw.dliao.base.BasePresenter;
import com.bw.dliao.bean.RegisterBean;
import com.bw.dliao.model.RegisterInforFragmentModel;
import com.bw.dliao.model.RegisterInforFragmentModelImpl;
import com.bw.dliao.view.RegisterInforFragmentView;

/**
 * Created by muhanxi on 17/7/5.
 */

public class RegisterInforFragmentPresenter extends BasePresenter<RegisterInforFragmentView> {


    private RegisterInforFragmentModel registerInforFragmentModel ;
    public RegisterInforFragmentPresenter(){
        registerInforFragmentModel = new RegisterInforFragmentModelImpl();
    }



    public void vaildInfor(String phone,String nickname,String sex,String age,String area,String introduce,String password){


        //非空判断

        registerInforFragmentModel.getData(phone, nickname, sex, age, area, introduce, password, new RegisterInforFragmentModel.RegisterInforFragmentDataListener() {
            @Override
            public void onSuccess(RegisterBean registerBean) {


                view.registerSuccess(registerBean);

            }

            @Override
            public void onFailed(int code) {

                view.registerFailed(code);
            }
        });






    }


}
