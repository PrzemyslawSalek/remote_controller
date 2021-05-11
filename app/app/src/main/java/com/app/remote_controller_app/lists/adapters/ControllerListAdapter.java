package com.app.remote_controller_app.lists.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.app.remote_controller_app.Controller;
import com.app.remote_controller_app.R;

import java.util.List;

public class ControllerListAdapter extends ArrayAdapter<Controller> {

    Context mCtx;
    int resource;
    List<Controller> controllerElements;

    public ControllerListAdapter(Context mCtx, int resource, List<Controller> controllerElements) {
        super(mCtx, resource, controllerElements);
        this.mCtx = mCtx;
        this.resource = resource;
        this.controllerElements = controllerElements;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        View  view = inflater.inflate(resource, null);

        TextView textViewName = view.findViewById(R.id.textView_ControllerList);
        ImageView imageView = view.findViewById(R.id.imageView_ControllerList);

        Controller controllerElement = controllerElements.get(position);

        textViewName.setText(controllerElement.toString());
        imageView.setImageDrawable(mCtx.getResources().getDrawable(R.drawable.ic_controller_opening));

        return view;
    }

}
