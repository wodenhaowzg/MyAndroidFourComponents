package com.example.myandroidfourcomponents.sevice;

import android.app.Notification;
import android.widget.Toast;

import com.example.myandroidfourcomponents.LocalHolder;
import com.example.myandroidfourcomponents.utils.MyLog;

/**
 * https://developer.android.com/training/notify-user/build-notification#system-category
 * setCategory(...) Android 使用一些预定义的系统范围类别来确定在用户启用勿扰模式后是否发出指定通知来干扰客户。
 */
public class ForegroundService extends BaseService {

    private static final String TAG = "ForegroundService";

    @Override
    public void onCreate() {
        super.onCreate();
        Notification notification = LocalHolder.getInstance().getNotification();
        startForeground(1, notification);
        MyLog.d(TAG, "notification 配置 : " + notification.toString());
        Toast.makeText(this, "成功开启前台服务", 0).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
    }
}
