package com.app.remote_controller_app.components;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.app.remote_controller_app.MainActivity;
import com.app.remote_controller_app.R;
import com.app.remote_controller_app.fragments.component_options.TextField;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FieldArea extends Component implements OutputComponent{

    @JsonIgnore
    EditText editText;

    @JsonCreator
    public FieldArea(@JsonProperty("name") String name, @JsonProperty("id") String id,
                  @JsonProperty("sizeX") int sizeX, @JsonProperty("sizeY") int sizeY,
                  @JsonProperty("posX") int posX, @JsonProperty("posY") int posY){
        super(name, id, sizeX, sizeY, posX, posY);
    }

    public FieldArea(String name, String id) {
        super(name, id, 400,64,50,50);
    }

    @Override
    public View getEditView(Context context, Fragment fragment) {
        View view = ((MainActivity) context).getLayoutInflater().inflate(R.layout.text_field_component, null);
        ConstraintLayout layout = (ConstraintLayout) view.findViewById(R.id.textFieldLayout);
        setAndroidView(layout, context);


        FieldArea ths = this;
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).setCurrentSelectedComponent(ths);
                NavHostFragment.findNavController(fragment).navigate(R.id.action_editMode_to_textField);
            }
        });

        return layout;
    }

    @Override
    public View getUsageView(Context context) {
        View view = ((MainActivity) context).getLayoutInflater().inflate(R.layout.text_field_component, null);
        ConstraintLayout layout = (ConstraintLayout) view.findViewById(R.id.textFieldLayout);
        setAndroidView(layout, context);

        editText = (EditText) view.findViewById(R.id.textFieldEditText);
        editText.setClickable(true);
        editText.setEnabled(true);

        android.widget.Button btn = (android.widget.Button) view.findViewById(R.id.textFieldButton);
        btn.setEnabled(true);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });

        setAndroidView(layout, context);


        return view;
    }

    @Override
    public void send() {
        bluetoothService.send(editText.getText().toString());
        editText.setText("");
    }
}
