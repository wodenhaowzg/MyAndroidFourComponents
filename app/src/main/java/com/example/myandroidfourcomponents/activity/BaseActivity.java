package com.example.myandroidfourcomponents.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.myandroidfourcomponents.LocalHolder;
import com.example.myandroidfourcomponents.utils.ActivityUtils;
import com.example.myandroidfourcomponents.utils.FloatWindowUtils;
import com.example.myandroidfourcomponents.view.ActivityFloatView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    protected Context mContext;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        this.mContext = newBase;
        Log.d(this.getClass().getSimpleName(), "attachBaseContext...");
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(this.getClass().getSimpleName(), "onAttachedToWindow...");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(this.getClass().getSimpleName(), "onCreate... " + (savedInstanceState == null ? "null" : savedInstanceState.toString()));
        String activityInfos = ActivityUtils.getActivityInfos(this);
        Log.d(this.getClass().getSimpleName(), " activityInfos : " + activityInfos);

        ActivityFloatView activityFloatView = LocalHolder.getInstance().getActivityFloatView();
        if (activityFloatView == null) {
            activityFloatView = new ActivityFloatView(this);
            LocalHolder.getInstance().setActivityFloatView(activityFloatView);
            FloatWindowUtils.applyOrShowFloatWindow(this, activityFloatView);
        }
        activityFloatView.addItem(this.getClass().getSimpleName(), getTaskId());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(this.getClass().getSimpleName(), "onRestart...");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(this.getClass().getSimpleName(), "onNewIntent...");
        ActivityFloatView activityFloatView = LocalHolder.getInstance().getActivityFloatView();
        activityFloatView.moveItemToTop(this.getClass().getSimpleName());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        Log.d(this.getClass().getSimpleName(), "onCreateView...");
        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(this.getClass().getSimpleName(), "onStart...");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(this.getClass().getSimpleName(), "onPause...");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(this.getClass().getSimpleName(), "onResume...");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(this.getClass().getSimpleName(), "onStop...");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(this.getClass().getSimpleName(), "onDestroy...");
        ActivityFloatView activityFloatView = LocalHolder.getInstance().getActivityFloatView();
        activityFloatView.removeItem(this.getClass().getSimpleName());
    }

    @Override
    public void onClick(View v) {
        onClickImpl(v);
    }

    public abstract void onClickImpl(View v);

    protected void localStartActivity(Class<? extends Activity> baseActivityClass) {
        Intent i = new Intent(this, baseActivityClass);
        startActivity(i);
    }

    protected void localStartActivityWithNewTask(Class<? extends Activity> baseActivityClass) {
        Intent i = new Intent(this, baseActivityClass);
        // 在 service 或 application 中启动 activity，需要携带此标记才可以
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    protected void localStartActivityWithSingleTop(Class<? extends Activity> baseActivityClass) {
        Intent i = new Intent(this, baseActivityClass);
        // 此flag单独使用的行为与清单文件中的singleTop行为是一致的
        i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(i);
    }

    protected void localStartActivityWithSingleTask(Class<? extends Activity> baseActivityClass) {
        Intent i = new Intent(this, baseActivityClass);
        // 此两个个flag组合使用，与清单文件中的singleTask行为是一致的。
        // 清除任务栈中 activity 之上的其他 activity 实例
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(i);
    }

    protected void localStartActivityWithSingleInstance(Class<? extends Activity> baseActivityClass) {
        Intent i = new Intent(this, baseActivityClass);
        i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        startActivity(i);
    }
}
