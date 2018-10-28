package com.example.a3aetim.Myndie.Fragments;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.a3aetim.Myndie.Classes.Application;
import com.example.a3aetim.Myndie.ApplicationActivity;
import com.example.a3aetim.Myndie.Adapters.ApplicationAdapter;
import com.example.a3aetim.Myndie.helper.DatabaseHelper;
import com.example.a3aetim.Myndie.R;

import java.util.ArrayList;

public class MarketFragment extends Fragment {
    private DatabaseHelper helper;
    private SQLiteDatabase db;
    private ArrayList<Application> app;
    private RecyclerView mRecyclerViewNew,mRecyclerViewPromo,mRecyclerViewAvaliation;
    private ApplicationAdapter mRVAdapter;
    private RecyclerView.LayoutManager mRVLManager,mRVLManagerPromo,mRVLManagerAvaliation;
    private EditText mSearch;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_market, container, false);
        view.findViewById(R.id.recyclerViewMarket);
        mRecyclerViewNew = view.findViewById(R.id.recyclerViewMarket);
        mRecyclerViewNew.setHasFixedSize(true);
        ///
        mRecyclerViewPromo = view.findViewById(R.id.recyclerViewMarketPromo);
        mRecyclerViewPromo.setHasFixedSize(true);
        ///
        mRecyclerViewAvaliation = view.findViewById(R.id.recyclerViewMarketAvaliation);
        mRecyclerViewAvaliation.setHasFixedSize(true);
        setmRecyclerView();
        mSearch = (EditText)view.findViewById(R.id.edtSearchMarket);
        mSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mRVAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void load(){
        helper = new DatabaseHelper(getActivity());
        db = helper.getReadableDatabase();
        app = new ArrayList<>();
        mRVLManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        mRVLManagerPromo = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        mRVLManagerAvaliation = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
    }
    private void setmRecyclerView(){
        load();
        getApps();
        mRVAdapter = new ApplicationAdapter(app);
        mRecyclerViewNew.setLayoutManager(mRVLManager);
        mRecyclerViewNew.setAdapter(mRVAdapter);
        mRecyclerViewPromo.setLayoutManager(mRVLManagerPromo);
        mRecyclerViewPromo.setAdapter(mRVAdapter);
        mRecyclerViewAvaliation.setLayoutManager(mRVLManagerAvaliation);
        mRecyclerViewAvaliation.setAdapter(mRVAdapter);

        mRVAdapter.setOnitemClickListener(new ApplicationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view,int position) {
                Intent i = new Intent(getContext(),ApplicationActivity.class);
                i.putExtra("App",app.get(position));
                ImageView mImgvApp = (ImageView)view.findViewById(R.id.imgvAppItemMarket);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), Pair.<View, String>create(mImgvApp,"AppTransition"));
                startActivity(i,options.toBundle());
            }
        });
    }

    private void getApps(){
        Cursor cursorapp = db.rawQuery("SELECT _IdApp, NameApp, PriceApp,VersionApp from Application", null);
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

    @Override
    public void onDestroy(){
        helper.close();
        super.onDestroy();
    }
}