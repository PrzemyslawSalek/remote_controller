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
import com.app.remote_controller_app.components.Component;


public class ButtonOptions extends Fragment {

    Component thisComponent;

    EditText editText_id;
    EditText editText_name;
    Button button_delete;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisComponent = ((MainActivity) getActivity()).getCurrentSelectedComponent();
    }

    @Override
    public void onPause() {
        super.onPause();
        // Tutaj dodaj metode zapisywania zmian komponentu w bazie //
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_button_options, container, false);

        inflateViews(view);
        button_delete = (Button) view.findViewById(R.id.button_Button_delete);
        button_delete.setOnClickListener(listenerDeleteButton);

        return view;
    }

    // Metoda odpowiedzialna za uzupełnienie wszystkich widoków //
    private void inflateViews(View view) {
        // Plain Text ID //
        editText_id = view.findViewById(R.id.editText_Button_id);
        editText_id.setText(thisComponent.getId());

        // Plain Text Name //
        editText_name = view.findViewById(R.id.editText_Button_name);
        editText_name.setText(thisComponent.getName());

        // Color (pewnie jakaś lista) //

        // Plain Text Size X //

        // Plain Text Size Y //

        // Plain Text Position X //

        // Plain Text Position Y //

        // Plain Text Message //
    }

    // Metoda dla przycisku "Usun" //
    View.OnClickListener listenerDeleteButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(), getString(R.string.action_delete), Toast.LENGTH_SHORT).show();
            // Usuń Button //
        }
    };
}