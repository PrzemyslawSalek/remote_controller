package com.app.remote_controller_app.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.app.remote_controller_app.Controller;
import com.app.remote_controller_app.MainActivity;
import com.app.remote_controller_app.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class Opening extends Fragment {

    ListView listView;
    EditText textButtonName;
    FloatingActionButton buttonAddController;
    View dialogLayout;
    ArrayAdapter<Controller> arrayAdapter;

    ArrayList<Controller> controllerList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restoreFromDatabase();
    }

    // Avoid passing null as the view of root
    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_opening, container, false);
        listView = (ListView) view.findViewById(R.id.list_view_layout);

        buttonAddController = (FloatingActionButton) view.findViewById(R.id.button_addController);
        buttonAddController.setOnClickListener(listenerAddController);

        arrayAdapter = new ArrayAdapter<Controller>(getContext(), android.R.layout.simple_list_item_1, controllerList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(listenerGetController);
        return view;
    }

    View.OnClickListener listenerAddController = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            dialogLayout = getLayoutInflater().inflate(R.layout.dialog_add_controller_layout, null);

            builder.setView(dialogLayout);
            builder.setPositiveButton("OK", listenerButtonCreation);
            builder.setNegativeButton("Cancel", null);

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    };

    DialogInterface.OnClickListener listenerButtonCreation = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            textButtonName = dialogLayout.findViewById(R.id.dialog_add_controller_edit_name);
            Controller newController = ((MainActivity) getActivity() ).addController(String.valueOf(textButtonName.getText()));
            controllerList.add(newController);
            NavHostFragment.findNavController(Opening.this).navigate(R.id.action_opening_to_controllerMenu);
        }
    };

    AdapterView.OnItemClickListener listenerGetController = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ((MainActivity) getActivity() ).setCurrentSelectedController(controllerList.get(position));
            NavHostFragment.findNavController(Opening.this).navigate(R.id.action_opening_to_controllerMenu);
        }
    };

    public void restoreFromDatabase(){
        controllerList = new ArrayList<>();
        controllerList = ((MainActivity) getActivity() ).listOfController();
    }
}