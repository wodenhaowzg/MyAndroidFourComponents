package com.example.myandroidfourcomponents.dialog;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.View;

import com.example.myandroidfourcomponents.LocalHolder;
import com.example.myandroidfourcomponents.R;
import com.example.myandroidfourcomponents.databinding.DialogNotificationLayoutBinding;
import com.example.myandroidfourcomponents.sevice.ForegroundService;
import com.example.myandroidfourcomponents.utils.NotificationUtils;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.databinding.ViewDataBinding;

public class NotificationDialog extends BaseDialog {

    private static final String CHANNEL_ID = "com.example.myandroidfourcomponents.channel.id";
    private DialogNotificationLayoutBinding mBinding;

    public NotificationDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    int getContentViewId() {
        return R.layout.dialog_notification_layout;
    }

    @Override
    void initViewBinding(ViewDataBinding viewDataBinding) {
        mBinding = (DialogNotificationLayoutBinding) viewDataBinding;
    }

    @Override
    void initData() {
        mBinding.notificationBuildService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Context context = getContext();
        int id = v.getId();
        if (id == mBinding.notificationBuildService.getId()) {
            String title = "我是通知";
            String titleStr = mBinding.notificationTitleEt.getText().toString();
            if (!TextUtils.isEmpty(titleStr)) {
                title = titleStr;
            }

            String content = "我是通知的内容";
            String contentStr = mBinding.notificationContentEt.getText().toString();
            if (!TextUtils.isEmpty(contentStr)) {
                content = contentStr;
            }

            int smallIcon = 0;
            if (mBinding.notificationSmallIconCb.isChecked()) {
                smallIcon = R.mipmap.ic_launcher;
            }

            Bitmap largeIcon = null;
            if (mBinding.notificationLargeIconCb.isChecked()) {
                largeIcon = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
            }

            long when = 0;
            String whenStr = mBinding.notificationWhenEt.getText().toString();
            if (!TextUtils.isEmpty(whenStr)) {
                when = Long.parseLong(whenStr);
            }

            int priority = NotificationCompat.PRIORITY_DEFAULT;
            String priorityStr = mBinding.notificationPriorityEt.getText().toString();
            if (!TextUtils.isEmpty(priorityStr)) {
                priority = Integer.parseInt(priorityStr);
            }

            boolean ongoing = false;
            if (mBinding.notificationOngoingCb.isChecked()) {
                ongoing = true;
            }

            boolean autoCannel = false;
            if (mBinding.notificationAutocannelCb.isChecked()) {
                autoCannel = true;
            }

            Notification notification = NotificationUtils.buildNotification(context, CHANNEL_ID,
                    title,
                    content,
                    smallIcon,
                    largeIcon,
                    when,
                    priority,
                    ongoing,
                    autoCannel
            );

            if (notification == null) {
                throw new RuntimeException("notification is null!");
            }
            LocalHolder.getInstance().setNotification(notification);
            context.stopService(new Intent(context, ForegroundService.class));
            context.startService(new Intent(context, ForegroundService.class));
        }
    }
}
