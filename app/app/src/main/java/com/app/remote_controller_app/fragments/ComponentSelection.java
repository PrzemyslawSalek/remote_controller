package com.app.remote_controller_app.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.app.remote_controller_app.MainActivity;
import com.app.remote_controller_app.R;
import com.app.remote_controller_app.components.Button;
import com.app.remote_controller_app.components.LED;
import com.app.remote_controller_app.components.TextArea;
import com.app.remote_controller_app.components.view_components.LEDComponent;
import com.app.remote_controller_app.lists.adapters.ComponentListAdapter;
import com.app.remote_controller_app.lists.elements.ComponentListElement;

import java.util.ArrayList;
import java.util.List;


public class ComponentSelection extends Fragment {

    ListView listView;
    ComponentListAdapter adapter;
    List<ComponentListElement> componentsList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createListWithComponents();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_component_selection, container, false);
        listView = (ListView) view.findViewById(R.id.listView_componentSelection);

        adapter = new ComponentListAdapter(getActivity(), R.layout.component_list_item, componentsList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(listenerGetComponent);
        return view;
    }

    AdapterView.OnItemClickListener listenerGetComponent = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String index = ((MainActivity) getActivity()).getNextIndexComponent();
            if(componentsList.get(position).getName().equals(getString(R.string.component_Button))) {
                Button btn = new Button("btn"+index, "id"+index);
                ((MainActivity) getActivity() ).addComponentToCurrentController(btn);
            } else if(componentsList.get(position).getName().equals(getString(R.string.component_TextArea))) {
                TextArea area = new TextArea("txt"+index, "id"+index);
                ((MainActivity) getActivity() ).addComponentToCurrentController(area);
            } else if(componentsList.get(position).getName().equals(getString(R.string.component_LED))) {
                LED led = new LED("led"+index, "id"+index);
                ((MainActivity) getActivity() ).addComponentToCurrentController(led);
            } else if(componentsList.get(position).getName().equals(getString(R.string.component_Slider))) {
                System.out.println(componentsList.get(position).getName()); //Slider
            } else if(componentsList.get(position).getName().equals(getString(R.string.component_TextField))) {
                System.out.println(componentsList.get(position).getName()); //TextField
            }
            getActivity().onBackPressed();
        }
    };

    private void createListWithComponents() {
        componentsList = new ArrayList<>();
        componentsList.add(new ComponentListElement(R.drawable.ic_component_button, getString(R.string.component_Button)));
        componentsList.add(new ComponentListElement(R.drawable.ic_component_textarea, getString(R.string.component_TextArea)));
        componentsList.add(new ComponentListElement(R.drawable.ic_component_led, getString(R.string.component_LED)));
        componentsList.add(new ComponentListElement(R.drawable.ic_component_slider, getString(R.string.component_Slider)));
        componentsList.add(new ComponentListElement(R.drawable.ic_component_textfield, getString(R.string.component_TextField)));
    }
}