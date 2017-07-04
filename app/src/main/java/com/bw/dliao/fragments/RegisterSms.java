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
public class RegisterSms extends IFragment {


    @BindView(R.id.register_sms_edittext_phone)
    EditText registerSmsEdittextPhone;
    @BindView(R.id.register_sms_edittext_password)
    EditText registerSmsEdittextPassword;
    @BindView(R.id.register_sms_btn)
    Button registerSmsBtn;
    @BindView(R.id.register_sms_btn_next)
    Button registerSmsBtnLogin;
    Unbinder unbinder;
    private RegisterActivity registerActivity;

    public RegisterSms() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_sms, container, false);
        unbinder = ButterKnife.bind(this, view);
        registerActivity = (RegisterActivity) getActivity();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.register_sms_btn, R.id.register_sms_btn_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_sms_btn:
                break;
            case R.id.register_sms_btn_next:


                registerActivity.toInforFragment();

                break;
        }
    }
}
