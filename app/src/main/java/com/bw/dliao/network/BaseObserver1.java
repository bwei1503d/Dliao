package com.bw.dliao.network;

import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baidu.platform.comapi.map.M;
import com.bw.dliao.base.IApplication;
import com.bw.dliao.bean.base.BaseBean;
import com.socks.library.KLog;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.SocketException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by muhanxi on 17/6/19.
 */

public abstract class BaseObserver1<T> implements Observer<String> {


    /**
     * 项目框架搭建
     * 1 通用模块的封装  1 共用UI  2 父类定义方法 子类实现
     * 2 通用网络请求模块  定义错误编码
     * 3 常用工具类的封装
     * 4 basemvpactivity  basemvpfragment
     * 5 主框架页面结构
     * 6 fragment 切换 通用
     * 7 rxjava retrofit mvp
     * @param d
     */
    private String tag;
    public BaseObserver1(String tag){
        this.tag = tag;
    }

    @Override
    public void onSubscribe( Disposable d) {



    }



    @Override
    public void onNext(@NonNull String s) {


        try {
            Type genType = getClass().getGenericSuperclass();
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            Class entityClass = (Class) params[0];
            T t = (T)  JSON.parseObject(s,entityClass);
            BaseBean baseBean = (BaseBean) t ;
            Toast.makeText(IApplication.getApplication(), ""+baseBean.getResult_code(), Toast.LENGTH_SHORT).show();
            KLog.e(s);
            onSuccess(t,tag);
        } catch (Exception e) {
            onFailed(JSON_FORMAT_ERROR);
            e.printStackTrace();
        }


    }

    @Override
    public void onError(@NonNull Throwable e) {
        try {
            if(e != null){
                if(e instanceof HttpException){
                    onFailed(HTTP_ERROR);
                } else if(e instanceof SocketException){
                    onFailed(NET_WORK_ERROR);
                }else {
                    onFailed(UNKNOW_ERROR);
                }
            }else {
                onFailed(UNKNOW_ERROR);
            }
            e.printStackTrace() ;
            System.out.println();
            KLog.i(e);
        } catch (Exception e1) {
            onFailed(UNKNOW_ERROR);
            e1.printStackTrace();
        }


    }

    @Override
    public void onComplete() {

    }


    public abstract void onSuccess(T result,String tag);

    /**
     * code
     *  1000 UNKNOW_ERROR 未知错误
     *  1001 json 转化异常  parse error
     *  1002 当前网络不可用     java.net.SocketException: Network is unreachable  超时
     *  1003 服务器不可用 401 402 403 500 502 503 504
     * @param code
     */
    public abstract void onFailed(int code);






    public static final int UNKNOW_ERROR = 1000;
    public static final int JSON_FORMAT_ERROR = 1001;
    public static final int NET_WORK_ERROR = 1002;
    public static final int HTTP_ERROR = 1003;

//    HttpException 都属于 http exception
//    private static final int UNAUTHORIZED = 401;
//    private static final int FORBIDDEN = 403;
//    private static final int NOT_FOUND = 404;
//    private static final int REQUEST_TIMEOUT = 408;
//    private static final int INTERNAL_SERVER_ERROR = 500;
//    private static final int BAD_GATEWAY = 502;
//    private static final int SERVICE_UNAVAILABLE = 503;
//    private static final int GATEWAY_TIMEOUT = 504;

}
