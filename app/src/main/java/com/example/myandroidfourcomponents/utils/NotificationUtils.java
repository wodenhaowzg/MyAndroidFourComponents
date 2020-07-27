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
        if (!TextUtils.isEmpty(title)) {
            builder.setContentTitle(title);
        }

        if (!TextUtils.isEmpty(content)) {
            builder.setContentText(content);
        }

        if (smallIcon != 0) {
            builder.setSmallIcon(smallIcon);
        }

        if (largeIcon != null) {
            builder.setLargeIcon(largeIcon);
        }

        if (when != 0) {
            builder.setWhen(when);
        }

        // NotificationCompat.PRIORITY_XXX 范围 -2 ~ 2
        builder.setPriority(priorityLevel);
        builder.setOngoing(ongoing);
        builder.setAutoCancel(autoCannel);
        return builder.build();
    }
}
