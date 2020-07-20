package com.example.myandroidfourcomponents;

import com.example.myandroidfourcomponents.view.ActivityFloatView;

public class LocalHolder {

    private static LocalHolder mLocalHolder;

    private ActivityFloatView mActivityFloatView;

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
