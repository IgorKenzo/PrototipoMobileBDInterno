package com.example.a3aetim.prototipomobilebdinterno;

import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.view.MenuItem;

import com.example.a3aetim.prototipomobilebdinterno.Fragments.MarketFragment;
import com.example.a3aetim.prototipomobilebdinterno.Fragments.SettingsFragment;

public class SettingsActivity extends AppCompatActivity {
    public static final String KEY_NOT = "notification_chk";
    public static final String KEY_SOUND = "sound_chk";
    public static final String KEY_VIBRATION = "vibration_chk";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.FrameSettings, new SettingsFragment());
        ft.commit();


        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String idiomPref = sharedPref.getString("select_idioma", "-1");

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean switchPref = sp.getBoolean(SettingsActivity.KEY_NOT, false);
        Boolean switchPref1 = sp.getBoolean(SettingsActivity.KEY_SOUND, false);
        Boolean switchPref2 = sp.getBoolean(SettingsActivity.KEY_VIBRATION, false);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
