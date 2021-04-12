package com.app.remote_controller_app;

import com.app.remote_controller_app.components.Component;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Controller {
    private String name;
    private String favoriteMAC;
    private List<Component> listOfComponents;

    @JsonCreator
    public Controller(@JsonProperty("name") String name, @JsonProperty("favoriteDeviceMAC") String favoriteMAC, @JsonProperty("listOfComponents") List<Component> listOfComponents) {
        this.name = name;
        this.favoriteMAC = favoriteMAC;
        this.listOfComponents = listOfComponents;
    }

    public void addComponent(Component c){
        listOfComponents.add(c);
    }

    public void removeComponent(Component c){
        listOfComponents.remove(c);
    }
}
