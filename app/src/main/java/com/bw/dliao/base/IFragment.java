package com.bw.dliao.base;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.dliao.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class IFragment extends Fragment {


    public IFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_i, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy this = " + this);
    }
}
