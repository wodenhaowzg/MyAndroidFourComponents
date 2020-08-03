package com.example.myandroidfourcomponents.utils;

import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;

import androidx.core.app.NotificationCompat;

public class NotificationUtils {


    public static Notification buildNotification(Context context, String channelId, String title, String content, int smallIcon, Bitmap largeIcon, long when,
                                                 int priorityLevel, boolean ongoing, boolean autoCannel) {
        if (context == null || TextUtils.isEmpty(channelId)) {
            return null;
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId);
        // 设置通知标题
        if (!TextUtils.isEmpty(title)) {
            builder.setContentTitle(title);
        }
        // 设置通知内容
        if (!TextUtils.isEmpty(content)) {
            builder.setContentText(content);
        }
        // 设置通知小图标
        if (smallIcon != 0) {
            builder.setSmallIcon(smallIcon);
        }
        // 设置通知大图标
        if (largeIcon != null) {
            builder.setLargeIcon(largeIcon);
        }
        // 设置通知上显示的时间
        if (when != 0) {
            builder.setWhen(when);
        }

        // NotificationCompat.PRIORITY_XXX 范围 -2 ~ 2
        builder.setPriority(priorityLevel);
        // 设置是否是正在活动的通知，被标记后没有"X"按钮，并且一键清除不会清除该通知
        builder.setOngoing(ongoing);
        // 设置通知在点击之后是否自动消失
        builder.setAutoCancel(autoCannel);
        return builder.build();
    }
}
