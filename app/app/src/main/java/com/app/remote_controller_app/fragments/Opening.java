package com.app.remote_controller_app.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.remote_controller_app.MainActivity;
import com.app.remote_controller_app.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Opening extends Fragment {

    private ConstraintLayout mLayout;
    private EditText textButtonName;
    private FloatingActionButton buttonAddController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_opening, container, false);

//        FloatingActionButton btnAddController = (FloatingActionButton) view.findViewById(R.id.button_addController);
//        btnAddController.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        mLayout = (ConstraintLayout) view.findViewById(R.id.__opening);

        buttonAddController = (FloatingActionButton) view.findViewById(R.id.button_addController);
        buttonAddController.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View dialogLayout = getLayoutInflater().inflate(R.layout.dialog_add_controller_layout, null);

                builder.setView(dialogLayout);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textButtonName = dialogLayout.findViewById(R.id.dialog_add_controller_edit_name);
                        mLayout.addView(createNewButton(textButtonName.getText().toString()));
                    }
                });
                builder.setNegativeButton("Cancel", null);

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return view;
    }


    private Button createNewButton(String text) {
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        Button newButton = new Button(getContext());

        newButton.setLayoutParams(lparams);
        newButton.setText(text);
        newButton.setTag(text);
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), (String) v.getTag(), Toast.LENGTH_SHORT).show();
            }
        });

        return newButton;
    }

}