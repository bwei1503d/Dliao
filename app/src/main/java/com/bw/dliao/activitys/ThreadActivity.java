package com.bw.dliao.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.dliao.R;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.bw.dliao.R.raw.msg;


public class ThreadActivity extends Activity implements View.OnClickListener{



    Button btn_main_to_work;
    Button btn_work_to_main;
    Handler handler;
    Handler handler_main_to_work;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
//        initViews();
//        initMainHandler();//子线程向主线程发送消息
//        new MainToWorkThread().start();//主线程向子线程发送消息
//        initClicks();

        initThread();
    }


    private void initMainHandler() {
        handler = new MainHandler();
    }
    class MainHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            String msg_rtn = (String)msg.obj;
            Toast.makeText(ThreadActivity.this, "MainHandler.handleMessage():"+msg_rtn+",当前线程："+Thread.currentThread().getName(), 100).show();
        }
    }
    //子线程向主线程发送消息
    class WorktoMainThread extends Thread{
        @Override
        public void run() {
            Message msg = handler.obtainMessage();
            msg.obj = "我是模拟的网络数据";
            handler.sendMessage(msg);
            System.out.println("开始发送网络数据：");
            //暂时还不清楚为什么这个线程里使用Toast的时候一定要加上Looper.prepare()，否则就报错
//            Looper.prepare();
//            Toast.makeText(SecActivity.this, Thread.currentThread().getName()+"开始发送网络数据:", 100).show();
        }
    }

    class MainToWorkThread extends Thread{
        @Override
        public void run() {
            Looper.prepare();
            handler_main_to_work = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    String msg_get = (String)msg.obj;
//                    handler_main_to_work.sendMessage(msg);
                    Toast.makeText(ThreadActivity.this, Thread.currentThread().getName()+",MaintoWorkHandler.handleMessage()开始接收消息:"+msg_get, 100).show();
                }
            };
            Message msg = handler_main_to_work.obtainMessage();
            String msg_get = (String) msg.obj;
            System.out.println("MainToWorkThread获取到的消息："+msg_get);
            Looper.loop();
        }
    }

    private void initClicks() {
        btn_main_to_work.setOnClickListener(this);
        btn_work_to_main.setOnClickListener(this);
    }

    private void initViews() {
        btn_main_to_work = (Button) findViewById(R.id.id_btn_w_to_main);
        btn_work_to_main = (Button) findViewById(R.id.id_btn_main_to_w);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_btn_w_to_main:
                new WorktoMainThread().start();
                break;
            case R.id.id_btn_main_to_w:
                Message msg = handler_main_to_work.obtainMessage();
                msg.obj = "我是点击的时候发送给子线程的消息";
                handler_main_to_work.sendMessage(msg);
                break;
            default:
                break;
        }
    }

    TextView mTextView ;
    HandlerTest1  mHandlerTest1 ;
    private void init() {
        mTextView = (TextView) findViewById(R.id.textView);

        //1 子线程发送消息给本身
        new Thread() {
            public void run() {
                Looper.prepare();
                mHandlerTest1=new HandlerTest1(Looper.myLooper());
                Message message = new Message();
                message.obj = "子线程发送的消息Hi~Hi";
                mHandlerTest1.sendMessage(message);
                Looper.loop();
            };
        }.start();

    }

    HandlerTest2  mHandlerTest2 ;
    int counter = 0 ;
    private class HandlerTest1 extends Handler {

        private HandlerTest1(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            System.out.println("子线程收到:" + msg.obj + "  " + Thread.currentThread());

            //2  收到消息后可再发消息到主线程
            mHandlerTest2=new HandlerTest2(getMainLooper());
            Message message = new Message();
            message.obj = "O(∩_∩)O";
            mHandlerTest2.sendMessage(message);
        }
    }

    private class HandlerTest2 extends Handler {

        private HandlerTest2(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mTextView.setText("在主线程中,收到子线程发来消息:" + msg.obj);

            //3  收到消息后再发消息到子线程
            if (counter==0) {
                Message message = new Message();
                message.obj = "主线程发送的消息Xi~Xi";
                mHandlerTest1.sendMessage(message);
                counter++;
            }

        }
    }





    ///

    public void initThread(){
        System.out.println("msg initThread = " + msg + Thread.currentThread());

        new Thread(new Runnable() {
            @Override
            public void run() {

                Looper.prepare();
                System.out.println("msg prepare = " + msg + Thread.currentThread());

                SubThreadHandler handler = new SubThreadHandler(Looper.myLooper());
                handler.sendEmptyMessage(1);
                Looper.loop();
            }
        }).start();

    }


    class SubThreadHandler extends  Handler{
        private SubThreadHandler(Looper looper){
            super(looper);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            System.out.println("msg sun = " + msg + Thread.currentThread());

            MainThreadHandler handler = new MainThreadHandler(getMainLooper());
            handler.sendEmptyMessage(1);

        }
    }

    class MainThreadHandler extends Handler{

        private MainThreadHandler(Looper looper){
            super(looper);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            System.out.println("msg main= " + msg + Thread.currentThread());

        }
    }





}
