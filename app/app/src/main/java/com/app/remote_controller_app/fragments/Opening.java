package com.app.remote_controller_app.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.app.remote_controller_app.MainActivity;
import com.app.remote_controller_app.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class Opening extends Fragment {

    ListView listView;
    EditText textButtonName;
    FloatingActionButton buttonAddController;
    View dialogLayout;
    ArrayAdapter<String> arrayAdapter;

    ArrayList<String> arrayList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Avoid passing null as the view of root
    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_opening, container, false);

        listView = (ListView) view.findViewById(R.id.list_view_layout);


        buttonAddController = (FloatingActionButton) view.findViewById(R.id.button_addController);
        buttonAddController.setOnClickListener(listenerAddController);

        arrayList.add("sample");

        arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, arrayList);
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
            arrayList.add( String.valueOf(textButtonName.getText()) );
        }
    };

    AdapterView.OnItemClickListener listenerGetController = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getContext(), arrayList.get(position), Toast.LENGTH_SHORT).show();
        }
    };
}