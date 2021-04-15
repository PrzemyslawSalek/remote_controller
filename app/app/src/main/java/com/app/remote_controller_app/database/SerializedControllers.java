package com.app.remote_controller_app.database;

import com.app.remote_controller_app.Controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class SerializedControllers {
    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private String serializedController;

    public SerializedControllers() {
    }

    public SerializedControllers(Controller c) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            serializedController = mapper.writeValueAsString(c);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public Controller getObject() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(serializedController, Controller.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setSerializedController(Controller c) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            serializedController = mapper.writeValueAsString(c);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void setSerializedController(String serializedController) {
        this.serializedController = serializedController;
    }

    public String getSerializedController() {
        return serializedController;
    }

    public long getId() {
        return id;
    }
}
