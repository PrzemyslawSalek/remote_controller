package com.app.remote_controller_app.components;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Button extends Component implements OutputComponent {
    String msg = "to działa xD";


    @JsonCreator
    public Button(@JsonProperty("name") String name, @JsonProperty("sizeX") float sizeX, @JsonProperty("sizeY") float sizeY, @JsonProperty("posX") float posX, @JsonProperty("posY") float posY, @JsonProperty("isLocal") boolean isLocal) {
        super(name, sizeX, sizeY, posX, posY, isLocal);
    }

    @Override
    public View getEditView(Context context) {
        android.widget.Button btn = new android.widget.Button(context);
        btn.setText(name);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("[Component]", "editMode");
            }
        });
        return btn;
    }

    @Override
    public View getUsageView(Context context) {
        android.widget.Button btn = new android.widget.Button(context);
//        btn.setX(posX);
//        btn.setY(posY);
        Log.v("[Component]", name);
        btn.setText(name);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });

        return btn;
    }

    @Override
    public void send() {
        bluetoothService.send(msg);
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
