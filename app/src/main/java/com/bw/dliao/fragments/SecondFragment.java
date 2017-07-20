package com.bw.dliao.fragments;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.dliao.R;
import com.bw.dliao.activitys.ChatActivity;
import com.bw.dliao.activitys.SystemChatActivity;
import com.bw.dliao.activitys.VoiceActivity;
import com.bw.dliao.adapter.Fragment_second_Adapter;
import com.bw.dliao.base.IApplication;
import com.bw.dliao.bean.DataBean1;
import com.bw.dliao.bean.FActionFriendBean;
import com.bw.dliao.network.BaseObserver;
import com.bw.dliao.network.RetrofitManager;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.bw.dliao.R.id.fragment_two_login_tv;
import static com.bw.dliao.base.IApplication.application;
import static com.bw.dliao.base.IApplication.getApplication;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {


    List<DataBean1> list = new ArrayList<DataBean1>();
    @BindView(R.id.fragment_two_login_iv)
    ImageView fragmentTwoLoginIv;
    @BindView(fragment_two_login_tv)
    TextView fragmentTwoLoginTv;
    @BindView(R.id.fragment_second_login)
    TextView fragmentSecondLogin;
    @BindView(R.id.fragment_second_finsh)
    ImageView fragmentSecondFinsh;
    @BindView(R.id.fragment_frist_recyviews)
    RecyclerView fragmentFristRecyviews;
    @BindView(R.id.spingviews)
    SpringView spingviews;
    private Fragment_second_Adapter adapter;
    private Unbinder unbinder;

    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {


        fragmentTwoLoginIv.setVisibility(View.GONE);
        fragmentTwoLoginTv.setVisibility(View.GONE);
        long ctimer = System.currentTimeMillis();
        getInfor(ctimer);
        application = (IApplication) getApplication();
        spingviews.setHeader(new DefaultHeader(getApplication()));
        spingviews.setFooter(new DefaultFooter(getApplication()));
        spingviews.setType(SpringView.Type.FOLLOW);
        adapter = new Fragment_second_Adapter(list, IApplication.getApplication());
        fragmentFristRecyviews.setAdapter(adapter);
        adapter.setOnItemClickListener(new Fragment_second_Adapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, View view,int uid) {


                Intent intent =  new Intent(getActivity(), VoiceActivity.class) ;
                intent.putExtra("uid",uid);
                startActivity(intent);



            }

            @Override
            public void onItemLongClickListener(int position, View view) {

            }
        });

        fragmentFristRecyviews.setLayoutManager(new LinearLayoutManager(getApplication()));
        spingviews.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                list.clear();
                long ctimer = System.currentTimeMillis();
                getInfor(ctimer);
                spingviews.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                long relationtime = list.get(list.size() - 1).getRelationtime();
                getInfor(relationtime);
                spingviews.onFinishFreshAndLoad();
            }
        });
    }

    public void getInfor(long re) {

        //获取判断是否有网络的管理器
        ConnectivityManager connectivityManager = (ConnectivityManager) IApplication.getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        //获取可用网络
        NetworkInfo infor = connectivityManager.getActiveNetworkInfo();
        //获取wifi状态
        NetworkInfo.State wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        //获取网络状态
        NetworkInfo.State interstat = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        if (infor == null) {
            //  List<DataBean1> lista = application.daoSession.getDataBean1Dao().queryBuilder().list();
            // list.addAll(lista);
            adapter.notifyDataSetChanged();
        } else {
            long ctimer = System.currentTimeMillis();
            Map<String, String> map = new HashMap<String, String>();
            map.put("user.currenttimer", re + "");

            RetrofitManager.post("http://qhb.2dyt.com/MyInterface/userAction_selectAllUserAndFriend.action", map, new BaseObserver() {
                @Override
                public void onSuccess(String result) {
                    Gson gson = new Gson();
                    FActionFriendBean fActionFriendBean = gson.fromJson(result, FActionFriendBean.class);
                    List<DataBean1> data = fActionFriendBean.getData();
                    if (data == null) {
                        return;
                    } else {
                        list.addAll(data);
                        adapter.notifyDataSetChanged();
                    }



                }

                @Override
                public void onFailed(int code) {

                }
            });
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }




}
