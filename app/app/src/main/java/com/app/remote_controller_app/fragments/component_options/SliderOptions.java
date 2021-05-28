package com.app.remote_controller_app.fragments.component_options;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.remote_controller_app.MainActivity;
import com.app.remote_controller_app.R;
import com.app.remote_controller_app.components.Component;
import com.app.remote_controller_app.components.SeekBar;


public class SliderOptions extends Options {

    SeekBar thisComponent;

    EditText editText_rangeMIN;
    EditText editText_rangeMAX;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisComponent = (com.app.remote_controller_app.components.SeekBar) ((MainActivity) getActivity()).getCurrentSelectedComponent();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflateFragment(R.layout.fragment_slider_options, inflater, container, thisComponent);

        // Plain Text Range MIN //
        editText_rangeMIN = view.findViewById(R.id.editTextNumber_Slider_rangeMIN);
        editText_rangeMIN.setText(String.valueOf(thisComponent.getMinRange()));

        // Plain Text Range MAX //
        editText_rangeMAX = view.findViewById(R.id.editTextNumber_Slider_rangeMAX);
        editText_rangeMAX.setText(String.valueOf(thisComponent.getMaxRange()));

        return view;
    }

    @Override
    public void updateComponent(Component thisComponent) {
        super.updateComponent(thisComponent);
        this.thisComponent.setMaxRange(Integer.parseInt(editText_rangeMAX.getText().toString()));
        this.thisComponent.setMinRange(Integer.parseInt(editText_rangeMIN.getText().toString()));
    }
}