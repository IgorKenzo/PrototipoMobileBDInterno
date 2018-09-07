package com.example.a3aetim.prototipomobilebdinterno;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CadastroCli extends Activity implements AdapterView.OnItemSelectedListener {
    EditText txtName, txtEmail,txtUser, txtPass, txtBirth;
    Spinner country,lang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cli);
        country = (Spinner)findViewById(R.id.spnCountry);
        lang = (Spinner)findViewById(R.id.spnLang);
        country.setOnItemSelectedListener(this);
        lang.setOnItemSelectedListener(this);

        List<String> paises = new ArrayList<>(Arrays.asList("Brazil","United States","Canada"));

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, paises );
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country.setAdapter(dataAdapter);

        List<String> linguagens = new ArrayList<>(Arrays.asList("Portuguese","English"));

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, linguagens );
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lang.setAdapter(dataAdapter2);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void Cad(View view){
        Toast.makeText(this, "Cad", Toast.LENGTH_SHORT).show();
    }
}
