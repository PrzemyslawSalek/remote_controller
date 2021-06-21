package com.app.remote_controller_app.fragments.component_options;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.app.remote_controller_app.MainActivity;
import com.app.remote_controller_app.R;
import com.app.remote_controller_app.components.Component;


public class TextAreaOptions extends Options {

    com.app.remote_controller_app.components.TextArea thisComponent;
    CheckBox checkBoxLogger;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisComponent = (com.app.remote_controller_app.components.TextArea) ((MainActivity) getActivity()).getCurrentSelectedComponent();
    }

    @Override
    public void onPause() {
        super.onPause();
        ((MainActivity) getActivity()).setCurrentSelectedComponent(null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflateFragment(R.layout.fragment_textarea_options, inflater, container, thisComponent);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.component_TextArea);

        // Plain Text Message //
        checkBoxLogger = view.findViewById(R.id.checkboxLogger);
        checkBoxLogger.setChecked(thisComponent.isLoggerEnabled());


        return view;
    }

    @Override
    public void updateComponent(Component thisComponent) {
        super.updateComponent(thisComponent);
        this.thisComponent.setLoggerEnabled(checkBoxLogger.isChecked());
    }


}