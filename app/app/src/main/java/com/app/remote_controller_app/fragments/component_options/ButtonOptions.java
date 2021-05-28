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
import android.widget.EditText;
import android.widget.Toast;

import com.app.remote_controller_app.MainActivity;
import com.app.remote_controller_app.R;
import com.app.remote_controller_app.components.Component;


public class ButtonOptions extends Options {

    com.app.remote_controller_app.components.Button thisComponent;
    EditText editText_Button_message;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisComponent = (com.app.remote_controller_app.components.Button) ((MainActivity) getActivity()).getCurrentSelectedComponent();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflateFragment(R.layout.fragment_button_options, inflater, container, thisComponent);

        // Plain Text Message //
        editText_Button_message = view.findViewById(R.id.editText_Button_message);
        editText_Button_message.setText(thisComponent.getMsg());

        return view;
    }

    @Override
    public void updateComponent(Component thisComponent) {
        super.updateComponent(thisComponent);
        this.thisComponent.setMsg(editText_Button_message.getText().toString());
    }
}