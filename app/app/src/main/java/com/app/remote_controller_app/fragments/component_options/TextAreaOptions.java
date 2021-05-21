package com.app.remote_controller_app.fragments.component_options;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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


public class TextAreaOptions extends Fragment {

    com.app.remote_controller_app.components.TextArea thisComponent;

    EditText editText_id;
    EditText editText_sizeX;
    EditText editText_sizeY;
    EditText editText_posX;
    EditText editText_posY;
    Button button_save;
    Button button_delete;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisComponent = (com.app.remote_controller_app.components.TextArea) ((MainActivity) getActivity()).getCurrentSelectedComponent();
    }

    @Override
    public void onPause() {
        super.onPause();
        updateComponent();
        ((MainActivity) getActivity()).updateCurrentSelectedController();
        ((MainActivity) getActivity()).setCurrentSelectedComponent(null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_textarea_options, container, false);

        inflateViews(view);

        button_save = (Button) view.findViewById(R.id.button_TextArea_save);
        button_save.setOnClickListener(listenerSaveButton);

        button_delete = (Button) view.findViewById(R.id.button_TextArea_delete);
        button_delete.setOnClickListener(listenerDeleteButton);

        return view;
    }

    // Metoda odpowiedzialna za uzupełnienie wszystkich widoków //
    private void inflateViews(View view) {
        // Plain Text ID //
        editText_id = view.findViewById(R.id.editText_TextArea_id);
        editText_id.setText(thisComponent.getId());

        // Plain Text Size X //
        editText_sizeX = view.findViewById(R.id.editTextNumber_TextArea_sizeX);
        editText_sizeX.setText(String.valueOf(thisComponent.getSizeX()*100));

        // Plain Text Size Y //
        editText_sizeY = view.findViewById(R.id.editTextNumber_TextArea_sizeY);
        editText_sizeY.setText(String.valueOf(thisComponent.getSizeY()*100));

        // Plain Text Position X //
        editText_posX = view.findViewById(R.id.editTextNumber_TextArea_positionX);

        // Plain Text Position Y //
        editText_posY = view.findViewById(R.id.editTextNumber_TextArea_positionY);
    }

    // Metoda dla przycisku "Zapisz" //
    View.OnClickListener listenerSaveButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            updateComponent();
            ((MainActivity) getActivity()).updateCurrentSelectedController();
            ((MainActivity) getActivity()).onBackPressed();
        }
    };

    // Metoda dla przycisku "Usuń" //
    View.OnClickListener listenerDeleteButton = new View.OnClickListener() {
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
    };

    public void updateComponent(){
        thisComponent.setId(editText_id.getText().toString());
        float x = Float.parseFloat(editText_sizeX.getText().toString());
        float y = Float.parseFloat(editText_sizeY.getText().toString());
        if(x>100)
            x=100;
        if(x<10)
            x=10;

        if(y>100)
            y=100;
        if(y<10)
            y=10;

        thisComponent.resize(x/100.f, y/100.f);
        //thisComponent.setName(editText_name.getText().toString());
    }
}