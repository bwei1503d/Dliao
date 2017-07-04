package com.bw.dliao.activitys;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bw.dliao.R;
import com.bw.dliao.base.BaseMvpActivity;
import com.bw.dliao.base.IActivity;
import com.bw.dliao.cipher.aes.JNCryptorUtils;
import com.bw.dliao.cipher.rsa.RsaUtils;
import com.bw.dliao.mview.LoginView;
import com.bw.dliao.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseMvpActivity<LoginView,LoginPresenter> {
    @BindView(R.id.pub_title_leftbtn)
    Button pubTitleLeftbtn;
    @BindView(R.id.pub_title_title)
    TextView pubTitleTitle;
    @BindView(R.id.pub_title_rightbtn)
    Button pubTitleRightbtn;
    @BindView(R.id.login_edittext_phone)
    EditText loginEdittextPhone;
    @BindView(R.id.login_edittext_password)
    EditText loginEdittextPassword;
    @BindView(R.id.login_btn_login)
    Button loginBtnLogin;


    @Override
    public LoginPresenter initPresenter() {
        return new LoginPresenter();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


        setPubTitle(getText(R.string.login).toString());
        setLeftBtn();


        //随机数 rsa 公钥进行加密
//  aes 加密  拿着加密后的
        String random = RsaUtils.getInstance().createRandom();
        System.out.println("random = " + random);
        String rsaKey = RsaUtils.getInstance().createRsaSecret(getApplicationContext(), random);
        String mobile = JNCryptorUtils.getInstance().encryptData("18511085102", getApplicationContext(), random);
        System.out.println("mobile = " + mobile);
//        pass =  JNCryptorUtils.getInstance().encryptData(pass,loginActivity.getApplicationContext());


        System.out.println("random = " + random);
        System.out.println("rsaKey = " + rsaKey);
        System.out.println("mobile = " + mobile);


    }

    @OnClick(R.id.login_btn_login)
    public void onClick() {


        toActivity(RegisterActivity.class,null,0);
//        presenter

    }
}
