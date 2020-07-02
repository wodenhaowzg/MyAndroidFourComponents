package com.example.myandroidfourcomponents.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myandroidfourcomponents.utils.ActivityUtils;

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
    }

    @Override
    public void onClick(View v) {
        onClickImpl(v);
    }

    abstract void onClickImpl(View v);

    protected void localStartActivity(Class<? extends Activity> baseActivityClass) {
        Intent i = new Intent(this, baseActivityClass);
        startActivity(i);
    }

    protected void localStartActivityWithNewTask(Class<? extends Activity> baseActivityClass) {
        Intent i = new Intent(this, baseActivityClass);
            i.addFlags(Intent.FLAG_ACTIVITY_);
        startActivity(i);
    }
}
