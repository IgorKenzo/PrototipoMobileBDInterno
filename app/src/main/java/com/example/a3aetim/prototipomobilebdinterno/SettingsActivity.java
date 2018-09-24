package com.example.a3aetim.prototipomobilebdinterno;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.a3aetim.prototipomobilebdinterno.Fragments.MarketFragment;
import com.example.a3aetim.prototipomobilebdinterno.Fragments.SettingsFragment;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        FragmentManager fm = getSupportFragmentManager();
        // Abre uma transação e adiciona
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.FrameSettings, new SettingsFragment());
        ft.commit();
    }
}
