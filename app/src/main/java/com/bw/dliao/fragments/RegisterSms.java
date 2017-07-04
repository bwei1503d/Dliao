package com.bw.dliao.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.dliao.R;
import com.bw.dliao.activitys.RegisterActivity;
import com.bw.dliao.base.IFragment;
import com.mob.MobSDK;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

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

        SMSSDK.initSDK(getActivity(),"1f2a06c6cca20","62183728136ab66360efc0378c10c6c4");
//        MobSDK.init(getActivity(),"1f2a06c6cca20","62183728136ab66360efc0378c10c6c4");

        eventHandler = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {

            }
        };
        // 注册回调监听接口
        SMSSDK.registerEventHandler(eventHandler);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    EventHandler eventHandler ;

    @OnClick({R.id.register_sms_btn, R.id.register_sms_btn_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_sms_btn:




            SMSSDK.getVerificationCode("86", "18511085102");

                break;
            case R.id.register_sms_btn_next:


                registerActivity.toInforFragment();

                break;
        }
    }
}
