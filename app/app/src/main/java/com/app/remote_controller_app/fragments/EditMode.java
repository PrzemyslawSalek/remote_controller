package com.app.remote_controller_app.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.remote_controller_app.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class EditMode extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_mode, container, false);

        Button btnSampleEdit = (Button) view.findViewById(R.id.button_sampleEdit);
        btnSampleEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(EditMode.this).navigate(R.id.action_editMode_to_componentOptions);
            }
        });

        FloatingActionButton btnComponentSelection = (FloatingActionButton) view.findViewById(R.id.floatingActionButton_componentSelection);
        btnComponentSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(EditMode.this).navigate(R.id.action_editMode_to_componentSelection);
            }
        });

        return view;
    }
}