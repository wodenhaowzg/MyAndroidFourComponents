package com.example.myandroidfourcomponents.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.example.myandroidfourcomponents.R;
import com.example.myandroidfourcomponents.activity.launch.LaunchActivity;
import com.example.myandroidfourcomponents.activity.launch2.LaunchActivity2;
import com.example.myandroidfourcomponents.databinding.ActivityMainBinding;
import com.example.myandroidfourcomponents.dialog.NotificationDialog;
import com.example.myandroidfourcomponents.sevice.MainService;
import com.example.myandroidfourcomponents.utils.permission.PermissionUtils;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

import androidx.databinding.DataBindingUtil;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    private ActivityMainBinding viewDataBinding;
    boolean mControlMainService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewDataBinding.mainStartNext.setOnClickListener(this);
        viewDataBinding.mainStartNext2.setOnClickListener(this);
        viewDataBinding.mainStartNext3.setOnClickListener(this);
        viewDataBinding.mainStartService1.setOnClickListener(this);
        viewDataBinding.mainTestNotification.setOnClickListener(this);
        PermissionUtils.checkNotificationPermission(this);

        initHandleThread();
    }

    @Override
    public void onClickImpl(View v) {
        if (v.getId() == viewDataBinding.mainStartNext.getId()) {
            localStartActivity(LaunchActivity.class);
        } else if (v.getId() == viewDataBinding.mainStartNext2.getId()) {
            localStartActivity(LaunchActivity2.class);
        } else if (v.getId() == viewDataBinding.mainStartNext3.getId()) {
//            localStartActivityWithNewTask(BActivity.class);
        } else if (v.getId() == viewDataBinding.mainStartService1.getId()) {
            Intent i = new Intent(this, MainService.class);
            if (mControlMainService) {
                stopService(i);
                viewDataBinding.mainStartService1.setText("开启服务MAIN");
            } else {
                startService(i);
                viewDataBinding.mainStartService1.setText("停止服务MAIN");
            }
            mControlMainService = !mControlMainService;
        } else if (v.getId() == viewDataBinding.mainTestNotification.getId()) {
            NotificationDialog dialog = new NotificationDialog(this);
            dialog.show();
        }
    }

    private void receiveMessage(Message msg) {
        switch (msg.what) {
            case 1:
                Log.d("HandlerTest", "Recv msg 1");
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("HandlerTest", "Execute msg 1 over");
                break;
            case 2:
                Log.d("HandlerTest", "Recv msg 2");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    // ------------------------------ Handle test ------------------------------

    private LocalHandler mLocalHandler;

    // 测试发现 setAsynchronous 标记能将 message 同步处理，并阻塞其他异步消息。
    private void initHandleThread() {
        HandlerThread mHandlerThread = new HandlerThread("MainActivity");
        mHandlerThread.start();
        mLocalHandler = new LocalHandler(mHandlerThread.getLooper(), this);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mLocalHandler.sendEmptyMessage(2);
            }
        }, 0, 1000);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Message obtain = Message.obtain(mLocalHandler, 1);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                    obtain.setAsynchronous(false);
                }
                obtain.sendToTarget();
            }
        }, 5000);
    }

    private static class LocalHandler extends Handler {
        private final WeakReference<MainActivity> mOutReference;

        LocalHandler(Looper looper, MainActivity outReference) {
            super(looper);
            this.mOutReference = new WeakReference<>(outReference);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity obj = mOutReference.get();
            if (obj == null) {
                return;
            }

            obj.receiveMessage(msg);
        }
    }
}