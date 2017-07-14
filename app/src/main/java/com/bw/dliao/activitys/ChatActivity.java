package com.bw.dliao.activitys;

import android.app.Activity;
import android.os.Bundle;

import com.bw.dliao.R;
import com.bw.dliao.base.IActivity;
//import com.hyphenate.easeui.EaseConstant;
//import com.hyphenate.easeui.ui.EaseChatFragment;

public class ChatActivity extends IActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


//        EaseChatFragment chatFragment = new EaseChatFragment();
//        //传入参数
//        Bundle args = new Bundle();
//        args.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
//        args.putString(EaseConstant.EXTRA_USER_ID, "10");
//        chatFragment.setArguments(args);
//        getSupportFragmentManager().beginTransaction().add(R.id.chat_contaner, chatFragment).commit();
//
    }
}
