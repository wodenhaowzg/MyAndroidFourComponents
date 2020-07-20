package com.example.myandroidfourcomponents.view;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myandroidfourcomponents.R;
import com.example.myandroidfourcomponents.utils.MyLog;

public class ActivityFloatView extends FrameLayout implements View.OnClickListener {

    private static final String TAG = "ActivityFloatView";
    private ViewGroup mListView;

    public ActivityFloatView(Context context) {
        super(context);
        View.inflate(context, R.layout.view_float_view, this);
        mListView = findViewById(R.id.list_ly);
    }

    public ActivityFloatView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        MyLog.d(TAG, "onAttachedToWindow...");
    }


    @Override
    protected void onFinishInflate() {
        MyLog.d(TAG, "onFinishInflate...");
        super.onFinishInflate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        MyLog.d(TAG, "onMeasure...");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
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

    @Override
    public void onClick(View v) {

    }

    public void addItem(String simpleName, int stackId) {
        TextView textView = new TextView(getContext());
        textView.setText(simpleName + "(" + stackId + ")");
        textView.setTextColor(Color.BLUE);
        textView.setTextSize(16);
        textView.setTag(simpleName);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        params.topMargin = 10;
        mListView.addView(textView, 0);
    }

    public void removeItem(String simpleName) {
        int index = -1;
        int childCount = mListView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = mListView.getChildAt(i);
            String tag = (String) childAt.getTag();
            if (tag.equals(simpleName)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            mListView.removeViewAt(index);
        }
    }

    public void moveItemToTop(String simpleName) {
        View targetView = null;
        int childCount = mListView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = mListView.getChildAt(i);
            String tag = (String) childAt.getTag();
            if (tag.equals(simpleName)) {
                targetView = childAt;
                break;
            }
        }

        if (targetView != null) {
            mListView.removeView(targetView);
            mListView.addView(targetView, 0);
        }
    }
}
