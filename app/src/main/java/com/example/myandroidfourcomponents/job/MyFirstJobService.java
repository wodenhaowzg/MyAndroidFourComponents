package com.example.myandroidfourcomponents.job;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.widget.Toast;

public class MyFirstJobService extends JobService {

    @Override
    public boolean onStartJob(JobParameters params) {
        int jobId = params.getJobId();
        Toast.makeText(this, "开始执行任务: " + jobId, Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        int jobId = params.getJobId();
        Toast.makeText(this, "停止执行任务: " + jobId, Toast.LENGTH_SHORT).show();
        return false;
    }
}
