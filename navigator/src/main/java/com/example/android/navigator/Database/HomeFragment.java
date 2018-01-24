package com.example.android.navigator.Database;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.navigator.R;
import com.example.android.navigator.web.PicturesFragment;


public class HomeFragment extends Fragment {
    EditText Name, Pass, updateold, updatenew, delete;
    Button add_button, update_button, delete_button, view_button;
    DBAdapter helper;
    Toolbar toolbar;


    private PicturesFragment.OnFragmentInteractionListener mListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        helper = new DBAdapter(getActivity());
        setupToolbar();
        Name = rootView.findViewById(R.id.editName);
        Pass = rootView.findViewById(R.id.editPass);
        updateold = rootView.findViewById(R.id.editText3);
        updatenew = rootView.findViewById(R.id.editText5);
        delete = rootView.findViewById(R.id.editText6);
        add_button = rootView.findViewById(R.id.button);
        view_button = rootView.findViewById(R.id.button2);
        update_button = rootView.findViewById(R.id.button3);
        delete_button = rootView.findViewById(R.id.button4);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser(rootView);
            }
        });
        view_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewdata(rootView);
            }
        });
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(rootView);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(rootView);
            }
        });

        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PicturesFragment.OnFragmentInteractionListener) {
            mListener = (PicturesFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public void addUser(View view) {
        String t1 = Name.getText().toString();
        String t2 = Pass.getText().toString();
        if (t1.isEmpty() || t2.isEmpty()) {
            Message.message(getActivity(), "Enter Both Name and Password");
        } else {
            long id = helper.insertData(t1, t2);
            if (id <= 0) {
                Message.message(getActivity(), "Insertion Unsuccessful");
                Name.setText("");
                Pass.setText("");
            } else {
                Message.message(getActivity(), "Insertion Successful");
                Name.setText("");
                Pass.setText("");
            }
        }
    }

    public void viewdata(View view) {
        String data = helper.getData();
        Message.message(getActivity().getApplicationContext(), data);
    }

    public void update(View view) {
        String u1 = updateold.getText().toString();
        String u2 = updatenew.getText().toString();
        if (u1.isEmpty() || u2.isEmpty()) {
            Message.message(getActivity().getApplicationContext(), "Enter Data");
        } else {
            int a = helper.updateName(u1, u2);
            if (a <= 0) {
                Message.message(getActivity().getApplicationContext(), "Unsuccessful");
                updateold.setText("");
                updatenew.setText("");
            } else {
                Message.message(getActivity().getApplicationContext(), "Updated");
                updateold.setText("");
                updatenew.setText("");
            }
        }

    }

    public void delete(View view) {
        String uname = delete.getText().toString();
        if (uname.isEmpty()) {
            Message.message(getActivity().getApplicationContext(), "Enter Data");
        } else {
            int a = helper.delete(uname);
            if (a <= 0) {
                Message.message(getActivity().getApplicationContext(), "Unsuccessful");
                delete.setText("");
            } else {
                Message.message(getActivity().getApplicationContext(), "DELETED");
                delete.setText("");
            }
        }
    }

    void setupToolbar() {
        toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.hh);
        toolbar.setTitle("Home");
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
