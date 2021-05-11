package com.app.remote_controller_app.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.app.remote_controller_app.Controller;
import com.app.remote_controller_app.MainActivity;
import com.app.remote_controller_app.R;
import com.app.remote_controller_app.lists.adapters.ControllerListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class Opening extends Fragment {

    ListView listView;
    FloatingActionButton buttonAddController;
    ControllerListAdapter arrayAdapter;

    ArrayList<Controller> controllerList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restoreFromDatabase();
        for(Controller c: controllerList)
            Log.v("Controller", String.valueOf(c.getId()) + "- " + c);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }
    @Override
    public void onStop() {
        super.onStop();
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // Avoid passing null as the view of root
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_opening, container, false);
        listView = (ListView) view.findViewById(R.id.list_view_layout);

        buttonAddController = (FloatingActionButton) view.findViewById(R.id.button_addController);
        buttonAddController.setOnClickListener(listenerAddController);

        arrayAdapter = new ControllerListAdapter(getActivity(), R.layout.controller_list_item, controllerList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(listenerGetController);
        return view;
    }

    View.OnClickListener listenerAddController = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

            EditText inputText = new EditText(getContext());
            builder.setView(inputText);
            builder.setTitle(getString(R.string.enter_controller_name));

            builder.setPositiveButton(getString(R.string.action_ok), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Controller newController = ((MainActivity) getActivity() ).addController(String.valueOf(inputText.getText()));
                    controllerList.add(newController);
                    arrayAdapter.notifyDataSetChanged();
                }
            });

            builder.setNegativeButton(getString(R.string.action_cancel), null);
            AlertDialog dialog = builder.create();
            dialog.show();
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