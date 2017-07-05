package com.bw.dliao.view;

import com.bw.dliao.bean.RegisterBean;

/**
 * Created by muhanxi on 17/7/5.
 */

public interface RegisterInforFragmentView {


    public void registerSuccess(RegisterBean registerBean);
    public void registerFailed(int code);

}
