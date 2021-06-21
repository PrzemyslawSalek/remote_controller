package com.app.remote_controller_app.components;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.app.remote_controller_app.MainActivity;
import com.app.remote_controller_app.R;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TextArea extends Component implements InputComponent{
    private String logger;

    @JsonIgnore
    android.widget.TextView textView;

    @JsonCreator
    public TextArea(@JsonProperty("name") String name, @JsonProperty("id") String id,
                    @JsonProperty("sizeX") int sizeX, @JsonProperty("sizeY") int sizeY,
                    @JsonProperty("posX") int posX, @JsonProperty("posY") int posY, @JsonProperty("layer") float layer,
                    @JsonProperty("logger") String logger){
        super(name, id, sizeX, sizeY, posX, posY, layer);
        this.logger = logger;
    }

    public TextArea(String name, String id) {
        super(name, id, 100,200,0,0, 0);
    }

    @Override
    public View getEditView(Context context, Fragment fragment) {
        android.widget.TextView textView = new android.widget.TextView(context);
        ScrollView sc = new ScrollView(context);

        setAndroidView(textView, context);
        setMove(textView, context);

        if(logger!=null)
            textView.setText("logger active");
        else
            textView.setText("niektywny dupa");
        
        textView.setBackgroundColor(Color.GRAY);

        TextArea ths = this;
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).setCurrentSelectedComponent(ths);
                NavHostFragment.findNavController(fragment).navigate(R.id.action_editMode_to_textAreaOptions);
            }
        });
        return textView;
    }

    @Override
    public View getUsageView(Context context) {
        this.textView = new android.widget.TextView(context);
        ScrollView sc  = new ScrollView(context);
        sc.addView(textView);
        setAndroidView(sc, context);
        sc.setBackgroundColor(Color.GRAY);

        textView.setText(name);
        textView.setBackgroundColor(Color.GRAY);

        return sc;
    }

    @Override
    public void receive(ArrayList<String> data) {
        String method = data.get(0);
        switch (method){
            case "clear": clear(); break;
            case "write": if(data.size()>1) write(data.get(1)); break;
            case "append" : if(data.size()>1) append(data.get(1)); break;
        }
    }

    public void clear(){
        textView.setText("");
    }

    public void write(String s){
        textView.setText(s);
    }

    public void append(String s){
        textView.append(s);
    }
}
