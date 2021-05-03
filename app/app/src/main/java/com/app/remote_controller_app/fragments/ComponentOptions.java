package com.app.remote_controller_app.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.app.remote_controller_app.MainActivity;
import com.app.remote_controller_app.R;
import com.app.remote_controller_app.components.Component;


public class ComponentOptions extends Fragment {

    EditText editText_id;

    Component thisComponent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisComponent = ((MainActivity) getActivity()).getCurrentSelectedComponent();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_component_options, container, false);

        editText_id = view.findViewById(R.id.editText_id);
        editText_id.setText(thisComponent.getId());

        return view;
    }
}