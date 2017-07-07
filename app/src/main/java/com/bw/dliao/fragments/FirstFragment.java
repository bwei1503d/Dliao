package com.bw.dliao.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bw.dliao.R;
import com.bw.dliao.activitys.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.socks.library.KLog.I;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {


    @BindView(R.id.button)
    Button button;
    Unbinder unbinder;

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        initView(view);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void initView(View view) {


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.button)
    public void onClick() {
        startActivity(new Intent(getActivity(),LoginActivity.class));
    }
}
