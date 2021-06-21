package com.app.remote_controller_app;

import android.content.Context;
import android.util.Log;

import com.app.remote_controller_app.components.Component;
import com.app.remote_controller_app.components.InputComponent;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Controller {
    private long id;
    private String name;
    private String favoriteMAC;
    private List<Component> listOfComponents;

    @JsonCreator
    public Controller(@JsonProperty("name") String name,
                      @JsonProperty("favoriteDeviceMAC") String favoriteMAC,
                      @JsonProperty("listOfComponents") List<Component> listOfComponents) {
        this.name = name;
        this.favoriteMAC = favoriteMAC;
        this.listOfComponents = listOfComponents;
    }

    public Controller(String name, String favoriteMAC) {
        this.listOfComponents = new LinkedList<Component>();
        this.name = name;
        this.favoriteMAC = favoriteMAC;
    }

    public void msgToCommand(Context context, String id, ArrayList<String> data){
        for(Component c : listOfComponents){
            if(c.getId().equals(id) && c instanceof InputComponent){
                ((InputComponent) c).receive(context, data);
                break; //ale czy napewno
            }
        }
    }

    public void addComponent(Component c){
        listOfComponents.add(c);
    }

    public void removeComponent(Component c){
        listOfComponents.remove(c);
    }

    public List<Component> getListOfComponents() {
        return listOfComponents;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFavoriteMAC() {
        return favoriteMAC;
    }

    public void setFavoriteMAC(String favoriteMAC) {
        this.favoriteMAC = favoriteMAC;
    }

    @Override
    public String toString() {
        return name;
    }
}
