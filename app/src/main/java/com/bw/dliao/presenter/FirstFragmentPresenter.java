package com.bw.dliao.presenter;

import com.bw.dliao.base.BasePresenter;
import com.bw.dliao.bean.IndexBean;
import com.bw.dliao.model.FirstFragmentModel;
import com.bw.dliao.model.FirstFragmentModelImpl;
import com.bw.dliao.view.FirstFragmentView;

/**
 * Created by muhanxi on 17/7/8.
 */

public class FirstFragmentPresenter extends BasePresenter<FirstFragmentView> {

    private FirstFragmentModel firstFragmentModel ;

    public FirstFragmentPresenter(){
        firstFragmentModel = new FirstFragmentModelImpl();
    }

    public void getData(int page){

        firstFragmentModel.getData(page, new FirstFragmentModel.DataListener() {
            @Override
            public void onSuccess(IndexBean indexBean,int page) {
                view.success(indexBean,page);
            }

            @Override
            public void onFailed(int code,int page) {
                view.failed(code,page);
            }
        });

    }

}
