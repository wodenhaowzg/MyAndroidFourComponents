package com.example.myandroidfourcomponents.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.myandroidfourcomponents.R;
import com.example.myandroidfourcomponents.databinding.ActivityMainBinding;
import com.example.myandroidfourcomponents.utils.ActivityUtils;
import com.example.myandroidfourcomponents.utils.MyLog;

import java.util.List;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    private ActivityMainBinding viewDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewDataBinding.mainStartNext.setOnClickListener(this);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                // 获取activity任务栈
//                ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//                List<ActivityManager.AppTask> appTasks = manager.getAppTasks();
//                for (ActivityManager.AppTask appTask : appTasks) {
//                    MyLog.d(TAG, "iterator AppTask : " + appTask.toString());
//                }
//
//
//                List<ActivityManager.RunningTaskInfo> runningTasks = manager.getRunningTasks(5);
//                for (ActivityManager.RunningTaskInfo runningTask : runningTasks) {
//                    MyLog.d(TAG, "iterator runningTask : " + runningTask.toString());
//                }
//            }
//        }).start();
    }

    @Override
    void onClickImpl(View v) {
        if (v.getId() == viewDataBinding.mainStartNext.getId()) {
            localStartActivityWithNewTask(BActivity.class);
        }
    }
}