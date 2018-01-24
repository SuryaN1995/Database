package com.example.android.navigator.scheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.widget.Toast;

import com.example.android.navigator.R;
import com.shashank.sony.fancytoastlib.FancyToast;

/**
 * Created by techjini on 12/1/18.
 */

public class MyJobService extends JobService {

    public MyJobService() {
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        FancyToast.makeText(this,
                "Job is up & running",
                Toast.LENGTH_SHORT,FancyToast.INFO, R.drawable.scheduler).show();
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        FancyToast.makeText(this,
                "Job cancelled",
                Toast.LENGTH_SHORT,FancyToast.INFO,R.drawable.scheduler).show();
        return false;
    }
}
