package com.example.myandroidfourcomponents.activity.launch;

import android.os.Bundle;
import android.view.View;

import com.example.myandroidfourcomponents.R;
import com.example.myandroidfourcomponents.activity.BaseActivity;
import com.example.myandroidfourcomponents.databinding.ActivityDBinding;

import androidx.databinding.DataBindingUtil;

/**
 * 这种模式启动的Activity独自占用一个Task任务栈，同一时刻系统中只会存在一个实例，已存在的实例被再次启动时，只会唤起原实例，并回调 onNewIntent 方法。
 */
public class DActivity extends BaseActivity {

    private ActivityDBinding viewDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_d);
        viewDataBinding.bStartNext.setOnClickListener(this);
        viewDataBinding.bStartNext2.setOnClickListener(this);
        viewDataBinding.bStartNext3.setOnClickListener(this);
    }

    @Override
    public void onClickImpl(View v) {
        if (v.getId() == viewDataBinding.bStartNext.getId()) {
            localStartActivity(BActivity.class);
        } else if (v.getId() == viewDataBinding.bStartNext2.getId()) {
            localStartActivity(CActivity.class);
        } else if (v.getId() == viewDataBinding.bStartNext3.getId()) {
            localStartActivity(DActivity.class);
        }
    }
}
