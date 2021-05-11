package com.app.remote_controller_app.components;

import android.content.Context;
import android.view.View;

import androidx.fragment.app.Fragment;

import java.util.List;

public class LED extends Component implements InputComponent{

    public LED(String name, String id, float sizeX, float sizeY, float posX, float posY, boolean isLocal) {
        super(name, id, sizeX, sizeY, posX, posY, isLocal);
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
    public void receive(String msg){ }

    public void receive(List<String> data) {
        if(data.get(0).contains("off")){
            off();
        }
    }

    public void off(){
    }
}
