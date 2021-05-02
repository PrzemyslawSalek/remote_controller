package com.app.remote_controller_app.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.app.remote_controller_app.BluetoothService;
import com.app.remote_controller_app.Controller;
import com.app.remote_controller_app.MainActivity;
import com.app.remote_controller_app.R;

import java.util.function.ToIntBiFunction;


public class ControllerMenu extends Fragment {

    Button btnEditMode;
    Button btnUsageMode;
    Button btnDelete;
    Controller currentController;
    BluetoothService bluetoothService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        this.currentController = ((MainActivity) getActivity() ).getCurrentSelectedController();
        this.bluetoothService = ((MainActivity) getActivity() ).getBluetoothService();
        if(currentController.getFavoriteMAC()!=null){
            boolean connect=bluetoothService.pairWithFavoriteMAC(currentController.getFavoriteMAC());
            if(!connect){
                //Nie udało się połączyć z domyślnym urządzeniem

            }
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_controller_menu, container, false);

        btnEditMode = (Button) view.findViewById(R.id.button_editMode);
        btnEditMode.setOnClickListener(listenerEditMode);

        btnUsageMode = (Button) view.findViewById(R.id.button_usageMode);
        btnUsageMode.setOnClickListener(listenerUsageMode);

        btnDelete = (Button) view.findViewById(R.id.button_delete);
        btnDelete.setOnClickListener(listenerDelete);

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
            if( ((MainActivity) getActivity() ).getBluetoothService().isPair())
                NavHostFragment.findNavController(ControllerMenu.this).navigate(R.id.action_controllerMenu_to_usageMode);
            else
                Toast.makeText(getContext(), "Nie wybrano urządzenia bluetooth", Toast.LENGTH_SHORT).show();
        }
    };

    /* Co się dzieje po wybraniu Delete */
    View.OnClickListener listenerDelete = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(), "Delete", Toast.LENGTH_SHORT).show();
        }
    };

}