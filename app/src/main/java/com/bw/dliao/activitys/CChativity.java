package com.bw.dliao.activitys;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.bw.dliao.R;
import com.bw.dliao.adapter.CChativityAdapter;
import com.bw.dliao.utils.PreferencesUtils;
import com.bw.dliao.widget.keyboard.KeyBoardHelper;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMChatManager;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.keyHeight;
import static com.hyphenate.chat.EMMessage.createTxtSendMessage;
import static com.xiaomi.push.service.y.m;

public class CChativity extends Activity implements KeyBoardHelper.OnKeyBoardStatusChangeListener {

    @BindView(R.id.chat_title)
    View chatTitle;
    @BindView(R.id.chat_listview)
    ListView chatListview;
    @BindView(R.id.chat_edittext)
    EditText chatEdittext;
    @BindView(R.id.chat_btn_emoj)
    Button chatBtnEmoj;
    @BindView(R.id.chat_btn_sendtext)
    Button chatBtnSendtext;
    @BindView(R.id.buttom_layout_view)
    View buttomLayoutView;

    List<EMMessage> list ;

    CChativityAdapter adapter ;

    Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch ( msg.what ){
                case 1:

                    break;
            }
        }
    } ;
    private EMMessageListener msgListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cchativity);
        ButterKnife.bind(this);


        list = new ArrayList<EMMessage>();

        adapter = new CChativityAdapter(this,list);
        chatListview.setAdapter(adapter);

        int keyHeight = PreferencesUtils.getValueByKey(this, "kh", 300);


        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) buttomLayoutView.getLayoutParams();
        params.height = keyHeight;
        buttomLayoutView.setLayoutParams(params);



        KeyBoardHelper keyBoardHelper = new KeyBoardHelper(this) ;
        keyBoardHelper.onCreate();
        keyBoardHelper.setOnKeyBoardStatusChangeListener(this);

        //
        chatBtnEmoj.setTag(1);


        setKeyBoardModelResize();


        receive();
    }

    @Override
    public void OnKeyBoardPop(int keyBoardheight) {



        handler.sendEmptyMessageAtTime(1,200);

    }

    @Override
    public void OnKeyBoardClose(int oldKeyBoardheight) {

    }


    public  void setKeyBoardModelPan() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN
                | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    public  void setKeyBoardModelResize() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN
                | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }


    //隐藏键盘

    public void hidenKeyBoard(EditText editText) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public void showKeyBoard(EditText editText){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText,InputMethodManager.SHOW_FORCED);
    }

    @OnClick({R.id.chat_btn_emoj, R.id.chat_btn_sendtext})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chat_btn_emoj:
                //点击表情按钮

                setKeyBoardModelPan();
                int tag = (Integer) chatBtnEmoj.getTag() ;

                if(tag == 1){

                    // 显示表情
                    hidenKeyBoard(chatEdittext);
                    buttomLayoutView.setVisibility(View.VISIBLE);

                    chatBtnEmoj.setTag(2);


                } else {
                    chatBtnEmoj.setTag(1);
                    //  显示键盘
                    showKeyBoard(chatEdittext);

                }


                break;
            case R.id.chat_btn_sendtext:

                setTextMessage();

                break;
        }
    }



    public void setTextMessage(){
        EMMessage emMessage =  EMMessage.createTxtSendMessage("1","1");
        EMClient.getInstance().chatManager().sendMessage(emMessage);


        emMessage.setMessageStatusCallback(new EMCallBack() {
            @Override
            public void onSuccess() {
                System.out.println("emMessage = onSuccess ");
            }

            @Override
            public void onError(int i, String s) {

            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
        list.add(emMessage);
        adapter.notifyDataSetChanged();


    }


    public void receive(){
        //收到消息
//收到透传消息
//收到已读回执
//收到已送达回执
//消息状态变动
        msgListener = new EMMessageListener() {

            @Override
            public void onMessageReceived(List<EMMessage> messages) {
                //收到消息
                System.out.println("onMessageReceived messages = " + messages);
            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> messages) {
                //收到透传消息
                System.out.println("onCmdMessageReceived messages = " + messages);

            }

            @Override
            public void onMessageRead(List<EMMessage> messages) {
                //收到已读回执
                System.out.println("onMessageRead messages = " + messages);

            }

            @Override
            public void onMessageDelivered(List<EMMessage> message) {
                //收到已送达回执
                System.out.println("onMessageDelivered messages = " + message);

            }

            @Override
            public void onMessageChanged(EMMessage message, Object change) {
                //消息状态变动
                System.out.println("onMessageChanged messages = " + message);

            }
        };
        EMClient.getInstance().chatManager().addMessageListener(msgListener);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EMClient.getInstance().chatManager().removeMessageListener(msgListener);

    }
}
