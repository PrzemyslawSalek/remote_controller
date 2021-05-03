package com.app.remote_controller_app.components;

import android.content.Context;
import android.view.View;

import androidx.fragment.app.Fragment;

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
    public void receive(String msg) {
        if(msg.contains("off")){
            off();
        }
    }

    public void off(){
    }
}
