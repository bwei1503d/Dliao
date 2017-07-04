package com.bw.dliao.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.dliao.R;
import com.bw.dliao.base.IFragment;

/**
 * A simple {@link } subclass.
 */
public class ThirdFragment extends IFragment {

    public ThirdFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_third, container, false);


        return view;
    }





}
