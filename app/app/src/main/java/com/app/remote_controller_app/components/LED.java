package com.app.remote_controller_app.components;

import android.content.Context;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class LED extends Component implements InputComponent{

    @JsonCreator
    public LED(@JsonProperty("name") String name, @JsonProperty("id") String id,
               @JsonProperty("sizeX") float sizeX, @JsonProperty("sizeY") float sizeY,
               @JsonProperty("posX") float posX, @JsonProperty("posY") float posY){
        super(name, id, sizeX, sizeY, posX, posY);
    }

    public LED(String name, String id) {
        super(name, id, 0,0,0,0);
    }

    @Override
    public View getEditView(Context context, Fragment fragment) {
        return null;
    }

    @Override
    public View getUsageView(Context context) {
        return null;
    }

    @Override
    public void receive(ArrayList<String> data) {
    }

    public void off(){
    }
}
