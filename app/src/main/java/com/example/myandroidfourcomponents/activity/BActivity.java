package com.example.myandroidfourcomponents.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.example.myandroidfourcomponents.R;
import com.example.myandroidfourcomponents.databinding.ActivityBBinding;
import com.example.myandroidfourcomponents.databinding.ActivityMainBinding;

public class BActivity extends BaseActivity{

    private ActivityBBinding viewDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_b);
        viewDataBinding.bStartNext.setOnClickListener(this);
        viewDataBinding.bStartNext2.setOnClickListener(this);
    }

    @Override
    void onClickImpl(View v) {
        if (v.getId() == viewDataBinding.bStartNext.getId()) {
            localStartActivity(CActivity.class);
        } else if (v.getId() == viewDataBinding.bStartNext2.getId()) {
            localStartActivityWithNewTask(CActivity.class);
        }
    }
}
