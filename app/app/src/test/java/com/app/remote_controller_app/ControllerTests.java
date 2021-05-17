package com.app.remote_controller_app;
import com.app.remote_controller_app.components.Button;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ControllerTests {
    @Test
    public void createController(){
        Controller c = new Controller("name1", "12345");
        //c.addComponent(new Button(1,5,1.5f,1.8f,false));
        assertEquals("Controller{name='name1', favoriteMAC='12345', listOfComponents=[Button{sizeX=1.0, sizeY=5.0, posX=1.5, posY=1.8, isLocal=false}]}", c.toString());
    }


    @Test
    public void controllerCheckId(){
        Controller c = new Controller("name1", "12345");
        //c.addComponent(new Button(1,5,1.5f,1.8f,false));
        //c.addComponent(new Button(5,15,6.5f,24,false));
        assertEquals(0,c.getId());
        c.setId(1);
        assertEquals(1,c.getId());
    }
}
