package com.app.remote_controller_app.components;

import android.content.Context;
import android.view.View;

import com.app.remote_controller_app.BluetoothService;
import com.app.remote_controller_app.ConnectedThread;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type( value = Button.class),
        @JsonSubTypes.Type( value = LED.class)
})
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.PROTECTED_AND_PUBLIC)
public class Component {
    protected float sizeX;
    protected float sizeY;
    protected float posX;
    protected float posY;
    protected boolean isLocal;

    @JsonIgnore
    protected BluetoothService bluetoothService;

    public Component(float sizeX, float sizeY, float posX, float posY, boolean isLocal) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.posX = posX;
        this.posY = posY;
    }

    public View getView(Context context){
        return null;
    }

    public void setBluetoothService(BluetoothService bluetoothService) {
        this.bluetoothService = bluetoothService;
    }
}
