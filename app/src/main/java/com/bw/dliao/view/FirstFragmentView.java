package com.bw.dliao.view;

import com.bw.dliao.bean.IndexBean;

/**
 * Created by muhanxi on 17/7/8.
 */

public interface FirstFragmentView {



    public void success(IndexBean indexBean,long currenttimer);
    public void failed(int code,long currenttimer);


}
