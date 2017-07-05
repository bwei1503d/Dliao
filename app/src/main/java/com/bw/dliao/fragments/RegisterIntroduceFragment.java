package com.bw.dliao.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.bw.dliao.R;
import com.bw.dliao.activitys.RegisterActivity;
import com.bw.dliao.base.IFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterIntroduceFragment extends IFragment {


    @BindView(R.id.register_des)
    EditText registerDes;
    @BindView(R.id.register_des_save)
    Button registerDesSave;
    Unbinder unbinder;
    RegisterActivity activity ;
    private InputMethodManager imm;

    public RegisterIntroduceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        activity = (RegisterActivity) getActivity();

        View view = inflater.inflate(R.layout.fragment_register_introduce, container, false);
        unbinder = ButterKnife.bind(this, view);
        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.register_des_save)
    public void onClick() {


        // 关闭软键盘 如果 显示 则隐藏  同理
//        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

        //强制隐藏键盘
        imm.hideSoftInputFromWindow(registerDes.getWindowToken(), 0);
//        强制显示键盘
//        imm.showSoftInput(view,InputMethodManager.SHOW_FORCED);

        //隐藏键盘
//        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

//        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//        boolean isOpen=imm.isActive();//isOpen若返回true，则表示输入法打开

        String des =  registerDes.getText().toString().trim() ;
        activity.setDes(des);
        //跳转第二个fragment
        activity.toPos(1);


    }
}
