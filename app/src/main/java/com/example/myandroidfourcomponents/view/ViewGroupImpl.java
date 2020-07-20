package com.example.myandroidfourcomponents.view;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.myandroidfourcomponents.utils.MyLog;

class ViewGroupImpl extends ViewGroup {
    private static final String TAG = "ViewGroupImpl";

    public ViewGroupImpl(Context context) {
        super(context);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        MyLog.d(TAG, "onAttachedToWindow...");
    }

    // 只有在布局文件中加载View实例会回调
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        MyLog.d(TAG, "onFinishInflate...");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        MyLog.d(TAG, "onMeasure...");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        MyLog.d(TAG, "onLayout...");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        MyLog.d(TAG, "onDraw...");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        MyLog.d(TAG, "onSizeChanged...");
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        MyLog.d(TAG, "onConfigurationChanged...");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        MyLog.d(TAG, "onDetachedFromWindow...");
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        MyLog.d(TAG, "onWindowVisibilityChanged...");
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        MyLog.d(TAG, "onVisibilityChanged...");
    }

}
