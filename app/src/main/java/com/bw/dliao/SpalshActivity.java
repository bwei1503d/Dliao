package com.bw.dliao;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.dliao.activitys.CChativity;
import com.bw.dliao.activitys.ChatActivity;
import com.bw.dliao.activitys.LoginActivity;
import com.bw.dliao.activitys.TabActivity;
import com.bw.dliao.activitys.VoiceActivity;
import com.bw.dliao.base.IActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);
        ButterKnife.bind(this);


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
