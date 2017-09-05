package com.bw.dliao;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.platform.comapi.map.M;
import com.bw.dliao.activitys.CChativity;
import com.bw.dliao.activitys.ChatActivity;
import com.bw.dliao.activitys.LoginActivity;
import com.bw.dliao.activitys.SystemChatActivity;
import com.bw.dliao.activitys.TabActivity;
import com.bw.dliao.activitys.TestActivity;
import com.bw.dliao.activitys.ThreadActivity;
import com.bw.dliao.activitys.VoiceActivity;
import com.bw.dliao.base.IActivity;
import com.bw.dliao.service.RobService;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


//zmuhanxibaweiZ1
public class SpalshActivity extends IActivity {

    @BindView(R.id.textview)
    TextView textview;
    @BindView(R.id.btn)
    Button btn;
    private Button button;
    private TextView textView;

    Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);
        ButterKnife.bind(this);


//        startService(new Intent(this, RobService.class));

        button = (Button) findViewById(R.id.btn);
        textView = (TextView) findViewById(R.id.textview);


        toIActivity(TabActivity.class, null, 0);




    }


    @OnClick({R.id.textview, R.id.btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textview:
                toIActivity(ChatActivity.class, null, 0);
                break;
            case R.id.btn:
                toIActivity(LoginActivity.class, null, 0);

                break;
        }
    }
}
