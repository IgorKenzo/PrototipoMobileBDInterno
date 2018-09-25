package com.example.a3aetim.prototipomobilebdinterno.Fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.Settings;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a3aetim.prototipomobilebdinterno.R;
import com.example.a3aetim.prototipomobilebdinterno.SettingsActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        setPreferencesFromResource(R.xml.fragment_settings, s);
    }
}
