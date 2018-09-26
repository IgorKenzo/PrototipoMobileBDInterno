package com.example.a3aetim.prototipomobilebdinterno;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.a3aetim.prototipomobilebdinterno.Fragments.MarketFragment;
import com.example.a3aetim.prototipomobilebdinterno.Fragments.SettingsFragment;

import java.util.Locale;

import es.dmoral.toasty.Toasty;

import static com.example.a3aetim.prototipomobilebdinterno.Splash.PREF_NAME;

public class SettingsActivity extends AppCompatActivity {
    public static final String KEY_NOT = "notification_chk";
    public static final String KEY_SOUND = "sound_chk";
    public static final String KEY_VIBRATION = "vibration_chk";
    public static final String KEY_LANGUAGE = "selec_lang";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_settings);
        if(savedInstanceState == null) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.FrameSettings, new SettingsFragment());
            ft.commit();
        }
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String idiomPref = sharedPref.getString(SettingsActivity.KEY_LANGUAGE, "-1");
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
    public void setLocale(String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor = getSharedPreferences(PREF_NAME,0).edit();
        editor.putString(KEY_LANGUAGE,lang);
        editor.commit();
    }
    public void loadLocale(){
        SharedPreferences sp = getSharedPreferences(PREF_NAME,0);
        String language = sp.getString(KEY_LANGUAGE,"");
        setLocale(language);
    }
}
