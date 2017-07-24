package com.bw.dliao.activitys;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.bw.dliao.R;
import com.bw.dliao.base.IActivity;
import com.bw.dliao.base.IApplication;
import com.bw.dliao.utils.PreferencesUtils;
import com.bw.dliao.widget.EditTextPreIme;
import com.bw.dliao.widget.keyboard.KeyBoardHelper;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.hyphenate.easeui.EaseConstant.CHATTYPE_GROUP;
//import com.hyphenate.easeui.EaseConstant;
//import com.hyphenate.easeui.ui.EaseChatFragment;

public class ChatActivity extends IActivity implements KeyBoardHelper.OnKeyBoardStatusChangeListener , EditTextPreIme.EditTextListener {

    @BindView(R.id.chat_title)
    View chatTitle;
    @BindView(R.id.chat_listview)
    ListView chatListview;
    @BindView(R.id.chat_edittext)
    EditTextPreIme chatEdittext;

    // chatBtnEmoj 表情btn
    @BindView(R.id.chat_btn_emoj)
    Button chatBtnEmoj;
    //发送btn
    @BindView(R.id.chat_btn_sendtext)
    Button chatBtnSendtext;
    @BindView(R.id.buttom_layout_view)
    LinearLayout buttomLayoutView;
    //键盘的高度
    private int keyHeight;




    String uid ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cchativity);
        ButterKnife.bind(this);

        uid = getIntent().getExtras().getInt("uid")+"" ;

        KeyBoardHelper helper = new KeyBoardHelper(this);
        helper.onCreate();
        helper.setOnKeyBoardStatusChangeListener(this);


        keyHeight = PreferencesUtils.getValueByKey(this, "kh", 0);


        //动态修改底部view 的高度 （表情 加号  View 的高度）
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) buttomLayoutView.getLayoutParams();
        params.height = keyHeight;
        buttomLayoutView.setLayoutParams(params);

        System.out.println("keyHeight = " + keyHeight);

        chatEdittext.setListener(this);
        chatEdittext.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(buttomLayoutView.getVisibility() == View.VISIBLE){
                    setKeyBoardModelPan();
                } else {
                    setKeyBoardModelResize();
                }
                chatEdittext.setListener(ChatActivity.this);
                return false;
            }
        });


        // 1 表示表情  2 表示弹键盘
        chatBtnEmoj.setTag(1);



//       EMConversation conversation =   EMClient.getInstance().chatManager().getConversation("1");
//
//       List<EMMessage> list1 =  conversation.getAllMessages() ;
//
//        if(list1.size() > 0){
//            List<EMMessage> list = conversation.loadMoreMsgFromDB(list1.get(0).getMsgId(),20);
//
//            for(int i=0;i<list.size();i++){
//
//                System.out.println("i = " + list.get(i).getFrom() + "  " + list.get(i).getTo() + "  " + list.get(i).getBody().toString() + " " + list.get(i).getMsgId());
//
//            }
//
//        }
        receiveMessage();


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

    public void hidenKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(chatEdittext.getWindowToken(), 0);
    }

    public void showKeyBoard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(chatEdittext,InputMethodManager.SHOW_FORCED);
    }



    //  键盘 弹起 键盘 关闭 回调
    @Override
    public void OnKeyBoardPop(int keyBoardheight) {

    }

    @Override
    public void OnKeyBoardClose(int oldKeyBoardheight) {

    }

    @OnClick({R.id.chat_btn_emoj, R.id.chat_btn_sendtext})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chat_btn_emoj:

                chatEdittext.setListener(null);

                setKeyBoardModelPan();


                int tag = (int)  chatBtnEmoj.getTag() ;

                if(tag == 1){
                    //表情
                    //表情按钮
                    buttomLayoutView.setVisibility(View.VISIBLE);
                    chatBtnEmoj.setTag(2);
                    hidenKeyBoard();

                } else {
                    //键盘
                    chatBtnEmoj.setTag(1);
                    change();

                }




                break;
            case R.id.chat_btn_sendtext:

                sendTextMessage();

                break;
        }
    }

    //发送消息
    public void sendTextMessage(){



//        EMClient.getInstance().isConnected()  true 表示自己和服务器是链接状态  false 表示自己是断开状态
//

        //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此

        if(!EMClient.getInstance().isConnected()){
            IApplication.getApplication().emLogin();
        }

        int random = new Random().nextInt(100);
        System.out.println("random = " + random);

       final  EMMessage message = EMMessage.createTxtSendMessage(random+"", uid);

        System.out.println("i = " + message.getFrom() + "  " + message.getTo() + "  " + message.getBody().toString() + " " + message.getMsgId() + " " + message.getMsgTime());


        //发送消息
        EMClient.getInstance().chatManager().sendMessage(message);


        // 当前消息的状态
        message.setMessageStatusCallback(new EMCallBack() {
            @Override
            public void onSuccess() {
                System.out.println("i ok= " + message.getFrom() + "  " + message.getTo() + "  " + message.getBody().toString() + " " + message.getMsgId() + " " + message.getMsgTime());

            }

            @Override
            public void onError(int i, String s) {

                System.out.println("i = " + i + " s " + s);

            }

            @Override
            public void onProgress(int i, String s) {

            }
        });

//
//      系统系统数据库的api

//       EMConversation emConversation =  EMClient.getInstance().chatManager().getConversation("1");
//        emConversation.appendMessage(message);



    }


    public void change(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }



    public void receiveMessage(){
        EMMessageListener msgListener = new EMMessageListener() {

            @Override
            public void onMessageReceived(List<EMMessage> messages) {
                //收到消息

                if(messages.size() > 0){
                    System.out.println("messages.get(0).getBody() = " + messages.get(0).getBody());
                }
                
            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> messages) {
                //收到透传消息
                System.out.println("messages onCmdMessageReceived = " + messages);
            }

            @Override
            public void onMessageRead(List<EMMessage> messages) {
                //收到已读回执
            }

            @Override
            public void onMessageDelivered(List<EMMessage> message) {
                //收到已送达回执
            }

            @Override
            public void onMessageChanged(EMMessage message, Object change) {
                //消息状态变动

            }
        };

        EMClient.getInstance().chatManager().addMessageListener(msgListener);

//        记得在不需要的时候移除listener，如在activity的onDestroy()时
//        EMClient.getInstance().chatManager().removeMessageListener(msgListener);
    }


    @Override
    public void onBack() {
        chatEdittext.setListener(null);

        System.out.println("chatTitle = onBack" );



        setKeyBoardModelResize();
        buttomLayoutView.setVisibility(View.GONE);
        chatBtnEmoj.setTag(1);



    }





    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK ){
            System.out.println("chatTitle = onBack KEYCODE_BACK" );
            if(buttomLayoutView.getVisibility() == View.VISIBLE){
                buttomLayoutView.setVisibility(View.GONE);
                chatBtnEmoj.setTag(1);

                return  false;
            }else {
                return super.onKeyDown(keyCode, event);
            }

        } else{
            return super.onKeyDown(keyCode, event);
        }
    }
}
