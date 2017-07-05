package com.bw.dliao.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.dliao.R;
import com.bw.dliao.activitys.RegisterActivity;
import com.bw.dliao.base.BaseMvpFragment;
import com.bw.dliao.base.IApplication;
import com.bw.dliao.base.IFragment;
import com.bw.dliao.model.RegisterSmsModelImpl;
import com.bw.dliao.presenter.RegisterSmsPresenter;
import com.bw.dliao.view.RegisterSmsView;
import com.bw.dliao.widget.MyToast;
import com.jakewharton.rxbinding2.view.RxView;
import com.mob.MobSDK;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterSms extends BaseMvpFragment<RegisterSmsView,RegisterSmsPresenter>  implements RegisterSmsView{


    @BindView(R.id.register_sms_edittext_phone)
    EditText registerSmsEdittextPhone;
    @BindView(R.id.register_sms_edittext_password)
    EditText registerSmsEdittextPassword;
    @BindView(R.id.register_sms_btn)
    Button registerSmsBtn;
    @BindView(R.id.register_sms_btn_next)
    Button registerSmsBtnLogin;
    Unbinder unbinder;
    private RegisterActivity registerActivity;


    @Override
    public RegisterSmsPresenter initPresenter() {
        return new RegisterSmsPresenter();
    }

    public RegisterSms() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_sms, container, false);
        unbinder = ButterKnife.bind(this, view);
        registerActivity = (RegisterActivity) getActivity();

        SMSSDK.initSDK(getActivity(),"1f2a06c6cca20","62183728136ab66360efc0378c10c6c4");
        eventHandler = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {

                System.out.println("result = " + result);
                System.out.println("data = " + data);

            }
        };
        // 注册回调监听接口
        SMSSDK.registerEventHandler(eventHandler);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    EventHandler eventHandler ;

    @OnClick({R.id.register_sms_btn, R.id.register_sms_btn_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_sms_btn:




                presenter.getVerificationCode(registerSmsEdittextPhone.getText().toString().trim());




                break;
            case R.id.register_sms_btn_next:

                presenter.nextStep(registerSmsEdittextPhone.getText().toString().trim(),registerSmsEdittextPassword.getText().toString().trim());


                break;
        }
    }


    @Override
    public void phoneError(int type) {

        switch (type){
            case 1:
                MyToast.makeText(IApplication.getApplication(),"手机号码不能为空",Toast.LENGTH_SHORT);

                break;
            case 2:
                MyToast.makeText(IApplication.getApplication(),"手机格式不正确",Toast.LENGTH_SHORT);
                break;
        }

    }


    /**
     * 显示倒计时
     */
    @Override
    public void showTimer() {

        registerSmsBtn.setClickable(false);

        Observable.interval(0,1,TimeUnit.SECONDS)
                .take(30)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(@NonNull Long aLong) throws Exception {
                        return 29 - aLong;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {


//                        d.dispose();

                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {

                        System.out.println("aLong = " + aLong);
                        registerSmsBtn.setText(aLong+" S ");

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        registerSmsBtn.setClickable(true);
                        registerSmsBtn.setText(getText(R.string.register_sms));

                    }
                });




    }


    @Override
    public void toNextPage() {

       registerActivity.toPos(1);


    }
}
