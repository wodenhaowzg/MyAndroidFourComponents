package com.example.myandroidfourcomponents.job;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobWorkItem;
import android.content.ComponentName;
import android.content.Context;

import com.example.myandroidfourcomponents.utils.MyLog;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyFirstJobScheduler extends JobScheduler {

    private static final String TAG = "MyFirstJobScheduler";

    private static final int JOB_UPDATE = 1;

    public static void executeJob(Context context){
        JobScheduler service = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        JobInfo jobInfo = new JobInfo.Builder(JOB_UPDATE,  new ComponentName(context, MyFirstJobService.class))
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED) // 任务执行的网络条件，wifi状态下
                .setPeriodic(1000) // 设置以执行周期性任务。
                .build();
        service.schedule(jobInfo);
    }

    @Override
    public int schedule(@NonNull JobInfo job) {
        MyLog.d(TAG, "schedule... job : " + job.toString());
        return JobScheduler.RESULT_SUCCESS;
    }

    @Override
    public int enqueue(@NonNull JobInfo job, @NonNull JobWorkItem work) {
        MyLog.d(TAG, "enqueue... job : " + job.toString() + " | wrok : " + work);
        return JobScheduler.RESULT_SUCCESS;
    }

    @Override
    public void cancel(int jobId) {
        MyLog.d(TAG, "cancel... jobId : " + jobId);
    }

    @Override
    public void cancelAll() {
        MyLog.d(TAG, "cancelAll... ");
    }

    @NonNull
    @Override
    public List<JobInfo> getAllPendingJobs() {
        return null;
    }

    @Nullable
    @Override
    public JobInfo getPendingJob(int jobId) {
        MyLog.d(TAG, "getPendingJob... " + jobId);
        return null;
    }
}
