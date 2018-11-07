package com.example.a3aetim.Myndie;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.a3aetim.Myndie.Classes.Application;

public class ApplicationActivity extends AppCompatActivity {
    TextView mTitle,mPrice,mVersion,mDesc, mPublisherName, mReleaseDate;
    Application app;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
        app  =(Application) getIntent().getSerializableExtra("App");
        mPrice =(TextView)findViewById(R.id.txtPriceApp);
        mTitle =(TextView)findViewById(R.id.txtTitleApp);
        mVersion =(TextView)findViewById(R.id.txtVersionApp);
        mDesc =(TextView)findViewById(R.id.txtDescApp);
        mPublisherName = (TextView)findViewById(R.id.txtPublisherNameApp);
        mReleaseDate = (TextView)findViewById(R.id.txtReleaseDateApp);
        mTitle.setText(app.getTitle());
        mVersion.setText(app.getVersion());
        mPublisherName.setText(app.getPublisherName());
        mReleaseDate.setText(String.valueOf(app.getReleaseDate()));
        mPrice.setText("R$ "+String.valueOf(app.getPrice()));
        mDesc.setText("Aqui irá uma breve descrição do aplicativo"+String.valueOf(app.get_IdApp()));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.applicationmenu, menu);
        return true;
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
