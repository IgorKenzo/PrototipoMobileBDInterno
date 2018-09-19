package com.example.a3aetim.prototipomobilebdinterno;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class MarketFragment extends Fragment {
    private DatabaseHelper helper;
    private SQLiteDatabase db;
    private ArrayList<Application> app;
    private RecyclerView mRecyclerView;
    private ApplicationAdapter mRVAdapter;
    private RecyclerView.LayoutManager mRVLManager;
    private int[] ids;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_market, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        helper = new DatabaseHelper(getActivity());
        db = helper.getReadableDatabase();
        app = new ArrayList<>();
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerViewMarket);
        mRVLManager = new LinearLayoutManager(getActivity());
        setmRecyclerView();
    }
    private void setmRecyclerView(){
        getApps();
        mRecyclerView.setHasFixedSize(true);
        mRVAdapter = new ApplicationAdapter(app);
        mRecyclerView.setLayoutManager(mRVLManager);
        mRecyclerView.setAdapter(mRVAdapter);

        mRVAdapter.setOnitemClickListener(new ApplicationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                int id = ids[position];
                Intent i = new Intent(getContext(),ApplicationActivity.class);
                i.putExtra("IdApp",id);
                startActivity(i);
            }
        });
    }

    private void getApps(){
        Cursor cursorapp = db.rawQuery("SELECT _IdApp, NameApp, PriceApp,VersionApp from Application", null);
        int idapp;
        ids = new int[cursorapp.getCount()];
        String nameapp,version;
        double preco;
        cursorapp.moveToFirst();
        for (int j = 0; j < cursorapp.getCount(); j++) {
            idapp = cursorapp.getInt(0);
            nameapp = cursorapp.getString(1);
            preco = cursorapp.getDouble(2);
            version = cursorapp.getString(3);
            app.add(new Application(idapp,nameapp,preco,version));
            ids[j] = idapp;
            cursorapp.moveToNext();
        }
        cursorapp.close();
    }

    @Override
    public void onDestroy(){
        helper.close();
        super.onDestroy();
    }
}