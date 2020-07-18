package com.example.myandroidfourcomponents.job;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobWorkItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myandroidfourcomponents.utils.MyLog;

import java.util.List;

public class MyFirstJobScheduler extends JobScheduler {

    private static final String TAG = "MyFirstJobScheduler";

    @Override
    public int schedule(@NonNull JobInfo job) {
        MyLog.d(TAG, "schedule... job : " + job.toString());
        return JobScheduler.RESULT_SUCCESS;
    }

    @Override
    public int enqueue(@NonNull JobInfo job, @NonNull JobWorkItem work) {
        return JobScheduler.RESULT_SUCCESS;
    }

    @Override
    public void cancel(int jobId) {

    }

    @Override
    public void cancelAll() {

    }

    @NonNull
    @Override
    public List<JobInfo> getAllPendingJobs() {
        return null;
    }

    @Nullable
    @Override
    public JobInfo getPendingJob(int jobId) {
        return null;
    }
}
