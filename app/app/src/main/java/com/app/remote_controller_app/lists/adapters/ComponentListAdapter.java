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

import com.app.remote_controller_app.R;
import com.app.remote_controller_app.lists.elements.ComponentListElement;

import java.util.List;

public class ComponentListAdapter extends ArrayAdapter<ComponentListElement> {

    Context mCtx;
    int resource;
    List<ComponentListElement> componentElements;

    public ComponentListAdapter(Context mCtx, int resource, List<ComponentListElement> componentElements) {
        super(mCtx, resource, componentElements);
        this.mCtx = mCtx;
        this.resource = resource;
        this.componentElements = componentElements;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        View  view = inflater.inflate(resource, null);

        TextView textViewName = view.findViewById(R.id.textView_ComponentList);
        ImageView imageView = view.findViewById(R.id.imageView_ComponentList);

        ComponentListElement componentListElement = componentElements.get(position);

        textViewName.setText(componentListElement.getName());
        imageView.setImageDrawable(mCtx.getResources().getDrawable(componentListElement.getImage()));

        return view;
    }
}
