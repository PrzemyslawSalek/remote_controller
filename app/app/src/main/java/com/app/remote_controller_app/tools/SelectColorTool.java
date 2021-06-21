package com.app.remote_controller_app.tools;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.app.remote_controller_app.R;

public class SelectColorTool {
    View view;
    Context context;
    SeekBar redSlider;
    SeekBar greenSlider;
    SeekBar blueSlider;

    TextView mainColorText;
    TextView redText;
    TextView blueText;
    TextView greenText;

    View colorView;

    public SelectColorTool(Activity context, int color) {
        view = context.getLayoutInflater().inflate(R.layout.select_color, null);

        redSlider = view.findViewById(R.id.redSlider);
        redSlider.setProgress(Color.red(color));

        greenSlider = view.findViewById(R.id.greenSlider);
        greenSlider.setProgress(Color.green(color));

        blueSlider = view.findViewById(R.id.blueSlider);
        blueSlider.setProgress(Color.blue(color));


        mainColorText = view.findViewById(R.id.mainColor);
        mainColorText.setText(Integer.toHexString(color));

        redText = view.findViewById(R.id.redText);
        redText.setText(String.valueOf(Color.red(color)));

        greenText = view.findViewById(R.id.greenText);
        greenText.setText(String.valueOf(Color.green(color)));

        blueText = view.findViewById(R.id.blueText);
        blueText.setText(String.valueOf(Color.blue(color)));

        colorView = view.findViewById(R.id.colorView);
        colorView.setBackgroundColor(Color.rgb(Integer.parseInt(redText.getText().toString()), Integer.parseInt(greenText.getText().toString()), Integer.parseInt(blueText.getText().toString())));

        addListeners();
        this.context = context;
    }

    private void addListeners() {
        redSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                redText.setText(String.valueOf(progress));
                int main_color = Color.rgb(progress, Integer.parseInt(greenText.getText().toString()), Integer.parseInt(blueText.getText().toString()));
                mainColorText.setText(Integer.toHexString(main_color));
                colorView.setBackgroundColor(main_color);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        greenSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                greenText.setText(String.valueOf(progress));
                int main_color = Color.rgb(Integer.parseInt(redText.getText().toString()), progress, Integer.parseInt(blueText.getText().toString()));
                mainColorText.setText(Integer.toHexString(main_color));
                colorView.setBackgroundColor(main_color);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        blueSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                blueText.setText(String.valueOf(progress));
                int main_color = Color.rgb(Integer.parseInt(redText.getText().toString()), Integer.parseInt(greenText.getText().toString()), progress);
                mainColorText.setText(Integer.toHexString(main_color));
                colorView.setBackgroundColor(main_color);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public View getView() {
        return view;
    }

    public String getColor(){
        return mainColorText.getText().toString();
    }

    public void show(DialogInterface.OnClickListener positiveButton){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        if(positiveButton!=null){
            builder.setPositiveButton(R.string.action_ok, positiveButton);
        }
        builder.setNegativeButton(R.string.action_cancel,null);

        AlertDialog alert = builder.create();
        alert.show();
    }
}
