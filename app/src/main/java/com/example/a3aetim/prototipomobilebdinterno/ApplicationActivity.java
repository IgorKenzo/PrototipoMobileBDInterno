package com.example.a3aetim.prototipomobilebdinterno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.widget.TextView;

public class ApplicationActivity extends AppCompatActivity {
    TextView mTitle,mPrice,mVersion,mDesc;
    Application app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*Explode explodeTransit = new Explode();
        explodeTransit.setDuration(1000);
        Fade fadeTransit = new Fade();
        fadeTransit.setDuration(1000);
        getWindow().setReturnTransition(explodeTransit);
        getWindow().setEnterTransition(fadeTransit);*/
        //////////////////////
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
        app  =(Application) getIntent().getSerializableExtra("App");
        mPrice =(TextView)findViewById(R.id.txtPriceApp);
        mTitle =(TextView)findViewById(R.id.txtTitleApp);
        mVersion =(TextView)findViewById(R.id.txtVersionApp);
        mDesc =(TextView)findViewById(R.id.txtDescApp);
        mTitle.setText(app.getTitle());
        mVersion.setText(app.getVersion());
        mPrice.setText("R$ "+String.valueOf(app.getPrice()));
        mDesc.setText("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff"+String.valueOf(app.get_IdApp()));
    }

}
