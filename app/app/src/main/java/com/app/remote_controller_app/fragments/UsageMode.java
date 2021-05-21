package com.app.remote_controller_app.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.app.remote_controller_app.ConnectedThread;
import com.app.remote_controller_app.MainActivity;
import com.app.remote_controller_app.R;
import com.app.remote_controller_app.components.Component;


public class UsageMode extends Fragment {

    Handler handler;
    LinearLayout l;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == ConnectedThread.RESPONSE_MESSAGE) {
                    String txt = (String) msg.obj;
                    Log.v("BLUETOOTH", txt);
                    ((MainActivity) getActivity()).msgToDataProtocol(txt);
                }else if(msg.what == ConnectedThread.DISCONNECT_MESSAGE){
                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                    navController.navigate(R.id.action_usageMode_to_opening);
                }
            }
        };
        ((MainActivity) getActivity()).startTransmission(handler);
        ((MainActivity) getActivity()).setComponentBluetoothService();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).getSupportActionBar().hide();
    }
    @Override
    public void onStop() {
        super.onStop();
        ((MainActivity) getActivity()).closeTransmission();
        ((MainActivity) getActivity()).getSupportActionBar().show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_usage_mode, container, false);
        l = (LinearLayout) view.findViewById(R.id.test_layout);
        for(Component c : ((MainActivity) getActivity()).getCurrentSelectedController().getListOfComponents()) {
            l.addView(c.getUsageView(getContext()));
            Log.v("Component", c.getId());
        }
        return view;
    }

}