package com.app.remote_controller_app.Fragments;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.app.remote_controller_app.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}