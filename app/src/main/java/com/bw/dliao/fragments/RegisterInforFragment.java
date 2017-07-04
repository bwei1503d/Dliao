package com.bw.dliao.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.dliao.R;
import com.bw.dliao.base.IFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterInforFragment extends IFragment {


    public RegisterInforFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_infor, container, false);
    }

}
