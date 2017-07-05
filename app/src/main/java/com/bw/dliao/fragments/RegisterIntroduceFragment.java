package com.bw.dliao.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.bw.dliao.R;
import com.bw.dliao.activitys.RegisterActivity;
import com.bw.dliao.base.IFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterIntroduceFragment extends IFragment {


    @BindView(R.id.register_des)
    EditText registerDes;
    @BindView(R.id.register_des_save)
    Button registerDesSave;
    Unbinder unbinder;
    RegisterActivity activity ;

    public RegisterIntroduceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        activity = (RegisterActivity) getActivity();

        View view = inflater.inflate(R.layout.fragment_register_introduce, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.register_des_save)
    public void onClick() {

        activity.toPos(1);


    }
}
