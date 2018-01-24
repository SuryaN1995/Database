package com.example.android.navigator.databinding;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.navigator.R;
import com.example.android.navigator.main.MainActivity;
import com.example.android.navigator.model.SampleModel;


public class UserBindingFragment extends Fragment {

    SampleModel model;
    Toolbar toolbar;
    private FragmentUserBindingBinding fragment_user;
    private OnFragmentInteractionListener mListener;

    public UserBindingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

/**
 * Using of DataBindingUtil to incorporate the inflation using dataBinding
 */
        fragment_user = DataBindingUtil.inflate(inflater, R.layout.fragment_user_binding, container, false);
        fragment_user.setHandler(this);
        setupToolbar();
        model = new SampleModel("User", "useremailid@gmail.com", true);
        fragment_user.setModel(model);
        return fragment_user.getRoot();
    }

    public void onButtonClick(boolean status) {
        model.setOnline(status);
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

    /**
     * Toolbar is manually set for preferred view
     */
    void setupToolbar() {
        toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getmMainContext().onBackPressed();
            }
        });
        toolbar.setTitle("Profile");
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
