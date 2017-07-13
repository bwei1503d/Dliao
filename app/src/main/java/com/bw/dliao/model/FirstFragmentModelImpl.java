package com.bw.dliao.model;

import com.bw.dliao.base.IApplication;
import com.bw.dliao.bean.IndexBean;
import com.bw.dliao.daoutils.FirstFragmentDaoUtils;
import com.bw.dliao.network.BaseObserver;
import com.bw.dliao.network.RetrofitManager;
import com.bw.dliao.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.HashMap;
import java.util.Map;

import static com.socks.library.KLog.I;

/**
 * Created by muhanxi on 17/7/8.
 */

public class FirstFragmentModelImpl implements FirstFragmentModel {


    @Override
    public void getData(final long currenttimer, final DataListener listener) {

        Map<String,String> map = new HashMap<String, String>();
//        map.put("pageIndex",page+"");
//        map.put("pageSize","20");
        map.put("user.currenttimer",currenttimer+"");

        RetrofitManager.post(Constants.ALL_USER, map, new BaseObserver() {
            @Override
            public void onSuccess(String result) {

                try {
                    Gson gson = new Gson();
                    IndexBean indexBean =   gson.fromJson(result, IndexBean.class);

                    listener.onSuccess(indexBean,currenttimer);



                    FirstFragmentDaoUtils.insert(indexBean.getData());


                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed(int code) {
                listener.onFailed(code,currenttimer);
            }
        });


    }
}
