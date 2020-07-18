package com.example.myandroidfourcomponents.sevice;

import android.content.Intent;

import com.example.myandroidfourcomponents.activity.launch.BActivity;

public class MainService extends BaseService {

    @Override
    public void onCreate() {
        super.onCreate();
        // 在服务中开启activity，需要添加 FLAG_ACTIVITY_NEW_TASK
        Intent i = new Intent(this, BActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}
