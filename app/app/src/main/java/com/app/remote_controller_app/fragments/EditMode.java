package com.app.remote_controller_app.fragments;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.app.remote_controller_app.MainActivity;
import com.app.remote_controller_app.R;
import com.app.remote_controller_app.components.Component;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class EditMode extends Fragment {

    FloatingActionButton btnComponentSelection;
    ConstraintLayout l;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).getSupportActionBar().hide();
    }
    @Override
    public void onStop() {
        super.onStop();
        ((MainActivity) getActivity()).getSupportActionBar().show();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v("Button", "create");
        View view = inflater.inflate(R.layout.fragment_edit_mode, container, false);
        l = view.findViewById(R.id.component_view);

        for(Component c : ((MainActivity) getActivity()).getCurrentSelectedController().getListOfComponents()) {
            l.addView(c.getEditView(getContext(), EditMode.this));
        }

        btnComponentSelection = (FloatingActionButton) view.findViewById(R.id.floatingActionButton_componentSelection);
        btnComponentSelection.setOnClickListener(listenerComponentSelection);

        return view;
    }

    /* Co się dzieje po kliknięciu przycisku "Wybór komponentów" */
    View.OnClickListener listenerComponentSelection = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            NavHostFragment.findNavController(EditMode.this).navigate(R.id.action_editMode_to_componentSelection);
        }
    };
}