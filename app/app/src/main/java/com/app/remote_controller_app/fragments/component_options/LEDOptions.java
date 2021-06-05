package com.app.remote_controller_app.fragments.component_options;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.remote_controller_app.MainActivity;
import com.app.remote_controller_app.R;
import com.app.remote_controller_app.components.Component;
import com.app.remote_controller_app.tools.SelectColorTool;

import java.math.BigInteger;


public class LEDOptions extends Options {

    com.app.remote_controller_app.components.LED thisComponent;
    EditText editColor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisComponent = (com.app.remote_controller_app.components.LED) ((MainActivity) getActivity()).getCurrentSelectedComponent();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflateFragment(R.layout.fragment_led_options, inflater, container, thisComponent);
        editColor = view.findViewById(R.id.editColor);
        editColor.setText(thisComponent.getColor());
        editColor.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    SelectColorTool t = new SelectColorTool(getActivity(), new BigInteger(editColor.getText().toString(), 16).intValue());
                    t.show(new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ((EditText) v).setText(t.getColor());
                        }
                    });
                    return true;
                }
                return false;
            }
        });

        return view;
    }

    @Override
    public void updateComponent(Component thisComponent) {
        super.updateComponent(thisComponent);
        this.thisComponent.setColor(editColor.getText().toString());
    }
}