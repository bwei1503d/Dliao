package com.bw.dliao.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
public class RegisterInforFragment extends IFragment {


    @BindView(R.id.register_username)
    EditText registerUsername;
    @BindView(R.id.register_sex)
    TextView registerSex;
    @BindView(R.id.register_age)
    TextView registerAge;
    @BindView(R.id.register_diqu_value)
    TextView registerDiquValue;
    @BindView(R.id.register_infor_btn_next)
    Button registerInforBtnNext;
    Unbinder unbinder;
    @BindView(R.id.register_jieshao_value)
    TextView registerJieshaoValue;

    public RegisterInforFragment() {
        // Required empty public constructor
    }

    RegisterActivity activity ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        activity = (RegisterActivity) getActivity();
        View view = inflater.inflate(R.layout.fragment_register_infor, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.register_sex, R.id.register_age, R.id.register_diqu_value,R.id.register_jieshao_value ,R.id.register_infor_btn_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_sex:
                showSexChooseDialog();
                break;
            case R.id.register_age:
                showAgeDialog();
                break;
            case R.id.register_diqu_value:
                break;
            case R.id.register_infor_btn_next:
                break;
            case R.id.register_jieshao_value:
                activity.toPos(2);
                break;
        }
    }

    private String[] sexArry = new String[]{"女", "男"};

    private void showSexChooseDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("请选择性别");
        builder.setItems(sexArry, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                registerSex.setText(sexArry[which]);
            }
        });
        builder.show();
    }


    private void showAgeDialog(){
        final String [] ages  =  getResources().getStringArray(R.array.age);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("请选择年龄");
        builder.setItems(ages, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                registerAge.setText(ages[which]);
            }
        });
        builder.show();

    }





}
