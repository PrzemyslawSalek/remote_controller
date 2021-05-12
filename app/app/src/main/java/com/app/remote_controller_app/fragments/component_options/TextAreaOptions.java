package com.app.remote_controller_app.fragments.component_options;

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

        // Plain Text Size Y //
        editText_sizeY = view.findViewById(R.id.editTextNumber_TextArea_sizeY);

        // Plain Text Position X //
        editText_posX = view.findViewById(R.id.editTextNumber_TextArea_positionX);

        // Plain Text Position Y //
        editText_posY = view.findViewById(R.id.editTextNumber_TextArea_positionY);
    }

    // Metoda dla przycisku "Zapisz" //
    View.OnClickListener listenerSaveButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((MainActivity) getActivity()).onBackPressed();
        }
    };

    // Metoda dla przycisku "Usuń" //
    View.OnClickListener listenerDeleteButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            // Metoda do usuwania TextArea  <--- TUTAJ DOPISAC //

            ((MainActivity) getActivity()).onBackPressed();
            Toast.makeText(getActivity(), getString(R.string.label_deleted), Toast.LENGTH_SHORT).show();
        }
    };

    public void updateComponent(){
        thisComponent.setId(editText_id.getText().toString());
        //thisComponent.setName(editText_name.getText().toString());
    }
}