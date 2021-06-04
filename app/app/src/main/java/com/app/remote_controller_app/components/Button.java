package com.app.remote_controller_app.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.app.remote_controller_app.MainActivity;
import com.app.remote_controller_app.R;
import com.app.remote_controller_app.fragments.EditMode;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Button extends Component implements OutputComponent {
    String msg;

    @JsonCreator
    public Button(@JsonProperty("name") String name, @JsonProperty("id") String id,
                  @JsonProperty("sizeX") int sizeX, @JsonProperty("sizeY") int sizeY,
                  @JsonProperty("posX") int posX, @JsonProperty("posY") int posY,
                  @JsonProperty("msg") String msg){
        super(name, id, sizeX, sizeY, posX, posY);
        this.msg = msg;
    }

    public Button(String name, String id) {
        super(name, id, 100,100,50,50);
        msg = "hello";
    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View getEditView(Context context, Fragment fragment) {
        android.widget.Button btn = new android.widget.Button(context);
        setAndroidView(btn, context);
        btn.setText(name);
        Button ths = this;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).setCurrentSelectedComponent(ths);
                NavHostFragment.findNavController(fragment).navigate(R.id.action_editMode_to_buttonOptions);
            }
        });

        return btn;
    }

    @Override
    public View getUsageView(Context context) {
        android.widget.Button btn = new android.widget.Button(context);
        setAndroidView(btn, context);
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

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Button{" +
                "msg='" + msg + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", sizeX=" + sizeX +
                ", sizeY=" + sizeY +
                ", posX=" + posX +
                ", posY=" + posY +
                ", isLocal=" +
                ", bluetoothService=" + bluetoothService +
                '}';
    }
}
