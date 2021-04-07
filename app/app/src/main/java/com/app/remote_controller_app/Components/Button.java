package com.app.remote_controller_app.Components;


public class Button extends Component{
    Button(){
        this.type = Components.BUTTON;
        this.mode = Modes.EDIT;
    }

    //TODO Test exceptions
    public void setPositionX(float value) throws IllegalAccessException {
        if(this.mode == Modes.USE)
            throw new IllegalAccessException("Can't do this in USAGE mode!");

        this.positionX = value;
    }
    public void setPositionY(float value) throws IllegalAccessException {
        if(this.mode == Modes.USE)
            throw new IllegalAccessException("Can't do this in USAGE mode!");

        this.positionY = value;
    }
    public void setWidth(float value) throws IllegalAccessException {
        if(this.mode == Modes.USE)
            throw new IllegalAccessException("Can't do this in USAGE mode!");

        this.width = value;
    }
    public void setHeight(float value) throws IllegalAccessException {
        if(this.mode == Modes.USE)
            throw new IllegalAccessException("Can't do this in USAGE mode!");

        this.height = value;
    }

}
