package com.app.remote_controller_app;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import android.preference.PreferenceManager;

import com.app.remote_controller_app.database.DatabaseHelper;
import com.app.remote_controller_app.database.SerializedControllers;
import com.j256.ormlite.dao.Dao;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Inicjalizacja obiektów*/
        //db = new DatabaseHelper(this);

        /* Wybranie i ustawienie odpowiedniego języka aplikacji */
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        setAppLocale(settings.getString("language_key", "device"));

        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /* Metoda odpowiedzialna za zmianę języka w aplikacji */
    private void setAppLocale(String localeCode) {
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if(localeCode.equals("device"))
                conf.setLocale(new Locale(Locale.getDefault().getLanguage()));
            else
                conf.setLocale(new Locale(localeCode.toLowerCase()));
        } else {
            if(localeCode.equals("device"))
                conf.locale = new Locale(Locale.getDefault().getLanguage());
            else
                conf.locale = new Locale(localeCode.toLowerCase());
        }
        res.updateConfiguration(conf, dm);

        System.out.println("Język urządzenia: " + Locale.getDefault().getLanguage() + " | Język aplikacji: " + (conf.locale).toString());
    }


    /* -------------- Menu ------------------ */

    /* Dodawanie elementów do menu (Action Bar) */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /* Akcje wykonywane po wybraniu odpowiedniego elementu z menu */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bluetooth:
                Toast.makeText(this, "Bluetooth", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.settingsFragment:
                NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
                return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    /* Gdy menu jest otwarte... Wyświetla ikony obok elementów z listy */
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if((featureId & Window.FEATURE_ACTION_BAR) == Window.FEATURE_ACTION_BAR && menu != null){
            if(menu.getClass().getSimpleName().equals("MenuBuilder")){
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch(NoSuchMethodException e){
                    //Log.e(TAG, "onMenuOpened", e);
                } catch(Exception e){
                    throw new RuntimeException(e);
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    public Controller addController(String name){
        try{
            Dao d = db.getSerializedControllerDao();
            SerializedControllers sc=new SerializedControllers(new Controller(name, ""));
            d.create(sc);
            sc.getObject().setId(sc.getId());
            d.update(sc);
            return sc.getObject();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateController(Controller c){
        try {
            Dao d = db.getSerializedControllerDao();
            SerializedControllers SController= (SerializedControllers) d.queryForId(c.getId());
            SController.setSerializedController(c);
            d.update(SController);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeController(Controller c){
        try {
            Dao d = db.getSerializedControllerDao();
            d.deleteById(c.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Controller> listOfController(){
        try {
            Dao d = db.getSerializedControllerDao();
            List<SerializedControllers> listS=d.queryForAll();
            List<Controller> listC = new ArrayList<>();
            for(int i=0; i<listS.size(); i++){
                listC.add(listS.get(i).getObject());
                listC.get(i).setId(listS.get(i).getId());
            }
            return listC;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}