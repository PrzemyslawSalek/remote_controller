package com.app.remote_controller_app;

import com.app.remote_controller_app.components.Button;
import com.app.remote_controller_app.database.SerializedControllers;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SerializedTests {
    @Test
    public void serializeControllerWithoutComponents() {
        Controller c = new Controller("nama", "12345");
        SerializedControllers sc = new SerializedControllers(c);
        assertEquals("{\"name\":\"nama\",\"listOfComponents\":[],\"id\":0,\"favoriteMAC\":\"12345\"}", sc.getSerializedController());
    }

    @Test
    public void deserializeControllerWithoutComponents() {
        Controller c = new Controller("nama", "12345");
        SerializedControllers sc = new SerializedControllers(c);
        Controller c1 = sc.getObject();
        assertEquals(c.toString(), c1.toString());
    }

    @Test
    public void serializeControllerWithComponents() {
        Controller c = new Controller("nama", "12345");
        c.addComponent(new Button(1.2f, 13, 2.2f, 11, false));
        SerializedControllers sc = new SerializedControllers(c);
        System.out.println(sc.getSerializedController());
        System.out.println(c.toString());
        assertEquals("{\"name\":\"nama\",\"listOfComponents\":[{\"type\":\"com.app.remote_controller_app.components.Button\",\"sizeX\":1.2,\"sizeY\":13.0,\"posX\":2.2,\"posY\":11.0,\"isLocal\":false}],\"id\":0,\"favoriteMAC\":\"12345\"}", sc.getSerializedController());
    }
}
