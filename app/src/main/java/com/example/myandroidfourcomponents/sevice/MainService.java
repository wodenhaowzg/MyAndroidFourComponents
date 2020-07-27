package com.example.myandroidfourcomponents.sevice;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;

import com.example.myandroidfourcomponents.R;
import com.example.myandroidfourcomponents.activity.MainActivity;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MainService extends BaseService {

    @Override
    public void onCreate() {
        super.onCreate();
        // 在服务中开启activity，需要添加 FLAG_ACTIVITY_NEW_TASK
//        Intent i = new Intent(this, BActivity.class);
//        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(i);


        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default")
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher))  // 必选项
                .setSmallIcon(R.mipmap.ic_launcher)  // 必选项
                .setContentTitle("测试的通知")
                .setContentText("测试的通知222")
                .setContentIntent(pendingIntent)
                .setWhen(System.currentTimeMillis())
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        Notification build = builder.build();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(100, build);

//        Notification.Builder builder = new Notification.Builder(this); //获取一个Notification构造器
//        Intent i = new Intent(this, MainActivity.class); //点击后跳转的界面，可以设置跳转数据
//        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        builder.setContentIntent(PendingIntent.getActivity(this, 0, i, 0)) // 设置PendingIntent
////                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher)) // 设置下拉列表中的图标(大图标)
//                //.setContentTitle("SMI InstantView") // 设置下拉列表里的标题
////                .setSmallIcon(R.mipmap.ic_launcher) // 设置状态栏内的小图标
//                .setContentText("is running......") // 设置上下文内容
//                .setWhen(System.currentTimeMillis()); // 设置该通知发生的时间
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            builder.setChannelId("notification_id");
//        }
//
//        Notification notification = builder.build(); // 获取构建好的Notification
//        notification.defaults = Notification.DEFAULT_SOUND; //设置为默认的声音
//        startForeground(110, notification);
    }

}
