package com.app.remote_controller_app.fragments.component_options;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.app.remote_controller_app.MainActivity;
import com.app.remote_controller_app.R;
import com.app.remote_controller_app.components.Component;


public class TextField extends Options {

    com.app.remote_controller_app.components.FieldArea thisComponent;
    EditText editTextPrefix;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisComponent = (com.app.remote_controller_app.components.FieldArea) ((MainActivity) getActivity()).getCurrentSelectedComponent();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflateFragment(R.layout.fragment_text_field, inflater, container, thisComponent);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.component_TextField);

        editTextPrefix = view.findViewById(R.id.editTextPrefix);
        editTextPrefix.setText(thisComponent.getPrefix());

        return view;
    }

    @Override
    public void updateComponent(Component thisComponent) {
        super.updateComponent(thisComponent);
        this.thisComponent.setPrefix(editTextPrefix.getText().toString());
    }
}