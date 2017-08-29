package com.bw.dliao.fragments;


import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.dliao.R;
import com.bw.dliao.speex.SpeexPlayer;
import com.bw.dliao.speex.SpeexRecorder;
import com.bw.dliao.utils.PreferencesUtils;
import com.bw.dliao.utils.SDCardUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * apa
 * A simple {@link Fragment} subclass.
 */
public class FourthFragment extends Fragment {


    @BindView(R.id.textview_id_fourth)
    TextView textviewIdFourth;
    Unbinder unbinder;
    @BindView(R.id.btn_recoder)
    Button btnRecoder;
    @BindView(R.id.btn_unrecoder)
    Button btnUnrecoder;
    private SpeexRecorder recorderInstance;
    private String fileName;

    public FourthFragment() {
        // Required empty public constructor
    }


    Handler handler = new Handler(){


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){

                case 1:
                    System.out.println("Thread.currentThread().getName() = " + Thread.currentThread());



                    break;
            }
        }
    } ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fourth, container, false);
        unbinder = ButterKnife.bind(this, view);

        String nickName = PreferencesUtils.getValueByKey(getActivity(), "nickname", "");

        textviewIdFourth.setText(nickName);


        btnRecoder.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        String filePath = Environment.getExternalStorageDirectory() + File.separator + SDCardUtils.DLIAO;
                        System.out.println("filePath:" + filePath);
                        File file = new File(filePath  + "/");
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
                        break;
                }

                return false;
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_recoder, R.id.btn_unrecoder, R.id.btn_playrecoder})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_recoder:

//                String filePath = Environment.getExternalStorageDirectory() + File.separator + SDCardUtils.DLIAO;
//                System.out.println("filePath:" + filePath);
//                File file = new File(filePath  + "/");
//                System.out.println("file:" + file);
//                if (!file.exists()) {
//                    file.mkdirs();
//                }
//
//                fileName = file + File.separator + System.currentTimeMillis() + ".spx";
//                System.out.println("保存文件名：＝＝ " + fileName);
//                recorderInstance = new SpeexRecorder(fileName, handler);
//                Thread th = new Thread(recorderInstance);
//                th.start();
//                recorderInstance.setRecording(true);


                break;
            case R.id.btn_unrecoder:

                recorderInstance.setRecording(false);

                System.out.println("fileName = " + new File(fileName).length());


                break;
            case R.id.btn_playrecoder:

                SpeexPlayer player = new SpeexPlayer(fileName,handler);
                player.startPlay();

                break;
        }
    }
}
