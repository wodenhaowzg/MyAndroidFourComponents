package com.example.myandroidfourcomponents.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.example.myandroidfourcomponents.R;
import com.example.myandroidfourcomponents.databinding.ActivityBBinding;
import com.example.myandroidfourcomponents.databinding.ActivityCBinding;

public class CActivity extends BaseActivity {
    private ActivityCBinding viewDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_c);
        viewDataBinding.cStartNext.setOnClickListener(this);
    }

    @Override
    void onClickImpl(View v) {
        if (v.getId() == viewDataBinding.cStartNext.getId()) {
            localStartActivity(DActivity.class);
        }
    }
}
