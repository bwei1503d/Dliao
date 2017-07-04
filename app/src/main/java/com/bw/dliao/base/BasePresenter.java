package com.bw.dliao.base;

/**
 * Created by muhanxi on 17/6/19.
 */

public  abstract  class BasePresenter<T> {


    public T view ;


    public void attach(T view){

        this.view = view ;

    }

    public void detach(){

        this.view = null ;

    }



}
