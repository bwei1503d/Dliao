package com.bw.dliao.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bw.dliao.R;
import com.bw.dliao.base.BaseMvpActivity;
import com.bw.dliao.base.IApplication;
import com.bw.dliao.cipher.Md5Utils;
import com.bw.dliao.cipher.aes.JNCryptorUtils;
import com.bw.dliao.cipher.rsa.RsaUtils;
import com.bw.dliao.network.BaseObserver;
import com.bw.dliao.network.RetrofitManager;
import com.bw.dliao.presenter.LoginPresenter;
import com.bw.dliao.utils.Constants;
import com.bw.dliao.view.LoginView;
import com.bw.dliao.widget.keyboard.KeyBoardHelper;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class LoginActivity extends BaseMvpActivity<LoginView, LoginPresenter> implements KeyBoardHelper.OnKeyBoardStatusChangeListener {
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
    @BindView(R.id.to_register)
    TextView toRegister;
    @BindView(R.id.content)
    LinearLayout content;


    @Override
    public LoginPresenter initPresenter() {
        return new LoginPresenter();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


        setIPubTitle(getText(R.string.login).toString());
        setILeftBtn();



        KeyBoardHelper keyBoardHelper = new KeyBoardHelper(this) ;
        keyBoardHelper.onCreate();
        keyBoardHelper.setOnKeyBoardStatusChangeListener(this);

        //随机数 rsa 公钥进行加密
//  aes 加密  拿着加密后的
        String random = RsaUtils.getInstance().createRandom();
        System.out.println("random = " + random);
        String rsaKey = RsaUtils.getInstance().createRsaSecret(getApplicationContext(), random);
        String mobile = JNCryptorUtils.getInstance().encryptData("18511085102", getApplicationContext(), random);
        System.out.println("mobile = " + mobile);
//        pass =  JNCryptorUtils.getInstance().encryptData(pass,loginActivity.getApplicationContext());


//        System.out.println("random = " + random);
//        System.out.println("rsaKey = " + rsaKey);
//        System.out.println("mobile = " + mobile);


//        Map map = new HashMap<String,String>();
//        map.put("username","18511085102");
//        map.put("password","18511085102");
//        map.put("timer","11111222222");
        //排序
//        map.put("password","18511085102");
//        map.put("timer","11111222222");
        //map.put("username","18511085102");
        //拼接
//        password=18511085102&timer=11111222222&username=18511085102


//        jni   appkey= fgjkfggkf
//        appkey= fgjkfggkf&password=18511085102&timer=11111222222&username=18511085102
        //        appkey= md5(appkey= fgjkfggkf&= + (password=18511085102&timer=11111222222&username=18511085102)) return  sign
//        32位长度的字符串 sign
//        map 排序 ascd 排序  拼接成字符串  调用jni 中方法 返回一个sign  传递给服务器
//        map.put("sign","12fffff");


        //  qhb.222.com/login
//sign = 12fffff
// 验签




    }


    @OnClick({R.id.login_btn_login, R.id.to_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn_login:



                String phone = loginEdittextPhone.getText().toString().trim();
                String password = loginEdittextPassword.getText().toString().trim();


                //产生16位 随机数
                String randomKey = RsaUtils.getStringRandom(16);


                //rsa 公钥加密出来的结果
                String rsaRandomKey = RsaUtils.getInstance().createRsaSecret(IApplication.getApplication(), randomKey);


//                aes 加密
                String cipherPhone = JNCryptorUtils.getInstance().encryptData(phone, IApplication.getApplication(), randomKey);


                Map map = new HashMap<String, String>();
                map.put("user.phone", cipherPhone);
                map.put("user.password", Md5Utils.getMD5(password));
                map.put("user.secretkey", rsaRandomKey);

                RetrofitManager.post(Constants.LOGIN_ACTION, map, new BaseObserver() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("result = " + result);

                        finish();
                    }

                    @Override
                    public void onFailed(int code) {


                    }
                });
                break;
            case R.id.to_register:
                toIActivity(RegisterActivity.class,null,0);
                break;
        }
    }




    @Override
    public void OnKeyBoardPop(int keyBoardheight) {

        System.out.println("keyBoardheight OnKeyBoardPop = " + keyBoardheight);
    }

    @Override
    public void OnKeyBoardClose(int oldKeyBoardheight) {

        System.out.println("keyBoardheight OnKeyBoardClose = " + oldKeyBoardheight);


    }
}
