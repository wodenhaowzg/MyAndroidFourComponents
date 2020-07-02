package com.example.myandroidfourcomponents.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.example.myandroidfourcomponents.R;
import com.example.myandroidfourcomponents.databinding.ActivityCBinding;
import com.example.myandroidfourcomponents.databinding.ActivityDBinding;

public class DActivity extends BaseActivity {

    private ActivityDBinding viewDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_d);
        viewDataBinding.dStartNext.setOnClickListener(this);
    }

    @Override
    void onClickImpl(View v) {
        if (v.getId() == viewDataBinding.dStartNext.getId()) {
            localStartActivity(MainActivity.class);
        }
    }
}
