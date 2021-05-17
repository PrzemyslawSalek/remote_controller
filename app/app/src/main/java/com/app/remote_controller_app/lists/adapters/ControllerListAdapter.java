package com.app.remote_controller_app.lists.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.app.remote_controller_app.Controller;
import com.app.remote_controller_app.MainActivity;
import com.app.remote_controller_app.R;

import java.util.List;

public class ControllerListAdapter extends ArrayAdapter<Controller> {

    Context mCtx;
    int resource;
    List<Controller> controllerElements;

    Button button_EditMode;
    Button button_UsageMode;
    ImageButton button_Delete;
    ImageView imageView;
    TextView textViewName;

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

        View view = inflater.inflate(resource, null);

        textViewName = view.findViewById(R.id.textView_ControllerList);
        imageView = view.findViewById(R.id.imageView_ControllerList);
        button_EditMode = (Button) view.findViewById(R.id.button_Controller_EditMode);
        button_UsageMode = (Button) view.findViewById(R.id.button_Controller_UsageMode);
        button_Delete = (ImageButton) view.findViewById(R.id.imageButton_Controller_Delete);

        Controller controllerElement = controllerElements.get(position);

        textViewName.setText(controllerElement.toString());
        imageView.setImageDrawable(mCtx.getResources().getDrawable(R.drawable.ic_controller_opening));

        // Edit Mode //
        view.findViewById(R.id.button_Controller_EditMode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                ((MainActivity) mCtx).setCurrentSelectedController(controllerElement);
                NavController navController = Navigation.findNavController(((MainActivity) mCtx), R.id.nav_host_fragment);
                navController.navigate(R.id.action_opening_to_editMode);
            }
        });

        // Usage Mode //
        view.findViewById(R.id.button_Controller_UsageMode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                if(((MainActivity) mCtx).isPair()) {
                    ((MainActivity) mCtx).setCurrentSelectedController(controllerElement);
                    NavController navController = Navigation.findNavController(((MainActivity) mCtx), R.id.nav_host_fragment);
                    navController.navigate(R.id.action_opening_to_usageMode);
                } else
                    Toast.makeText(getContext(), mCtx.getString(R.string.label_BluetoothDeviceNotSelected), Toast.LENGTH_SHORT).show();
            }
        });

        // Delete //
        view.findViewById(R.id.imageButton_Controller_Delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(mCtx.getString(R.string.label_deleteController));
                builder.setMessage(mCtx.getString(R.string.label_deleteControllerSure));

                builder.setPositiveButton(mCtx.getString(R.string.action_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String deletedController = controllerElement.toString();

                        controllerElements.remove(position);
                        notifyDataSetChanged();
                        ((MainActivity) mCtx).removeController(controllerElement);

                        Toast.makeText(getContext(), mCtx.getString(R.string.label_deleted) + " \"" + deletedController + "\"", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton(mCtx.getString(R.string.action_no), null);
                builder.setIcon(android.R.drawable.ic_dialog_alert);

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        return view;
    }
}
