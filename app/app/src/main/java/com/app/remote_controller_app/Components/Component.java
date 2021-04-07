package com.app.remote_controller_app.Components;

import com.app.remote_controller_app.Controller;

public class Component {

    public enum Components {
        BUTTON
    }
    public enum Modes {
        USE, EDIT
    }

    int id;
    String name;
    Components type;
    float positionX;
    float positionY;
    float width;
    float height;
    Modes mode;
    //TODO implement deleting components from controller
    Controller controller;

    public void setMode(Modes value) {
        this.mode = value;
    }
    public Modes getMode() {
        return this.mode;
    }

    public float getPositionX() {
        return this.positionX;
    }
    public float getPositionY() {
        return this.positionY;
    }
    public float getWidth() {
        return this.width;
    }
    public float getHeight() {
        return this.height;
    }
}
