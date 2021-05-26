package com.app.remote_controller_app.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.app.remote_controller_app.MainActivity;
import com.app.remote_controller_app.R;

public class SeekBar extends Component implements OutputComponent {

    int progressValue;
    int maximumRange;
    int defaultValue;

    String msg;

    public SeekBar(String name, String id, int sizeX, int sizeY, int posX, int posY) {
        super(name, id, sizeX, sizeY, posX, posY);
        //TODO set range and default value (which user begins)
    }

    // SuppressLint for avoid warnings
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View getEditView(Context context, Fragment fragment) {
        android.widget.SeekBar seekBar = new android.widget.SeekBar(context);

        seekBar.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //TODO navigation to proper component option fragment
                return false;
            }
        });

        return seekBar;
    }

    @Override
    public View getUsageView(Context context) {
        android.widget.SeekBar seekBar = new android.widget.SeekBar(context);

        seekBar.setOnSeekBarChangeListener(new android.widget.SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(android.widget.SeekBar seekBar, int progress, boolean fromUser) {
                progressValue = progress;
                msg = String.valueOf(progressValue);

            }

            @Override
            public void onStartTrackingTouch(android.widget.SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(android.widget.SeekBar seekBar) {
                Toast.makeText(context, "value: " + progressValue, Toast.LENGTH_SHORT).show();
            }
        });
        return seekBar;
    }

    @Override
    public void send() {
        bluetoothService.send(msg);
    }

    @Override
    public String toString() {
        return "SeekBar{" +
                "progress value='" + progressValue + '\'' +
                "maximumRange='" + maximumRange + '\'' +
                "defaultValue='" + defaultValue + '\'' +
                "msg='" + msg + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", sizeX=" + sizeX +
                ", sizeY=" + sizeY +
                ", posX=" + posX +
                ", posY=" + posY +
                ", isLocal=" +
                ", bluetoothService=" + bluetoothService +
                '}';
    }
}
