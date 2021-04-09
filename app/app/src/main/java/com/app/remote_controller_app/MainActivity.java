package com.app.remote_controller_app;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    int number_of_clicks = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        setAppLocale(settings.getString("language_key", "en"));

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
            conf.setLocale(new Locale(localeCode.toLowerCase()));
        } else {
            conf.locale = new Locale(localeCode.toLowerCase());
        }
        res.updateConfiguration(conf, dm);

        System.out.println("Język urządzenia: " + Locale.getDefault().getLanguage() +
                " | Język aplikacji: " + (conf.locale).toString());
    }

    // for presentation
    public void testAction(View view) {
        System.out.println("Clicked");
        TextView text = (TextView) findViewById(R.id.textview_first);
        number_of_clicks++;
        //test test
        text.setText("Button clicked! " + number_of_clicks + " times...");
    }

    // Dodanie menu do aktywności
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if ( id == R.id.bluetooth ) {
            Toast.makeText(this, "Bluetooth", Toast.LENGTH_SHORT).show();
            return true;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.settingsFragment) {
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            return NavigationUI.onNavDestinationSelected(item, navController)
                    || super.onOptionsItemSelected(item);

        }

        return super.onOptionsItemSelected(item);
    }


}