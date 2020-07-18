package com.example.myandroidfourcomponents.activity.launch;

import android.os.Bundle;
import android.view.View;

import com.example.myandroidfourcomponents.R;
import com.example.myandroidfourcomponents.activity.BaseActivity;
import com.example.myandroidfourcomponents.databinding.ActivityBBinding;

import androidx.databinding.DataBindingUtil;

/**
 *  如果Activity实例位于当前任务栈顶，就重用栈顶实例，而不新建，并回调该实例 onNewIntent 方法，否则走新建流程。
 */
public class BActivity extends BaseActivity {

    private ActivityBBinding viewDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_b);
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
