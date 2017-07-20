package com.bw.dliao.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.dliao.R;
import com.bw.dliao.base.IApplication;
import com.hyphenate.chat.EMCallStateChangeListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.EMNoActiveCallException;
import com.hyphenate.exceptions.EMServiceNotReadyException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 视频通话界面
 */
public class TelActivity extends Activity {


    /**
     * 1 拨打
     * 2 接听 电话
     */
    public int type;
    public String uid ;


    @BindView(R.id.tel_activity_hint)
    TextView telActivityHint;
    @BindView(R.id.tel_activity_accept)
    Button telActivityAccept;
    @BindView(R.id.tel_activity_handup)
    Button telActivityHandup;
    @BindView(R.id.tel_activity_disaccept)
    Button telActivityDisaccept;


    public static void startTelActivity(int type,String uid, Context context) {

        Intent intent = new Intent(context, TelActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("uid", uid);
        context.startActivity(intent);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tel);
        ButterKnife.bind(this);


        type = getIntent().getExtras().getInt("type");

        uid = getIntent().getExtras().getString("uid");

//        telActivityAccept 1 接听电话
        telActivityAccept.setTag(1);

        if (type == 1) {
            //拨打电话
            telActivityAccept.setVisibility(View.GONE);
            telActivityDisaccept.setVisibility(View.GONE);
            telActivityHandup.setVisibility(View.VISIBLE);
            try {


                EMClient.getInstance().callManager().makeVoiceCall(uid);

                IApplication.callTo();

            } catch (EMServiceNotReadyException e) {
                e.printStackTrace();
            }
        } else {
            //接听电话
            telActivityHandup.setVisibility(View.GONE);
            telActivityAccept.setVisibility(View.VISIBLE);
            telActivityDisaccept.setVisibility(View.VISIBLE);


        }

        addListener();

    }

    public void addListener(){
        EMClient.getInstance().callManager().addCallStateChangeListener(new EMCallStateChangeListener() {
            @Override
            public void onCallStateChanged(CallState callState, CallError error) {

                System.out.println("callState = " + callState);
                System.out.println("error = " + error);

                switch (callState) {
                    case CONNECTING: // 正在连接对方


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                telActivityHint.setText("呼叫中");
                            }
                        });

                        break;
                    case CONNECTED: // 双方已经建立连接

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                telActivityHint.setText("连接中");

                            }
                        });
                        break;

                    case ACCEPTED: // 电话接通成功
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                telActivityHint.setText("通话中");

                            }
                        });
                        break;
                    case DISCONNECTED: // 电话断了


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                                Toast.makeText(TelActivity.this, "通话已经结束", Toast.LENGTH_SHORT).show();
                            }
                        });

                        break;
                    case NETWORK_UNSTABLE: //网络不稳定
                        if (error == CallError.ERROR_NO_DATA) {
                            //无通话数据
                        } else {

                        }
                        break;
                    case NETWORK_NORMAL: //网络恢复正常

                        break;
                    default:
                        break;
                }

            }
        });

    }


    @OnClick({R.id.tel_activity_accept, R.id.tel_activity_handup,R.id.tel_activity_disaccept})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tel_activity_accept:
                try {
//                    接听电话

                    int tag = (Integer) telActivityAccept.getTag() ;
                    if(tag == 1){
                        telActivityAccept.setText("挂断");
                        telActivityAccept.setTag(2);
                        telActivityDisaccept.setVisibility(View.GONE);
                        EMClient.getInstance().callManager().answerCall();
                    }else {

                        EMClient.getInstance().callManager().endCall();

                        finish();

                    }

                } catch (EMNoActiveCallException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            case R.id.tel_activity_handup:
                //挂断电话
                try {
                    EMClient.getInstance().callManager().endCall();
                } catch (EMNoActiveCallException e) {
                    e.printStackTrace();
                }finally {
                    finish();
                }


                break;
            case R.id.tel_activity_disaccept:
                //拒绝接听
                try {
                    EMClient.getInstance().callManager().rejectCall();
                } catch (EMNoActiveCallException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }finally {
                    finish();

                }

                break;
        }
    }
}
