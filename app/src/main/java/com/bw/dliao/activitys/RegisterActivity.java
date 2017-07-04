package com.bw.dliao.activitys;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bw.dliao.R;
import com.bw.dliao.base.IActivity;
import com.bw.dliao.fragments.RegisterInforFragment;
import com.bw.dliao.fragments.RegisterSms;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends IActivity {

    @BindView(R.id.pub_title_leftbtn)
    Button pubTitleLeftbtn;
    @BindView(R.id.pub_title_title)
    TextView pubTitleTitle;
    @BindView(R.id.pub_title_rightbtn)
    Button pubTitleRightbtn;
    @BindView(R.id.register_container)
    FrameLayout registerContainer;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_sms);
        ButterKnife.bind(this);

        setPubTitle(getText(R.string.register_title).toString());
        setLeftBtn();


        fragmentManager = getSupportFragmentManager();

        RegisterSms registerSms = new RegisterSms();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (!registerSms.isAdded()) {
            fragmentTransaction.add(R.id.register_container, registerSms);
        }

        fragmentTransaction.commit();


    }


    /**
     * 填写基本信息
     */
    public void toInforFragment(){

        RegisterInforFragment registerInforFragment = new RegisterInforFragment();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (!registerInforFragment.isAdded()) {
            fragmentTransaction.add(R.id.register_container, registerInforFragment);
        }

        fragmentTransaction.commit();
    }

}
