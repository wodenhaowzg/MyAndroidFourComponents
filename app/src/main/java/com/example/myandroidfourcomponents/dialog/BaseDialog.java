package com.example.myandroidfourcomponents.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.example.myandroidfourcomponents.R;
import com.example.myandroidfourcomponents.utils.DensityUtils;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class BaseDialog extends Dialog implements View.OnClickListener {

    BaseDialog(@NonNull Context context) {
        super(context, R.style.NoBackGroundDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int contentViewId = getContentViewId();
        View rootView = View.inflate(getContext(), contentViewId, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(DensityUtils.dip2px(getContext(), 300), ViewGroup.LayoutParams.WRAP_CONTENT);
        setContentView(rootView, params);
        ViewDataBinding viewDataBinding = DataBindingUtil.bind(rootView);
        initViewBinding(viewDataBinding);
        initData();
    }

    abstract int getContentViewId();

    abstract void initViewBinding(ViewDataBinding viewDataBinding);

    abstract void initData();
}
