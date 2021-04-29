package com.app.remote_controller_app;

import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class ConnectedThread extends Thread{
    private final BluetoothSocket mmSocket;
    private  InputStream mmInStream;
    private  OutputStream mmOutStream;

    public static final int RESPONSE_MESSAGE = 10;
    Handler handler;

    public ConnectedThread(BluetoothSocket socket, Handler uih){
        mmSocket = socket;
        this.handler = uih;
        
        Log.i("[THREAD-CT]","Creating thread");
        try{
            mmInStream = socket.getInputStream();
            mmOutStream = socket.getOutputStream();
            mmOutStream.flush();
        } catch(IOException e) {
            Log.e("[THREAD-CT]","Creating tread error");
        }
        Log.i("[THREAD-CT]","IO's obtained");
    }

    public void run(){
        BufferedReader br = new BufferedReader(new InputStreamReader(mmInStream));
        Log.i("[THREAD-CT]","Starting thread");
        while(true){
            try{
                String resp = br.readLine();
                Message msg = new Message();
                msg.what = RESPONSE_MESSAGE;
                msg.obj = resp;
                handler.sendMessage(msg);
            }catch(IOException e){
                break;
            }
        }
        Log.i("[THREAD-CT]","While loop ended");
    }

    public void write(byte[] bytes){
        try{
            mmOutStream.write(bytes);
            Log.i("[THREAD-CT]", "Writting bytes");
        }catch(IOException e){
            Log.i("[THREAD-CT]", "Writting bytes error");
        }
    }

    public void cancel(){
        try {
            mmSocket.close();
            Log.i("[THREAD-CT]", "Thread close");
        } catch (IOException e) {
            Log.i("[THREAD-CT]", "Thread close error");
        }
    }
}
