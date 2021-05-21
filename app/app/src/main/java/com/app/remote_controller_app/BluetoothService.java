package com.app.remote_controller_app;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class BluetoothService {
    BluetoothDevice currentDevice;
    BluetoothAdapter mBluetoothAdapter;
    ArrayList<BluetoothDevice> pairedDevices;

    ConnectedThread connectedThread;
    BluetoothSocket bluetoothSocket;

    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

    public BluetoothService() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        pairedDevices = new ArrayList<>();
        refreshDevices();
    }

    public void refreshDevices(){
        pairedDevices.clear();
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

    public boolean openBluetoothSocketForCurrentDevice() {
        try {
            bluetoothSocket = currentDevice.createRfcommSocketToServiceRecord(MY_UUID);
            bluetoothSocket.connect();
            Log.i("[BLUETOOTH]","Connected to: " + currentDevice.getName());
            return true;
        } catch (IOException e) {
            Log.i("[BLUETOOTH]","Pair error");
            bluetoothSocket = null;
            return false;
        }
    }

    public void closeBluetoothSocket(){
        try {
            if(bluetoothSocket != null)
                bluetoothSocket.close();
            bluetoothSocket = null;
            Log.i("[BLUETOOTH]","Bluetooth socket closed");
        } catch (IOException e) {
            Log.i("[BLUETOOTH]","Close bluetooth socket error");
        }
    }

    public void startTransmission(Handler handler){
        if(bluetoothSocket != null && bluetoothSocket.isConnected()){
            connectedThread = new ConnectedThread(bluetoothSocket, handler);
            connectedThread.start();
        }else{
            Log.i("[BLUETOOTH]", "Creating Thread ERROR");
        }
    }

    public void closeTransmission(){
        if(connectedThread != null) {
            connectedThread.cancel();
            connectedThread = null;
        }

    }

    public void send(String s){
        if(connectedThread != null)
            connectedThread.write(s.getBytes());
    }

    public void setCurrentDeviceByIndex(int index) {
        if(index != -1)
            this.currentDevice = pairedDevices.get(index);
        else
            this.currentDevice = null;
    }



    public String getCurrentName(){
        if(currentDevice != null)
            return currentDevice.getName();
        return null;
    }

    public String getCurrentAddress(){
        if(currentDevice != null)
            return currentDevice.getAddress();
        return null;
    }

    public boolean isEnabled(){
        return mBluetoothAdapter.isEnabled();
    }

    public boolean isPair(){
        if(bluetoothSocket != null)
            return bluetoothSocket.isConnected();
        return false;
    }


}
