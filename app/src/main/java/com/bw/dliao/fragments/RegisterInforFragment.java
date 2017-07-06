package com.bw.dliao.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.dliao.R;
import com.bw.dliao.activitys.LoginActivity;
import com.bw.dliao.activitys.RegisterActivity;
import com.bw.dliao.activitys.UploadPhotoActivity;
import com.bw.dliao.base.AppManager;
import com.bw.dliao.base.BaseMvpFragment;
import com.bw.dliao.base.IApplication;
import com.bw.dliao.bean.RegisterBean;
import com.bw.dliao.cipher.Md5Utils;
import com.bw.dliao.presenter.RegisterInforFragmentPresenter;
import com.bw.dliao.view.RegisterInforFragmentView;
import com.bw.dliao.widget.MyToast;
import com.bw.dliao.widget.cityview.SelectAddressDialog;
import com.bw.dliao.widget.cityview.myinterface.SelectAddressInterface;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxAdapterView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewAfterTextChangeEvent;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterInforFragment extends BaseMvpFragment<RegisterInforFragmentView, RegisterInforFragmentPresenter> implements RegisterInforFragmentView {


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
    @BindView(R.id.register_password)
    EditText registerPassword;

    public RegisterInforFragment() {
        // Required empty public constructor
    }


    @Override
    public RegisterInforFragmentPresenter initPresenter() {
        return new RegisterInforFragmentPresenter();
    }

    RegisterActivity activity;

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

    @OnClick({R.id.register_sex, R.id.register_age, R.id.register_diqu_value, R.id.register_jieshao_value, R.id.register_infor_btn_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_sex:
                showSexChooseDialog();
                break;
            case R.id.register_age:
                showAgeDialog();
                break;
            case R.id.register_diqu_value:
                showLocal();
                break;
            case R.id.register_infor_btn_next:


//                toData();

                activity.toIActivity(UploadPhotoActivity.class,null,0);
                AppManager.getAppManager().finishActivity(getActivity());
                AppManager.getAppManager().finishActivity(LoginActivity.class);

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

    AlertDialog.Builder builder;

    private void showAgeDialog() {
        if (builder == null) {
            final String[] ages = getResources().getStringArray(R.array.age);
            builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("请选择年龄");
            builder.setItems(ages, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    registerAge.setText(ages[which]);
                }
            });
        }

        builder.show();

    }


    private String phone;

    public void setPhone(String phone) {

        this.phone = phone;


    }

    public void setDes(String des) {
        if (!TextUtils.isEmpty(des)) {
            registerJieshaoValue.setText(des);
        }

    }

    private void showLocal() {

        SelectAddressDialog dialog = new SelectAddressDialog(getActivity(), new SelectAddressInterface() {
            @Override
            public void setAreaString(String area) {
                registerDiquValue.setText(area);
            }
        }, 3, null);
        dialog.showDialog();

    }


    /**
     * 判断所有的参数 非空
     * 注册 添加 草稿功能
     */
    private void toData() {

        //可以实现 监听 edittext 内容变化
//        TextWatcher textWatcher = new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        } ;
//
//
//        registerUsername.addTextChangedListener(textWatcher);

//        RxAdapterView.itemClicks()


//        RxTextView.afterTextChangeEvents(new TextView(getActivity())).subscribe(new Consumer<TextViewAfterTextChangeEvent>() {
//            @Override
//            public void accept(@NonNull TextViewAfterTextChangeEvent textViewAfterTextChangeEvent) throws Exception {
////                textViewAfterTextChangeEvent.view().getText().
//            }
//        })


        RxView.clicks(registerInforBtnNext).throttleFirst(5, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {

                        presenter.vaildInfor(phone, registerUsername.getText().toString().trim(), registerSex.getText().toString().trim()
                                , registerAge.getText().toString().trim(), registerDiquValue.getText().toString().trim()
                                , registerJieshaoValue.getText().toString().trim(), Md5Utils.getMD5(registerPassword.getText().toString().trim()));
                    }
                });



    }


    @Override
    public void registerSuccess(RegisterBean registerBean) {
        //跳到上传形象照页面

        if(registerBean.getResult_code() == 200){
            activity.toIActivity(UploadPhotoActivity.class,null,0);
            AppManager.getAppManager().finishActivity(getActivity());
            AppManager.getAppManager().finishActivity(LoginActivity.class);


        }else {
            MyToast.makeText(IApplication.getApplication(),registerBean.getResult_message(), Toast.LENGTH_SHORT);
        }

    }

    @Override
    public void registerFailed(int code) {
        // 给一个用户友好的提示
        MyToast.makeText(IApplication.getApplication(),code+"", Toast.LENGTH_SHORT);


    }
}
