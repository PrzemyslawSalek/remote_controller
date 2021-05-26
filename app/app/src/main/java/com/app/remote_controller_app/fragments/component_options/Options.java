package com.app.remote_controller_app.fragments.component_options;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.app.remote_controller_app.MainActivity;
import com.app.remote_controller_app.R;
import com.app.remote_controller_app.components.Component;


public class Options extends Fragment {

    EditText editText_id;
    EditText editText_sizeX;
    EditText editText_sizeY;
    EditText editText_posX;
    EditText editText_posY;

    Button button_save;
    Button button_delete;

    protected View inflateFragment(int resId, LayoutInflater inflater, ViewGroup container, Component thisComponent) {
        View view = inflater.inflate(resId, container, false);
        ConstraintLayout layout = (ConstraintLayout)view.findViewById(R.id.general_layout);

        button_save = (Button) view.findViewById(R.id.button_save);
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateComponent(thisComponent);
                ((MainActivity) getActivity()).updateCurrentSelectedController();
                ((MainActivity) getActivity()).onBackPressed();
            }
        });

        button_delete = (Button) view.findViewById(R.id.button_delete);

        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(getString(R.string.label_deleteTextArea));
                builder.setMessage(getString(R.string.label_deleteTextAreaSure));

                builder.setPositiveButton(getString(R.string.action_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ((MainActivity) getActivity()).removeComponentInSelectedController(thisComponent);
                        ((MainActivity) getActivity()).updateCurrentSelectedController();

                        ((MainActivity) getActivity()).onBackPressed();
                        Toast.makeText(getActivity(), getString(R.string.label_deleted), Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton(getString(R.string.action_no), null);
                builder.setIcon(android.R.drawable.ic_dialog_alert);

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        // Plain Text ID //
        editText_id = view.findViewById(R.id.editText_id);
        editText_id.setText(thisComponent.getId());

        // Plain Text Size X //
        editText_sizeX = view.findViewById(R.id.editTextNumber_sizeX);
        editText_sizeX.setText(String.valueOf(thisComponent.getSizeX()));

        // Plain Text Size Y //
        editText_sizeY = view.findViewById(R.id.editTextNumber_sizeY);
        editText_sizeY.setText(String.valueOf(thisComponent.getSizeY()));

        // Plain Text Position X //
        editText_posX = view.findViewById(R.id.editTextNumber_positionX);
        editText_posX.setText(String.valueOf(thisComponent.getPosX()));

        // Plain Text Position Y //
        editText_posY = view.findViewById(R.id.editTextNumber_positionY);
        editText_posY.setText(String.valueOf(thisComponent.getPosY()));

        setHasOptionsMenu(true);
        return view;
    }


    public void updateComponent(Component thisComponent){
        thisComponent.setId(editText_id.getText().toString());
        thisComponent.resize(Integer.parseInt(editText_sizeX.getText().toString()), Integer.parseInt(editText_sizeY.getText().toString()));
        thisComponent.move(Integer.parseInt(editText_posX.getText().toString()), Integer.parseInt(editText_posY.getText().toString()));
        //thisComponent.setName(editText_name.getText().toString());
    }




}