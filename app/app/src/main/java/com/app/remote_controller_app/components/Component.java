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
public abstract class Component {
    protected String name;
    protected float sizeX;
    protected float sizeY;
    protected float posX;
    protected float posY;
    protected boolean isLocal;

    @JsonIgnore
    protected BluetoothService bluetoothService;

    public Component(String name, float sizeX, float sizeY, float posX, float posY, boolean isLocal) {
        this.name = name;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.posX = posX;
        this.posY = posY;
    }

    public abstract View getEditView(Context context);
    public abstract View getUsageView(Context context);

    public void setBluetoothService(BluetoothService bluetoothService) {
        this.bluetoothService = bluetoothService;
    }
}
