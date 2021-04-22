package com.app.remote_controller_app.components;

import android.content.Context;
import android.view.View;

public class LED extends Component implements InputComponent{

    public LED(float sizeX, float sizeY, float posX, float posY, boolean isLocal) {
        super(sizeX, sizeY, posX, posY, isLocal);
    }

    @Override
    public View getView(Context context) {
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
