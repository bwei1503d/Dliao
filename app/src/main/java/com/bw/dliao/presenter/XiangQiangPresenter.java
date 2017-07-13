package com.bw.dliao.presenter;


import com.bw.dliao.bean.UserInfoBean;
import com.bw.dliao.jiekou.Xiangqing;
import com.bw.dliao.model.XiangQingModel;
import com.bw.dliao.view.XiangQiangActivityView;

public class XiangQiangPresenter implements Xiangqing {



    private XiangQingModel xiangQingModel ;
    XiangQiangActivityView xiangQiangActivityView;

    public XiangQiangPresenter(XiangQiangActivityView xiangQiangActivityView){
        xiangQingModel = new XiangQingModel(this);
        this.xiangQiangActivityView = xiangQiangActivityView;
    }


    public void modelgetShuju(String user_id){


        xiangQingModel.getshuju(user_id);

    }


    //成功失败
    @Override
    public void onSuccess(UserInfoBean userInfoBean) {

        xiangQiangActivityView.onRegisterSuccess(userInfoBean);

    }

    @Override
    public void onFail() {

    }
}
