package com.bw.dliao.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.dliao.R;
import com.bw.dliao.base.IApplication;
import com.bw.dliao.bean.UserInfoBean;
import com.bw.dliao.network.BaseObserver;
import com.bw.dliao.network.RetrofitManager;
import com.bw.dliao.photoview.PicShowDialog;
import com.bw.dliao.presenter.XiangQiangPresenter;
import com.bw.dliao.view.XiangQiangActivityView;
import com.bw.dliao.widget.MyToast;
import com.donkingliang.labels.LabelsView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class XiangQingActivity extends Activity implements XiangQiangActivityView, View.OnClickListener {


    @BindView(R.id.xiangqing_touxiang)
    ImageView xiangqingTouxiang;
    @BindView(R.id.xiangqing_zuoshangjiao)
    ImageView xiangqingZuoshangjiao;
    @BindView(R.id.xiangqing_youshangjiao)
    ImageView xiangqingYoushangjiao;
    @BindView(R.id.xiangqing_name)
    TextView xiangqingName;
    @BindView(R.id.xiangqing_age)
    TextView xiangqingAge;
    @BindView(R.id.fridentactivity_button)
    Button fridentactivitybutton;

    @BindView(R.id.id_gallery)
    LinearLayout linearLayout;
    @BindView(R.id.labels)
    LabelsView labelsView;
    private LayoutInflater mInflater;

    private XiangQiangPresenter xiangQiangPresenter;

    private String location;

    private int itemWidth;
    private ImageView img;
    private List<UserInfoBean.DataBean.PhotolistBean> photolist;
    ArrayList<String> label = new ArrayList<>();
    private String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang_qing);
        ButterKnife.bind(this);


        user_id = getIntent().getStringExtra("user_id");
        location = getIntent().getStringExtra("location");


        xiangQiangPresenter = new XiangQiangPresenter(this);
        xiangQiangPresenter.modelgetShuju(user_id);

        mInflater = LayoutInflater.from(this);

        initTag();

    }

    private void initTag() {

        label.add("稳重");
        label.add("养花");
        label.add("冷静");
        label.add("稳重");
        label.add("热情");

        labelsView.setLabels(label); //直接设置一个字符串数组就可以了。
    }

    @OnClick(R.id.fridentactivity_button)
    public void onJumpClicked(View view) {
        getFrident();


    }

    @OnClick({R.id.xiangqing_zuoshangjiao, R.id.xiangqing_youshangjiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.xiangqing_zuoshangjiao:

                finish();

                break;
            case R.id.xiangqing_youshangjiao:
                break;


        }
    }

    @Override
    public void onRegisterSuccess(UserInfoBean userInfoBean) {

        final UserInfoBean.DataBean data = userInfoBean.getData();

        System.out.println("3333" + data.getNickname());

        xiangqingName.setText(data.getNickname());
        xiangqingAge.setText(data.getArea() + " , " + location);


        Glide.with(XiangQingActivity.this).load(data.getImagePath()).error(R.mipmap.ic_launcher).into(xiangqingTouxiang);

        photolist = data.getPhotolist();

        if (photolist.size() != 0) {

            for (int i = 0; i < photolist.size(); i++) {

                img = new ImageView(this);

                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(400, 400);
                params.setMargins(100, 100, 100, 100);
                img.setLayoutParams(params);

                Glide.with(XiangQingActivity.this).load(photolist.get(i).getImagePath()).error(R.mipmap.ic_launcher).into(img);

                linearLayout.addView(img);


                img.setOnClickListener(this);

            }


        }
    }


    //子条目点击事件
    @Override
    public void onClick(View v) {

        Log.e("11111", photolist.size() + "");

        //点击位置及对象传入dialog
        PicShowDialog dialog = new PicShowDialog(XiangQingActivity.this, photolist, 1);
        dialog.show();
    }


    @Override
    public void onRegisterFailed() {

    }

    public void getFrident() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("relationship.friendId", user_id + "");
        RetrofitManager.post("http://qhb.2dyt.com/MyInterface/userAction_addFriends.action", map, new BaseObserver() {
            @Override
            public void onSuccess(String result) {
                System.out.println("=====" + result);
                Gson gson = new Gson();
                if (result.contains("未登录")) {
                    // 给一个用户友好的提示
                    MyToast.makeText(IApplication.getApplication(), "" + "请先登陆", Toast.LENGTH_SHORT);
                    return;
                }
                if (result.contains("200")) {
                    // 给一个用户友好的提示
                    MyToast.makeText(IApplication.getApplication(), "" + "添加成功", Toast.LENGTH_SHORT);
                    fridentactivitybutton.setText("发消息");

                }
            }

            @Override
            public void onFailed(int code) {

            }
        });
    }
}
