package com.app.remote_controller_app.components;

import android.content.Context;

import java.util.ArrayList;

public interface InputComponent {
    void receive(Context context, ArrayList<String> data);
}
