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

import static android.R.attr.tag;
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

        RetrofitManager.post(Constants.ALL_USER, map, new BaseObserver1<IndexBean>("age") {
            @Override
            public void onSuccess(IndexBean result, String tag) {

            }

            @Override
            public void onFailed(int code) {

            }
        });



//        public void getData1(Map map,String tag) {
//
////            Map<String,String> map = new HashMap<String, String>();
////        map.put("pageIndex",page+"");
////        map.put("pageSize","20");
////            map.put("user.currenttimer",currenttimer+"");
//
//            RetrofitManager.post(Constants.ALL_USER, map, new BaseObserver1<IndexBean>(tag) {
//                @Override
//                public void onSuccess(IndexBean result, String tag) {
//
//                }
//
//                @Override
//                public void onFailed(int code) {
//
//                }
//            });

    }
}
