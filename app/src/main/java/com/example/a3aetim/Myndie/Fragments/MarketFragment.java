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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.a3aetim.Myndie.Adapters.ApplicationAdapter;
import com.example.a3aetim.Myndie.Adapters.BackAppAdapter;
import com.example.a3aetim.Myndie.Adapters.GenreAdapter;
import com.example.a3aetim.Myndie.ApplicationActivity;
import com.example.a3aetim.Myndie.Classes.Application;
import com.example.a3aetim.Myndie.Classes.Genre;
import com.example.a3aetim.Myndie.Connection.AppConfig;
import com.example.a3aetim.Myndie.Connection.AppController;
import com.example.a3aetim.Myndie.R;
import com.example.a3aetim.Myndie.SearchableActivity;
import com.example.a3aetim.Myndie.helper.DatabaseHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

public class MarketFragment extends Fragment {
    private DatabaseHelper helper;
    private SQLiteDatabase db;
    private ArrayList<Application> app;
    private ArrayList<Genre> genre;
    private ArrayList<String> fundoNew,fundoPromo,fundoAvaliation;
    private RecyclerView mRecyclerViewNew,mRecyclerViewPromo,mRecyclerViewAvaliation;
    private ApplicationAdapter mRVAdapter;
    private BackAppAdapter mBackAdapterNew,mBackAdapterPromo,mBackAdapterAvaliation;
    private RecyclerView.LayoutManager mRVLManagerNew,mRVLManagerPromo,mRVLManagerAvaliation,mRVLManagerFundoNew,mRVLManagerFundoPromo,mRVLManagerFundoAvaliation;
    private EditText mSearch;
    RecyclerView genreRecyclerView;
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
        ///
        genreRecyclerView = view.findViewById(R.id.recyclerViewMarketGenre);
        genreRecyclerView.setHasFixedSize(true);
        setmRecyclerViewNew();
        setmRecyclerViewPromo();
        setmRecyclerViewAvaliation();
        setGenreMarket();
       /* mSearch = (EditText)view.findViewById(R.id.edtSearchMarket);
        mSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mBackAdapterNew.setAppFilter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/
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
        genre = new ArrayList<>();
        fundoNew = new ArrayList<String>();
        fundoNew.add(getResources().getString(R.string.market_base_new));
        fundoPromo = new ArrayList<String>();
        fundoPromo.add(getResources().getString(R.string.market_base_promo));
        fundoAvaliation = new ArrayList<String>();
        fundoAvaliation.add(getResources().getString(R.string.market_base_avaliated));
        mRVLManagerNew = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        mRVLManagerPromo = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        mRVLManagerAvaliation = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        mRVLManagerFundoNew = new LinearLayoutManager(getActivity());
        mRVLManagerFundoPromo = new LinearLayoutManager(getActivity());
        mRVLManagerFundoAvaliation = new LinearLayoutManager(getActivity());
    }
    private void setmRecyclerViewNew(){
        load();
        getAllApps();
            mRVAdapter = new ApplicationAdapter(app);
            mBackAdapterNew = new BackAppAdapter(fundoNew,mRVAdapter,mRVLManagerNew);
            //Define quadro que fica atrás dos apps
            mRecyclerViewNew.setLayoutManager(mRVLManagerFundoNew);
            mRecyclerViewNew.setAdapter(mBackAdapterNew);

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
    private void setmRecyclerViewPromo(){
        mRVAdapter = new ApplicationAdapter(app);
        mBackAdapterPromo = new BackAppAdapter(fundoPromo,mRVAdapter,mRVLManagerPromo);
        //Define quadro que fica atrás dos apps
        mRecyclerViewPromo.setLayoutManager(mRVLManagerFundoPromo);
        mRecyclerViewPromo.setAdapter(mBackAdapterPromo);

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
    private void setmRecyclerViewAvaliation(){
        mRVAdapter = new ApplicationAdapter(app);
        mBackAdapterAvaliation = new BackAppAdapter(fundoAvaliation,mRVAdapter,mRVLManagerAvaliation);
        //Define quadro que fica atrás dos apps
        mRecyclerViewAvaliation.setLayoutManager(mRVLManagerFundoAvaliation);
        mRecyclerViewAvaliation.setAdapter(mBackAdapterAvaliation);

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
    private void setGenreMarket(){
        loadGenre();
        GenreAdapter mGenredapter = new GenreAdapter(genre);
        //Define quadro que fica atrás dos apps

        genreRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        genreRecyclerView.setAdapter(mGenredapter);

        mGenredapter.setOnitemClickListener(new GenreAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view,int position) {
                Intent i = new Intent(getContext(),SearchableActivity.class);
                i.setAction(Intent.ACTION_SEARCH);
                i.putExtra("Genre",genre.get(position));
                startActivity(i);
            }
        });
    }

    private void loadGenre(){
        Cursor cursorgen = db.rawQuery("SELECT _IdGenre, NameGen from Genre", null);
        int idgen;
        String namegen;
        cursorgen.moveToFirst();
        for (int j = 0; j < cursorgen.getCount(); j++) {
            idgen = cursorgen.getInt(0);
            namegen = cursorgen.getString(1);
            genre.add(new Genre(idgen,namegen));
            cursorgen.moveToNext();
        }
        cursorgen.close();
    }

    private void getApps(){
        Cursor cursorapp = db.rawQuery("SELECT _IdApp, NameApp, PriceApp,VersionApp, PublisherNameApp, ReleaseDateApp, DescApp from Application", null);
        int idapp;
        String nameapp,version, publisher, desc;
        double preco;
        String releasedate;
        cursorapp.moveToFirst();
        for (int j = 0; j < cursorapp.getCount(); j++) {
            idapp = cursorapp.getInt(0);
            nameapp = cursorapp.getString(1);
            preco = cursorapp.getDouble(2);
            version = cursorapp.getString(3);
            publisher = cursorapp.getString(4);
            releasedate = cursorapp.getString(5);
            desc = cursorapp.getString(6);
            app.add(new Application(idapp,nameapp,preco,version,desc,publisher,releasedate));
            cursorapp.moveToNext();
        }
        cursorapp.close();
    }

    private void getAllApps() {
        // Tag used to cancel the request
        String tag_string_req = "req_login";



        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_ListaApps, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    JSONArray listaAplicativosResponse = new JSONArray(response);
                    //JSONArray appJSONArray = jsonObjectApp.getJSONArray("app");
                    //JSONObject jApp;

                    //boolean error = jsonObjectApp.getBoolean("error");

                    // Check for error node in json
                        // user successfully logged in
                        // Create login session
                        // Now store the user in SQLite
                        /*String uid = jObj.getString("Id");

                        JSONObject user = jObj.getJSONObject("User");
                        String username = user.getString("Username");

                        AlertDialog d = new AlertDialog.Builder(MainActivity.this).setMessage(uid + username).show();*/

                        //Toast.makeText(getApplicationContext(), uid + username, Toast.LENGTH_LONG).show();
                        for (int i = 0; i < listaAplicativosResponse.length(); i++) {
                            JSONObject jsonObjectApp = new JSONObject(listaAplicativosResponse.getString(i));
                            int idapp = Integer.parseInt(jsonObjectApp.getString("Id"));
                            String title = jsonObjectApp.getString("Name");
                            String desc = jsonObjectApp.getString("Desc");
                            String version = jsonObjectApp.getString("Version");
                            double price = Double.parseDouble(jsonObjectApp.getString("Price"));
                            String publisher = jsonObjectApp.getString("PublisherName");
                            String releasedate = jsonObjectApp.getString("releasedate");
                            Application objetoApp = new Application(idapp, title, price, version, desc, publisher, releasedate);
                            app.add(objetoApp);
                        }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {



            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {



        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    @Override
    public void onDestroy(){
        helper.close();
        super.onDestroy();
    }
}