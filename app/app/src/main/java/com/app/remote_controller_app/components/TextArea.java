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
    private String loggerText;
    private boolean loggerEnabled;

    @JsonIgnore
    android.widget.TextView textView;

    @JsonCreator
    public TextArea(@JsonProperty("name") String name, @JsonProperty("id") String id,
                    @JsonProperty("sizeX") int sizeX, @JsonProperty("sizeY") int sizeY,
                    @JsonProperty("posX") int posX, @JsonProperty("posY") int posY, @JsonProperty("layer") float layer,
                    @JsonProperty("logger") String loggerText, @JsonProperty("loggerEnabled") boolean loggerEnabled){
        super(name, id, sizeX, sizeY, posX, posY, layer);
        this.loggerText = loggerText;
        this.loggerEnabled = loggerEnabled;
    }

    public TextArea(String name, String id) {
        super(name, id, 100,200,0,0, 0);
        this.loggerText = "";
        this.loggerEnabled = false;
    }

    @Override
    public View getEditView(Context context, Fragment fragment) {
        android.widget.TextView textView = new android.widget.TextView(context);
        ScrollView sc = new ScrollView(context);

        setAndroidView(textView, context);
        setMove(textView, context);

        if(loggerEnabled)
            textView.setText(R.string.loggerActive);
        else
            textView.setText(R.string.loggerInactive);

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

        if(loggerEnabled)
            textView.setText(loggerText);
        textView.setBackgroundColor(Color.GRAY);

        return sc;
    }

    @Override
    public void receive(Context context, ArrayList<String> data) {
        String method = data.get(0);
        switch (method){
            case "clear": clear(); break;
            case "write": if(data.size()>1) write(data.get(1)); break;
            case "append" : if(data.size()>1) append(data.get(1)); break;
        }

        loggerText = textView.getText().toString();
        ((MainActivity) context).updateCurrentSelectedController();
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

    public String getLoggerText() {
        return loggerText;
    }

    public void setLoggerText(String loggerText) {
        this.loggerText = loggerText;
    }

    public boolean isLoggerEnabled() {
        return loggerEnabled;
    }

    public void setLoggerEnabled(boolean loggerEnabled) {
        this.loggerEnabled = loggerEnabled;
    }
}


