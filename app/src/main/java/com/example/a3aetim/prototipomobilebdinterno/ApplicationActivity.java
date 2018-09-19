package com.example.a3aetim.prototipomobilebdinterno;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ApplicationActivity extends AppCompatActivity {
    TextView mTitle,mPrice,mVersion,mDesc;
    DatabaseHelper helper;
    SQLiteDatabase db;
    String nameapp,version;
    double preco;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
        helper = new DatabaseHelper(this);
        db = helper.getReadableDatabase();
        int id = getIntent().getIntExtra("IdApp",0);
        mPrice =(TextView)findViewById(R.id.txtPriceApp);
        mTitle =(TextView)findViewById(R.id.txtTitleApp);
        mVersion =(TextView)findViewById(R.id.txtVersionApp);
        mDesc =(TextView)findViewById(R.id.txtDescApp);
        getApp(id);
        mTitle.setText(nameapp);
        mVersion.setText(version);
        mPrice.setText("R$ "+String.valueOf(preco));
        mDesc.setText("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff"+String.valueOf(id));
    }
    private void getApp(int id){
        Cursor cursorapp = db.rawQuery("SELECT NameApp, PriceApp,VersionApp from Application WHERE _IdApp = "+id, null);
        cursorapp.moveToFirst();
        for (int j = 0; j < cursorapp.getCount(); j++) {
            nameapp = cursorapp.getString(0);
            preco = cursorapp.getDouble(1);
            version = cursorapp.getString(2);
            cursorapp.moveToNext();
        }
        cursorapp.close();
    }
}
