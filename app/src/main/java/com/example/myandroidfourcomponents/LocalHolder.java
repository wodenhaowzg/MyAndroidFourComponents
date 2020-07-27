package com.example.myandroidfourcomponents;

import android.app.Notification;

import com.example.myandroidfourcomponents.view.ActivityFloatView;

public class LocalHolder {

    private static LocalHolder mLocalHolder;

    private ActivityFloatView mActivityFloatView;

    public Notification getNotification() {
        return mNotification;
    }

    public void setNotification(Notification mNotification) {
        this.mNotification = mNotification;
    }

    private Notification mNotification;

    private LocalHolder() {

    }

    public static LocalHolder getInstance() {
        if (mLocalHolder == null) {
            synchronized (LocalHolder.class) {
                if (mLocalHolder == null) {
                    mLocalHolder = new LocalHolder();
                }
            }
        }
        return mLocalHolder;
    }

    public ActivityFloatView getActivityFloatView() {
        return mActivityFloatView;
    }

    public void setActivityFloatView(ActivityFloatView mActivityFloatView) {
        this.mActivityFloatView = mActivityFloatView;
    }
}
