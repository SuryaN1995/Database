package com.example.android.navigator.serviceClass;

import android.app.DownloadManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.android.navigator.R;

import static android.content.Context.DOWNLOAD_SERVICE;
import static android.content.Context.JOB_SCHEDULER_SERVICE;
import static android.content.Context.NOTIFICATION_SERVICE;


public class ServiceFragment extends Fragment implements View.OnClickListener{

    private DownloadManager downloadManager;
    private long Image_DownloadId;
    private OnFragmentInteractionListener mListener;
    private Toolbar toolbar;
    int done=0;
    JobScheduler jobScheduler;
    private static final int MYJOBID = 1;
    public ServiceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_service, container, false);
        setupToolbar();
        Button DownloadImage = v.findViewById(R.id.Download);
        DownloadImage.setOnClickListener(this);
        Button CancelDownload =v.findViewById(R.id.CancelDownload);
        jobScheduler = (JobScheduler)getActivity().getSystemService(JOB_SCHEDULER_SERVICE);
        //CancelDownload.setEnabled(false);
        CancelDownload.setOnClickListener(this);
        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        getActivity().registerReceiver(downloadReceiver, filter);
        return v;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.Download:
                Uri image_uri = Uri.parse("http://www.androidtutorialpoint.com/wp-content/uploads/2016/09/Beauty.jpg");
                Image_DownloadId = DownloadData(image_uri, v);
                done=1;
                break;

            case R.id.CancelDownload:
                downloadManager.remove(Image_DownloadId);
                done=0;
                break;
        }
    }


    private long DownloadData (Uri uri, View v) {

        long downloadReference;
        downloadManager = (DownloadManager)getActivity().getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setTitle("Data Download");
        request.setDescription("Android Data download using DownloadManager.");
       if(v.getId() == R.id.Download) {
           request.setDestinationInExternalFilesDir(getActivity(), Environment.DIRECTORY_DOWNLOADS, "AndroidTutorialPoint.jpg");
       }
        downloadReference = downloadManager.enqueue(request);
        return downloadReference;
    }

    private BroadcastReceiver downloadReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            //check if the broadcast message is for our Enqueued download
            long referenceId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            String s="";
            if(referenceId == Image_DownloadId) {
                if(done==1)
                    s="Download Complete";
                else
                    s="Download Incomplete";
                PendingIntent pIntent = PendingIntent.getActivity(getActivity(), (int) System.currentTimeMillis(), intent, 0);
                Notification n  = new Notification.Builder(getContext())
                        .setContentTitle("Download")
                        .setContentText(s)
                        .setSmallIcon(R.drawable.services)
                        .setContentIntent(pIntent)
                        .setAutoCancel(true).build();
                NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);
                n.flags |= Notification.FLAG_AUTO_CANCEL;
                notificationManager.notify(0, n);
            }
        }
    };

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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    void setupToolbar() {
        toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setTitle("Services");
    }
}
