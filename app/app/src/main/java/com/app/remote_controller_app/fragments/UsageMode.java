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
                }
            }
        };
        ((MainActivity) getActivity()).startTransmission(handler);
        ((MainActivity) getActivity()).setComponentBluetoothService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_usage_mode, container, false);
        l = (LinearLayout) view.findViewById(R.id.test_layout);
        for(Component c : ((MainActivity) getActivity()).getCurrentSelectedController().getListOfComponents())
            l.addView(c.getUsageView(getContext()));
        return view;
    }

}