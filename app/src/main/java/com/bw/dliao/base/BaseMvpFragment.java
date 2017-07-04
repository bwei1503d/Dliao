package com.bw.dliao.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.dliao.R;


/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseMvpFragment<V,T extends BasePresenter<V>> extends Fragment {


    public T presenter ;

    public abstract T initPresenter();


    public BaseMvpFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_base_mvp, container, false);
    }


    @Override
    public void onResume() {
        super.onResume();
        presenter.attach((V) this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }
}
