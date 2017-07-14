package com.bw.dliao.model;

import android.util.Log;

import com.bw.dliao.bean.UserInfoBean;
import com.bw.dliao.jiekou.Xiangqing;
import com.bw.dliao.network.BaseObserver;
import com.bw.dliao.network.RetrofitManager;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;



public class XiangQingModel {

    Xiangqing xiangqing ;

    public XiangQingModel(Xiangqing xiangqing) {
        this.xiangqing = xiangqing;
    }

    public void getshuju(String user_id) {

        Map map = new HashMap<String,String>();
        map.put("user.userId",user_id);


        RetrofitManager.post("http://qhb.2dyt.com/MyInterface/userAction_selectUserById.action", map, new BaseObserver() {
            @Override
            public void onSuccess(String result) {


                Log.e("xunxun",result);

                Gson gson = new Gson();
                UserInfoBean userInfoBean = gson.fromJson(result, UserInfoBean.class);

                if (userInfoBean!=null && userInfoBean.getResult_code() == 200){

                    Log.e("xunxun123",userInfoBean.toString());

                    xiangqing.onSuccess(userInfoBean);
                }else {
                    xiangqing.onFail();

                }


            }

            @Override
            public void onFailed(int code) {
                xiangqing.onFail();
            }
        });


    }

}
