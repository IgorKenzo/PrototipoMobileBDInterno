package com.example.a3aetim.prototipomobilebdinterno;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.a3aetim.prototipomobilebdinterno.Adapters.ApplicationAdapter;
import com.example.a3aetim.prototipomobilebdinterno.Classes.Application;
import com.example.a3aetim.prototipomobilebdinterno.helper.DatabaseHelper;

import java.util.ArrayList;

public class SearchableActivity extends AppCompatActivity {
    private DatabaseHelper helper;
    private SQLiteDatabase db;
    private ArrayList<Application> app;
    private RecyclerView mRecyclerView;
    private ApplicationAdapter mRVAdapter;
    private RecyclerView.LayoutManager mRVLManager;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);

        toolbar = (Toolbar)findViewById(R.id.toolbar_Search);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("");
        mRecyclerView = findViewById(R.id.recyclerSearchActivity);
        mRecyclerView.setHasFixedSize(true);
        setmRecyclerView();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        searchHandler(intent);
    }

    private void load(){
        helper = new DatabaseHelper(this);
        db = helper.getReadableDatabase();
        app = new ArrayList<>();
        mRVLManager = new LinearLayoutManager(this);
    }
    private void setmRecyclerView(){
        load();
        searchHandler(getIntent());
        mRVAdapter = new ApplicationAdapter(app);
        mRecyclerView.setLayoutManager(mRVLManager);
        mRecyclerView.setAdapter(mRVAdapter);

        mRVAdapter.setOnitemClickListener(new ApplicationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view,int position) {
                Intent i = new Intent(getApplicationContext(),ApplicationActivity.class);
                i.putExtra("App",app.get(position));
                ImageView mImgvApp = (ImageView)view.findViewById(R.id.imgvAppItemMarket);
                //ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, Pair.<View, String>create(mImgvApp,"AppTransition"));
                startActivity(i/*,options.toBundle()*/);
            }
        });
    }

    private void getApps(String queryNome){
        Cursor cursorapp = db.rawQuery("SELECT _IdApp, NameApp, PriceApp,VersionApp from Application WHERE NameApp LIKE '%"+ queryNome+"%'", null);
        int idapp;
        String nameapp,version;
        double preco;
        cursorapp.moveToFirst();
        for (int j = 0; j < cursorapp.getCount(); j++) {
            idapp = cursorapp.getInt(0);
            nameapp = cursorapp.getString(1);
            preco = cursorapp.getDouble(2);
            version = cursorapp.getString(3);
            app.add(new Application(idapp,nameapp,preco,version));
            cursorapp.moveToNext();
        }
        cursorapp.close();
    }
    public void searchHandler(Intent intent){
        if(Intent.ACTION_SEARCH.toLowerCase().equalsIgnoreCase(intent.getAction().toLowerCase())){
            String query = intent.getStringExtra(SearchManager.QUERY);
            toolbar.setTitle(query);
            getApps(query);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.searchablemenu,menu);
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
    @Override
    protected void onDestroy(){
        helper.close();
        super.onDestroy();
    }
}
