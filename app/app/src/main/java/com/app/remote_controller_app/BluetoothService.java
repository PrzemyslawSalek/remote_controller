package com.app.remote_controller_app;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import java.util.ArrayList;

public class BluetoothService {
    public BluetoothDevice currentDevice;
    BluetoothAdapter mBluetoothAdapter;
    ArrayList<BluetoothDevice> pairedDevices;

    public BluetoothService() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        pairedDevices = new ArrayList<>();
        for(BluetoothDevice bt : mBluetoothAdapter.getBondedDevices()){
            pairedDevices.add(bt);
        }
    }

    public ArrayList<String> getAddressesList(){
        ArrayList<String> devicesList = new ArrayList<String>();
        for(BluetoothDevice bt : pairedDevices)
            devicesList.add(bt.getAddress());
        return devicesList;
    }

    public String[] getNameList(){
        ArrayList<String> devicesList = new ArrayList<String>();
        for(BluetoothDevice bt : pairedDevices)
            devicesList.add(bt.getName());
        return devicesList.toArray(new String[devicesList.size()]);
    }

    public void setCurrentDevice(int index) {
        this.currentDevice = pairedDevices.get(index);
    }

    public String getCurrentName(){
        return currentDevice.getName();
    }

    public String getCurrentAddress(){
        return currentDevice.getAddress();
    }

    public BluetoothAdapter getBluetoothAdapter() {
        return mBluetoothAdapter;
    }
}
