package com.example.android.navigator.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.android.navigator.BR;

/**
 * Created by techjini on 6/1/18.
 */


public class SampleModel extends BaseObservable {
    private String username, useremail;
    private boolean isOnline;

    public SampleModel(String username, String useremail, boolean value) {
        this.username = username;
        this.useremail = useremail;
        isOnline = value;
    }

    @Bindable
    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
        notifyChange();
    }

    @Bindable
    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
        notifyChange();
    }

    /**
     * Checks if online or not
     * @return boolean value of true or false
     */
    @Bindable
    public boolean isOnline() {
        return isOnline;
    }


    public void setOnline(boolean online) {
        isOnline = online;
        notifyPropertyChanged(BR.online);
    }
}