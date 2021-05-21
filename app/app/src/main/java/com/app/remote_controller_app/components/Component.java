package com.app.remote_controller_app.components;

import android.content.Context;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.app.remote_controller_app.BluetoothService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type( value = Button.class),
        @JsonSubTypes.Type( value = LED.class)
})
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.PROTECTED_AND_PUBLIC)
public abstract class Component{
    protected String name;
    protected String id;
    protected float sizeX;
    protected float sizeY;
    protected float posX;
    protected float posY;

    @JsonIgnore
    protected BluetoothService bluetoothService;

    public Component(String name, String id, float sizeX, float sizeY, float posX, float posY) {
        this.name = name;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.posX = posX;
        this.posY = posY;
        this.id = id;
    }

    public abstract View getEditView(Context context, Fragment fragment);
    public abstract View getUsageView(Context context);

    public void setBluetoothService(BluetoothService bluetoothService) {
        this.bluetoothService = bluetoothService;
    }

    public void resize(float sizeX, float sizeY){
        this.sizeX=sizeX;
        this.sizeY=sizeY;
    }

    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setId(String id) {
        this.id = id;
    }

    public float getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public float getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }
}
