package com.bw.dliao.activitys;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bw.dliao.R;
import com.bw.dliao.base.IActivity;
import com.bw.dliao.fragments.RegisterInforFragment;
import com.bw.dliao.fragments.RegisterIntroduceFragment;
import com.bw.dliao.fragments.RegisterSms;

import java.util.ArrayList;
import java.util.List;

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


    private List<Fragment> list = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_sms);
        ButterKnife.bind(this);

        setIPubTitle(getText(R.string.register_title).toString());
        setILeftBtn();


        fragmentManager = getSupportFragmentManager();


        list.add(new RegisterSms());
        list.add(new RegisterInforFragment());
        list.add(new RegisterIntroduceFragment());


         switchIFragment(0,list,R.id.register_container);


    }


    /**
     * 0 获取短信验证码
     * 1 注册填写基本资料
     * 2 添加自我描述
     */
    public void toPos(int pos){
        switchIFragment(pos,list,R.id.register_container);
    }


}
