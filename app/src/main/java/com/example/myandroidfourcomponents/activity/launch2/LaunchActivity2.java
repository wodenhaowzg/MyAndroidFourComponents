package com.example.myandroidfourcomponents.activity.launch2;

import android.os.Bundle;
import android.view.View;

import com.example.myandroidfourcomponents.R;
import com.example.myandroidfourcomponents.activity.BaseActivity;
import com.example.myandroidfourcomponents.databinding.ActivityLaunchBinding;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

public class LaunchActivity2 extends BaseActivity {
    private ActivityLaunchBinding viewDataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_launch);
        viewDataBinding.bStartNext.setOnClickListener(this);
        viewDataBinding.bStartNext2.setOnClickListener(this);
        viewDataBinding.bStartNext3.setOnClickListener(this);
    }

    @Override
    public void onClickImpl(View v) {
        if (v.getId() == viewDataBinding.bStartNext.getId()) {
            localStartActivityWithSingleTop(BActivity2.class);
        } else if (v.getId() == viewDataBinding.bStartNext2.getId()) {
            localStartActivityWithSingleTask(CActivity2.class);
        } else if (v.getId() == viewDataBinding.bStartNext3.getId()) {
            localStartActivityWithSingleInstance(DActivity2.class);
        }
    }
}
