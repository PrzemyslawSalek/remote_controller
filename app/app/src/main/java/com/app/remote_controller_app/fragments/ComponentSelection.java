package com.app.remote_controller_app.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.app.remote_controller_app.MainActivity;
import com.app.remote_controller_app.R;
import com.app.remote_controller_app.components.Button;

import java.util.ArrayList;
import java.util.Arrays;


public class ComponentSelection extends Fragment {

    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> componentsList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        componentsList = new ArrayList<String>(Arrays.asList(getString(R.string.component_Button), getString(R.string.component_TextArea)));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_component_selection, container, false);
        listView = (ListView) view.findViewById(R.id.listView_componentSelection);

        arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, componentsList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(listenerGetComponent);
        return view;
    }

    AdapterView.OnItemClickListener listenerGetComponent = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(componentsList.get(position).equals(getString(R.string.component_Button))) {
                Button btn = new Button();
                ((MainActivity) getActivity() ).addComponentToCurrentController(btn);
            } else if(componentsList.get(position).equals(getString(R.string.component_TextArea))) {
                System.out.println(componentsList.get(position)); //TextArea
            }
            getActivity().onBackPressed();
        }
    };
}