package com.example.android.navigator.scheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import com.example.android.navigator.R;
import com.example.android.navigator.main.MainActivity;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

import static android.content.Context.JOB_SCHEDULER_SERVICE;


public class SchedulerFragment extends Fragment {

    Toolbar toolbar;
    private OnFragmentInteractionListener mListener;

    Chronometer chronometer;
    Button btnStartJob, btnCancelJobs;

    JobScheduler jobScheduler;
    private static final int MYJOBID = 1;


    public SchedulerFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_scheduler, container, false);
        setupToolbar();
        chronometer = v.findViewById(R.id.chronometer);
        btnStartJob = v.findViewById(R.id.startjob);
        btnCancelJobs =  v.findViewById(R.id.canceljobs);

        jobScheduler = (JobScheduler)getActivity().getSystemService(JOB_SCHEDULER_SERVICE);
        /**
         * starting the chronometer reading
         */
        btnStartJob.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();

                /**
                 * Initialize the job service and get info about the job with the constraint
                 * that is set like setRequiresCharge(true/false)
                 */
                ComponentName jobService =
                        new ComponentName(getActivity().getPackageName(), MyJobService.class.getName());
                JobInfo jobInfo =
                        new JobInfo.Builder(MYJOBID, jobService).setRequiredNetworkType(JobInfo.NETWORK_TYPE_NOT_ROAMING).setRequiresCharging(true).setPeriodic(10000).build();


                int jobId = jobScheduler.schedule(jobInfo);
                if(jobScheduler.schedule(jobInfo)>JobScheduler.RESULT_FAILURE)
                    FancyToast.makeText(getActivity(), "Successfully scheduled job: " + jobId,
                        Toast.LENGTH_SHORT,FancyToast.SUCCESS, R.drawable.scheduler).show();
                else{
                    FancyToast.makeText(getActivity(), "RESULT_FAILURE: " + jobId, Toast.LENGTH_SHORT,FancyToast.ERROR,R.drawable.scheduler).show();
                }

            }});

        btnCancelJobs.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                chronometer.stop();
                //Get all the info about pending jobs
                List<JobInfo> allPendingJobs = jobScheduler.getAllPendingJobs();
                String s = "";
                //Destroying all pending jobs created by that services
                for(JobInfo j : allPendingJobs){
                    int jId = j.getId();
                    jobScheduler.cancel(jId);
                    s += "jobScheduler.cancel(" + jId + " )";
                }
                FancyToast.makeText(getActivity(), s, Toast.LENGTH_SHORT,FancyToast.INFO,R.drawable.scheduler).show();
            }});
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    void setupToolbar() {
        toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getmMainContext().onBackPressed();
            }
        });
        toolbar.setTitle("Scheduler");
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
