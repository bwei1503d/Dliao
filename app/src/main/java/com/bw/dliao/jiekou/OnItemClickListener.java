package com.bw.dliao.jiekou;

import android.view.View;

public interface OnItemClickListener<T> {

    void onItemClick(int position, T t, View v, String s);
}