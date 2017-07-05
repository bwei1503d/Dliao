package com.bw.dliao;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;

import com.bw.dliao.activitys.LoginActivity;
import com.bw.dliao.activitys.TabActivity;
import com.bw.dliao.base.IActivity;
import com.bw.dliao.cipher.DesEncrypt;
import com.bw.dliao.widget.cityview.SelectAddressDialog;
import com.bw.dliao.widget.cityview.myinterface.SelectAddressInterface;

import java.util.HashMap;
import java.util.Map;


//zmuhanxibaweiZ1
public class SpalshActivity extends IActivity {

    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);


        button = (Button) findViewById(R.id.btn);

        textView = (TextView) findViewById(R.id.textview);



        System.loadLibrary("core");




        toIActivity(LoginActivity.class,null,0);






    }


}
