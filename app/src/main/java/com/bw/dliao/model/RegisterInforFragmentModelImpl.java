package com.bw.dliao.model;

import com.bw.dliao.base.IApplication;
import com.bw.dliao.bean.RegisterBean;
import com.bw.dliao.core.JNICore;
import com.bw.dliao.core.SortUtils;
import com.bw.dliao.network.BaseObserver;
import com.bw.dliao.network.RetrofitManager;
import com.bw.dliao.utils.PreferencesUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;

/**
 * Created by muhanxi on 17/7/5.
 */

public class RegisterInforFragmentModelImpl implements RegisterInforFragmentModel {




    public void getData(String phone,String nickname,String sex,String age,String area,String introduce,String password,final RegisterInforFragmentDataListener listener){

        Map<String,String> map = new HashMap<String,String>();
        map.put("user.phone",phone);
        map.put("user.nickname",nickname);
        map.put("user.password",password);
        map.put("user.gender",sex);
        map.put("user.area",area);
        map.put("user.age",age);
        map.put("user.introduce",introduce);
        System.out.println("SortUtils.getMapResult(SortUtils.sortString(map)) = " + SortUtils.getMapResult(SortUtils.sortString(map)));

        String sign =  JNICore.getSign(SortUtils.getMapResult(SortUtils.sortString(map))) ;
        map.put("user.sign",sign);

        System.out.println("sign = " + sign);

        RetrofitManager.post("http://qhb.2dyt.com/MyInterface/userAction_add.action", map, new BaseObserver() {
            @Override
            public void onSuccess(String result) {


                Gson gson = new Gson();
                RegisterBean registerBean = gson.fromJson(result, RegisterBean.class);

                if(registerBean.getResult_code() == 200){
                    PreferencesUtils.addConfigInfo(IApplication.getApplication(),"phone",registerBean.getData().getPhone());
                    PreferencesUtils.addConfigInfo(IApplication.getApplication(),"password",registerBean.getData().getPassword());
                    PreferencesUtils.addConfigInfo(IApplication.getApplication(),"yxpassword",registerBean.getData().getYxpassword());
                    PreferencesUtils.addConfigInfo(IApplication.getApplication(),"uid",registerBean.getData().getUserId());
                    PreferencesUtils.addConfigInfo(IApplication.getApplication(),"nickname",registerBean.getData().getNickname());
                }
                listener.onSuccess(registerBean);

            }

            @Override
            public void onFailed(int code) {
                listener.onFailed(code);
            }
        });




    }



}
