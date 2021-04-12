package com.app.remote_controller_app.components;

import android.content.Context;
import android.view.View;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type( value = Button.class)
})
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public abstract class Component {
    protected float sizeX;
    protected float sizeY;
    protected float posX;
    protected float posY;
    protected boolean isLocal;

    public Component(float sizeX, float sizeY, float posX, float posY, boolean isLocal) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.posX = posX;
        this.posY = posY;
    }

    public abstract View getView(Context context);
}
