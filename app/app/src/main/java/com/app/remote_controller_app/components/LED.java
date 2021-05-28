package com.app.remote_controller_app.components;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.app.remote_controller_app.MainActivity;
import com.app.remote_controller_app.R;
import com.app.remote_controller_app.components.view_components.LEDComponent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class LED extends Component implements InputComponent{

    @JsonIgnore
    LEDComponent ledView;

    @JsonIgnore
    boolean is_on;

    @JsonCreator
    public LED(@JsonProperty("name") String name, @JsonProperty("id") String id,
               @JsonProperty("sizeX") int sizeX, @JsonProperty("sizeY") int sizeY,
               @JsonProperty("posX") int posX, @JsonProperty("posY") int posY){
        super(name, id, sizeX, sizeY, posX, posY);
        is_on = false;
    }

    public LED(String name, String id) {
        super(name, id, 10,10,10,10);
        is_on = false;
    }

    @Override
    public View getEditView(Context context, Fragment fragment) {
        LEDComponent led = new LEDComponent(context);
        setAndroidView(led, context);

        LED ths = this;
        led.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).setCurrentSelectedComponent(ths);
                NavHostFragment.findNavController(fragment).navigate(R.id.action_editMode_to_LEDOptions);
            }
        });
        return led;
    }

    @Override
    public View getUsageView(Context context) {
        this.ledView = new LEDComponent(context);
        ledView.darker(0.3f);

        return ledView;
    }

    @Override
    public void receive(ArrayList<String> data) {
        String method = data.get(0);
        switch (method){
            case "toggle": toggle(); break;
            case "off": off(); break;
            case "on" : on(); break;
            case "color" : if(data.size()>1) change_color(data.get(1)); break;
        }
    }

    private void toggle() {
        if(is_on)
            ledView.darker(0.3f);
        else
            ledView.lighter(0.3f);
        is_on = !is_on;

    }

    private void on() {
        if(!is_on){
            ledView.lighter(0.3f);
            is_on = true;
        }
    }

    public void off(){
        if(is_on){
            ledView.darker(0.3f);
            is_on = false;
        }
    }

    private void change_color(String s) {
        ledView.setLEDColor(s);
    }

}
