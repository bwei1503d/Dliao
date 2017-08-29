package com.bw.dliao.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;


import com.bw.dliao.R;
import com.bw.dliao.speex.SpeexRecorder;
import com.bw.dliao.utils.SDCardUtils;
import com.bw.dliao.widget.MyToast;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends Activity {

    @BindView(R.id.bt)
    Button bt;
    boolean flag = true;
//    private PopupWindow pw;
//    private TextView textView;
//    private ImageView voice_db;
//    private ImageView cancel;
//    private LinearLayout line;
    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {

                case 20:
                    Log.d("ChatActivity", "msg.obj:" + msg.obj);
                    // if (msg.obj instanceof  Double) {
                    Log.d("TestActivity", "Thread.currentThread():" + Thread.currentThread());
                    final double gb = (double) msg.obj;
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            setLevel(gb);
//                        }
//                    });

                    // }
                    break;
            }
        }
    };
    public void setLevel(double gb) {
//        if (gb < 40) {
//            voice_db.setImageResource(R.drawable.amp1);
//            return;
//        } else if (gb < 50) {
//            voice_db.setImageResource(R.drawable.amp2);
//            return;
//        } else if (gb < 60) {
//            voice_db.setImageResource(R.drawable.amp3);
//            return;
//        } else if (gb < 70) {
//            voice_db.setImageResource(R.drawable.amp4);
//            return;
//        } else if (gb < 80) {
//            voice_db.setImageResource(R.drawable.amp5);
//            return;
//        } else if (gb < 90) {
//            voice_db.setImageResource(R.drawable.amp6);
//            return;
//        } else {
//            voice_db.setImageResource(R.drawable.amp7);
//        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
       // initPopup();
        voice();
    }
    private void initPopup() {
//        View view = View.inflate(TestActivity.this, R.layout.pop_voice_layout, null);
//        view.setBackgroundResource(R.drawable.sp_popup);
//        view.setAlpha(0.8f);
//        textView = (TextView) view.findViewById(R.id.voice_text);
//        voice_db = (ImageView) view.findViewById(R.id.voice_db);
//        cancel = (ImageView) view.findViewById(R.id.voice_cancel);
//        line = (LinearLayout) view.findViewById(R.id.voice_line);
//
//        //创建PopupWindow
//
//        pw = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //   pw.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.toast_color)));
    }

    private void voice() {
        bt.setOnTouchListener(new View.OnTouchListener() {


            private SpeexRecorder recorderInstance;
            private String fileName;
            private long startVoiceT;
            private String filePath;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
             //   int x = (int) event.getX();// 获得x轴坐标
                //int y = (int) event.getY();// 获得y轴坐标
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startVoiceT = System.currentTimeMillis();
                        if (!Environment.getExternalStorageDirectory().exists()) {
//                            MyToast.getInstance().makeText("No SDCard");
                            return false;
                        } else {

                            //点击PopupWindow的外部消失PopupWindow
                            // pw.setOutsideTouchable(true);
                            //设置动画
                            // pw.setAnimationStyle(R.style.Animation);
                            //点击返回键让PopupWindow消失
                            //  pw.setFocusable(true);
//                            textView.setText("上滑取消发送");
//                            cancel.setVisibility(View.GONE);
//                            line.setVisibility(View.VISIBLE);
//                            pw.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                            filePath = Environment.getExternalStorageDirectory() + File.separator + SDCardUtils.DLIAO;
                            System.out.println("filePath:" + filePath);
                            File file = new File(filePath + "/");
                            System.out.println("file:" + file);

                            if (!file.exists()) {
                                file.mkdirs();
                            }

                            fileName = file + File.separator + System.currentTimeMillis() + ".spx";
                            System.out.println("保存文件名：＝＝ " + fileName);
                            recorderInstance = new SpeexRecorder(fileName, handler);
                            Thread th = new Thread(recorderInstance);
                            th.start();
                            recorderInstance.setRecording(true);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                       // pw.dismiss();
                        if (flag) {
                            Log.d("ChatActivity", "startVoiceT:" + startVoiceT);
                            long endVoiceT = System.currentTimeMillis();
                            Log.d("ChatActivity", "endVoiceT:" + endVoiceT);
                            int time = (int) ((endVoiceT - startVoiceT));
                            Log.d("ChatActivity", "time:" + time);
                            if (time < 1000) {
//                                MyToast.getInstance().makeText("录制时间不能小于1秒哦");
                                return false;
                            } else {
                                recorderInstance.setRecording(false);
                                System.out.println("fileName = " + new File(fileName).length());
                             //   EMMessage message = EMMessage.createVoiceSendMessage(fileName, time, chatId + "");
                            //    EMClient.getInstance().chatManager().sendMessage(message);
                            //    Log.d("ChatActivity", "message:" + message);
                               // list.add(message);
                               // chatRecycler.scrollToPosition(adapter.getItemCount() - 1);
                              //  adapter.notifyDataSetChanged();
                            }
                        }


                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        if (wantToCancle(x, y)) {
//                            flag = false;
//                            textView.setText("松开取消发送");
//                            cancel.setVisibility(View.VISIBLE);
//                            line.setVisibility(View.GONE);
//                            // recorderInstance.setRecording(false);
//                        } else {
//                            flag = true;
//                            textView.setText("上滑取消发送");
//                            cancel.setVisibility(View.GONE);
//                            line.setVisibility(View.VISIBLE);
//                        }
//                        break;
                }
                return false;
            }


        });
    }
    private boolean wantToCancle(int x, int y) {
        if (x < 0 || x > bt.getWidth()) { // 超过按钮的宽度
            return true;
        }
        // 超过按钮的高度
        if (y < -50 || y > bt.getHeight() + 50) {
            return true;
        }

        return false;
    }

}
