package com.app.remote_controller_app.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.remote_controller_app.ConnectedThread;
import com.app.remote_controller_app.Controller;
import com.app.remote_controller_app.R;


public class UsageMode extends Fragment {

    ConnectedThread ct;
    Controller c;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ct = new ConnectedThread();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_usage_mode, container, false);
    }


}