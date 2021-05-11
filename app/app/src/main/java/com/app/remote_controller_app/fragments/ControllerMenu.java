package com.app.remote_controller_app.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.remote_controller_app.MainActivity;
import com.app.remote_controller_app.R;

import java.io.StringReader;

public class ControllerMenu extends Fragment {

    Button btnEditMode;
    Button btnUsageMode;
    Button btnDelete;
    Button btnFavorite;
    TextView textView_name;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) getActivity()).pairWithFavoriteDevice();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_controller_menu, container, false);

        textView_name = view.findViewById(R.id.textView_ControllerMenu_name);
        textView_name.setText(((MainActivity) getActivity()).getCurrentSelectedController().toString());

        btnEditMode = (Button) view.findViewById(R.id.button_editMode);
        btnEditMode.setOnClickListener(listenerEditMode);

        btnUsageMode = (Button) view.findViewById(R.id.button_usageMode);
        btnUsageMode.setOnClickListener(listenerUsageMode);

        btnDelete = (Button) view.findViewById(R.id.button_delete);
        btnDelete.setOnClickListener(listenerDelete);

        btnFavorite = (Button) view.findViewById(R.id.button_favorite_device);
        btnFavorite.setOnClickListener(listenerFavorite);

        return view;
    }

    /* Co się dzieje po wybraniu Edit Mode */
    View.OnClickListener listenerEditMode = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            NavHostFragment.findNavController(ControllerMenu.this).navigate(R.id.action_controllerMenu_to_editMode);
        }
    };

    /* Co się dzieje po wybraniu Usage Mode */
    View.OnClickListener listenerUsageMode = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if( ((MainActivity) getActivity() ).isPair())
                NavHostFragment.findNavController(ControllerMenu.this).navigate(R.id.action_controllerMenu_to_usageMode);
            else
                Toast.makeText(getContext(), "Nie wybrano urządzenia bluetooth", Toast.LENGTH_SHORT).show();
        }
    };

    View.OnClickListener listenerFavorite = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((MainActivity) getActivity()).selectFavoriteDeviceAlert();
        }
    };

    /* Co się dzieje po wybraniu Delete */
    View.OnClickListener listenerDelete = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String deletedController = ((MainActivity) getActivity()).getCurrentSelectedController().toString();

            ((MainActivity) getActivity()).removeSelectedController();
            ((MainActivity) getActivity()).onBackPressed();

            Toast.makeText(getActivity(), getString(R.string.label_deleted) + " " + deletedController, Toast.LENGTH_SHORT).show();
        }
    };

}