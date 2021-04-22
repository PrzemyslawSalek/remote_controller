package com.app.remote_controller_app.components;

import android.content.Context;
import android.view.View;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Button extends Component implements OutputComponent{
    String msg = "dupa";

    android.widget.Button btn;

    @JsonCreator
    public Button(@JsonProperty("sizeX")float sizeX, @JsonProperty("sizeY")float sizeY, @JsonProperty("posX")float posX, @JsonProperty("posY")float posY, @JsonProperty("isLocal")boolean isLocal) {
        super(sizeX, sizeY, posX, posY, isLocal);
    }

    @Override
    public View getView(Context context) {
        android.widget.Button btn = new android.widget.Button(context);
        btn.setX(posX);
        btn.setY(posY);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });

        this.btn = btn;
        return btn;
    }

    @Override
    public void send() {
        ct.write(msg.getBytes());
    }

    @Override
    public String toString() {
        return "Button{" +
                "sizeX=" + sizeX +
                ", sizeY=" + sizeY +
                ", posX=" + posX +
                ", posY=" + posY +
                ", isLocal=" + isLocal +
                '}';
    }

}
