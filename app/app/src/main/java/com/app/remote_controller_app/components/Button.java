package com.app.remote_controller_app.components;

import android.content.Context;
import android.view.View;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Button extends Component{

    public Button(@JsonProperty("sizeX")float sizeX, @JsonProperty("sizeY")float sizeY, @JsonProperty("posX")float posX, @JsonProperty("posY")float posY, @JsonProperty("isLocal")boolean isLocal) {
        super(sizeX, sizeY, posX, posY, isLocal);
    }

    @Override
    public View getView(Context context) {
        android.widget.Button btn = new android.widget.Button(context);
        btn.setX(posX);
        btn.setY(posY);

        return btn;
    }
}
