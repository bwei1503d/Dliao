package com.bw.dliao.model;

import android.widget.Toast;

import com.bw.dliao.base.IApplication;
import com.bw.dliao.bean.IndexBean;
import com.bw.dliao.daoutils.FirstFragmentDaoUtils;
import com.bw.dliao.network.BaseObserver;
import com.bw.dliao.network.BaseObserver1;
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

        RetrofitManager.post(Constants.ALL_USER, map, new BaseObserver1<IndexBean>() {
            @Override
            public void onSuccess(IndexBean result) {

                try {
//                    Gson gson = new Gson();
//                    IndexBean indexBean =   gson.fromJson(result, IndexBean.class);


                    Toast.makeText(IApplication.getApplication(), ""+result.getData().size(), Toast.LENGTH_SHORT).show();
                    listener.onSuccess(result,currenttimer);



//                    FirstFragmentDaoUtils.insert(indexBean.getData());


                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailed(int code) {
                listener.onFailed(code,currenttimer);
            }
        }
        );


    }
}
