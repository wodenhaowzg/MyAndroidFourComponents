package com.example.myandroidfourcomponents.activity.launch2;

import android.os.Bundle;
import android.view.View;

import com.example.myandroidfourcomponents.R;
import com.example.myandroidfourcomponents.activity.BaseActivity;
import com.example.myandroidfourcomponents.databinding.ActivityCBinding;

import androidx.databinding.DataBindingUtil;

/**
 * 这种模式启动的 Activity 在任务栈中，同一时刻系统中只会存在一个实例，已存在的实例被再次启动时，
 * 会重新唤起该实例，并清理当前Task任务栈该实例之上的所有Activity，同时回调 onNewIntent 方法。
 */
public class CActivity2 extends BaseActivity {
    private ActivityCBinding viewDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_c);
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
