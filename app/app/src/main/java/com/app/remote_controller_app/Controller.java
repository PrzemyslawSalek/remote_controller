package com.app.remote_controller_app;

import com.app.remote_controller_app.Components.Component;

import java.util.LinkedList;
import java.util.List;

public class Controller {
    int id;
    String name;
    List<Component> componentList = new LinkedList<>();

    public void addComponent(Component component){
        componentList.add(component);
    }
    public void removeComponent(Component component){
        componentList.remove(component);
    }
    
    public void modeEdit() {
        for (Component component : componentList)
            component.setMode(Component.Modes.EDIT);
    }
    public void modeUse() {
        for (Component component : componentList)
            component.setMode(Component.Modes.USE);
    }
}