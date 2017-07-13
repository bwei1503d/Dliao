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

    public void getData(long currenttimer){

        firstFragmentModel.getData(currenttimer, new FirstFragmentModel.DataListener() {
            @Override
            public void onSuccess(IndexBean indexBean,long page) {
                view.success(indexBean,page);
            }

            @Override
            public void onFailed(int code,long page) {
                view.failed(code,page);
            }
        });

    }

}
