package com.app.remote_controller_app.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.remote_controller_app.R;

public class Opening extends Fragment {

    //======    THIS IS VERY FIRST FRAGMENT ======\\

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_opening, container, false);
    }

    //TODO make the "add new controller" button works ;)
}