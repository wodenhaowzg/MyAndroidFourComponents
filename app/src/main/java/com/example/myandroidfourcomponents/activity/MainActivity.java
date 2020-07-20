package com.example.myandroidfourcomponents.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myandroidfourcomponents.LocalHolder;
import com.example.myandroidfourcomponents.R;
import com.example.myandroidfourcomponents.activity.launch.BActivity;
import com.example.myandroidfourcomponents.activity.launch.LaunchActivity;
import com.example.myandroidfourcomponents.databinding.ActivityMainBinding;
import com.example.myandroidfourcomponents.sevice.MainService;
import com.example.myandroidfourcomponents.utils.FloatWindowUtils;
import com.example.myandroidfourcomponents.view.ActivityFloatView;

import androidx.databinding.DataBindingUtil;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    private ActivityMainBinding viewDataBinding;
    boolean mControlMainService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewDataBinding.mainStartNext.setOnClickListener(this);
        viewDataBinding.mainStartNext2.setOnClickListener(this);
        viewDataBinding.mainStartService1.setOnClickListener(this);
    }

    @Override
    public void onClickImpl(View v) {
        if (v.getId() == viewDataBinding.mainStartNext.getId()) {
            localStartActivity(LaunchActivity.class);
        } else if (v.getId() == viewDataBinding.mainStartNext2.getId()) {
            localStartActivityWithNewTask(BActivity.class);
        } else if (v.getId() == viewDataBinding.mainStartService1.getId()) {
            Intent i = new Intent(this, MainService.class);
            if (mControlMainService) {
                stopService(i);
                viewDataBinding.mainStartService1.setText("开启服务MAIN");
            } else {
                startService(i);
                viewDataBinding.mainStartService1.setText("停止服务MAIN");
            }
            mControlMainService = !mControlMainService;
        }
    }
}