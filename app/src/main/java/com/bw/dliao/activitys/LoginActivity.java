package com.bw.dliao.activitys;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bw.dliao.R;
import com.bw.dliao.base.BaseMvpActivity;
import com.bw.dliao.cipher.aes.JNCryptorUtils;
import com.bw.dliao.cipher.rsa.RsaUtils;
import com.bw.dliao.view.LoginView;
import com.bw.dliao.presenter.LoginPresenter;

import java.util.HashMap;
import java.util.Map;

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


        setIPubTitle(getText(R.string.login).toString());
        setILeftBtn();


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


        Map map = new HashMap<String,String>();
        map.put("username","18511085102");
        map.put("password","18511085102");
        map.put("timer","11111222222");
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
        map.put("sign","12fffff");




        //  qhb.222.com/login
//sign = 12fffff
// 验签






    }

    @OnClick(R.id.login_btn_login)
    public void onClick() {





//       String result =  JNICore.getSign("1111");
//        System.out.println("result = " + result);

        toIActivity(RegisterActivity.class,null,0);
//        presenter


//        Map map = new HashMap<String,String>();
//        map.put("username","18511085102");
//        map.put("password","18511085102");
//        map.put("timer","11111222222");
//
//        //排序 去掉中文
//        Map map1 =  SortUtils.sortString(map);
//        System.out.println("map1 = " + map1);
//
//       String result =   SortUtils.getMapResult(map1);
//
//        System.out.println("result = " + result);
//
//        String jniResult =  JNICore.getSign(result);
//
//        map.put("sign",jniResult);
//
//        System.out.println("jniResult = " + jniResult);


    }
}
