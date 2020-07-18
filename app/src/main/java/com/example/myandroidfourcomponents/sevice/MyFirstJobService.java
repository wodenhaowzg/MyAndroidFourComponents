package com.example.myandroidfourcomponents.sevice;

import android.app.job.JobParameters;
import android.app.job.JobService;

class MyFirstJobService extends JobService {

    @Override
    public boolean onStartJob(JobParameters params) {
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
