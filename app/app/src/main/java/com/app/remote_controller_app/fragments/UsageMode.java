package com.app.remote_controller_app.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.app.remote_controller_app.BluetoothService;
import com.app.remote_controller_app.ConnectedThread;
import com.app.remote_controller_app.Controller;
import com.app.remote_controller_app.MainActivity;
import com.app.remote_controller_app.R;
import com.app.remote_controller_app.components.Button;


public class UsageMode extends Fragment {

    Controller currentController;
    BluetoothService bluetoothService;
    Handler handler;

    LinearLayout l;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.currentController = ((MainActivity) getActivity() ).getCurrentSelectedController();
        this.bluetoothService = ((MainActivity) getActivity() ).getBluetoothService();

        handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what == ConnectedThread.RESPONSE_MESSAGE){
                    String txt = (String)msg.obj;
                    Log.v("BLUETOOTH", txt);
                }
            }
        };

        bluetoothService.startTransmission(handler);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_usage_mode, container, false);
        l = (LinearLayout) view.findViewById(R.id.test_layout);
        Button myBtn = new Button("proba_buttonowska",1.2f,12,0,0, false);
        myBtn.setBluetoothService(bluetoothService);
        l.addView(myBtn.getUsageView(getContext()));
        return view;
    }

}