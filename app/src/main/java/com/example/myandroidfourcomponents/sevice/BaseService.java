package com.example.myandroidfourcomponents.sevice;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

abstract class BaseService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        log("onCreate ...");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        log("onBind ...");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        log("onStartCommand ...");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onRebind(Intent intent) {
        log("onRebind ...");
        super.onRebind(intent);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        log("onTaskRemoved ...");
        super.onTaskRemoved(rootIntent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        log("onUnbind ...");
        return super.onUnbind(intent);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        log("onConfigurationChanged ...");
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onDestroy() {
        log("onDestroy ...");
        super.onDestroy();
    }

    private void log(String msg) {
        Log.d(this.getClass().getSimpleName(), msg);
    }
}
