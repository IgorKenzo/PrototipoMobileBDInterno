package com.example.a3aetim.prototipomobilebdinterno;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.transition.ChangeBounds;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a3aetim.prototipomobilebdinterno.Classes.ImageDAO;
import com.example.a3aetim.prototipomobilebdinterno.Classes.User;
import com.example.a3aetim.prototipomobilebdinterno.Fragments.*;

import java.util.Locale;

import static com.example.a3aetim.prototipomobilebdinterno.Splash.PREF_NAME;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    User loggedUser;
    NavigationView navigationView;
    Bitmap img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadLocale();
        getWindow().setSharedElementExitTransition(new ChangeBounds());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.frameContentMain, new MarketFragment());
            ft.commit();
        }

        loggedUser = (User) getIntent().getSerializableExtra("LoggedUser");
        //img = BitmapFactory.decodeByteArray(loggedUser.getPicUser(),0,loggedUser.getPicUser().length);
        img = ImageDAO.loadImageFromStorage(loggedUser.getPicUser());
        RoundedBitmapDrawable imgRound = RoundedBitmapDrawableFactory.create(getResources(),img);
        imgRound.setCornerRadius(100);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        ImageView navImgView = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.imgvNavHeader);
        TextView txtNomeUsu = (TextView) navigationView.getHeaderView(0).findViewById(R.id.txtvNameUserNav);
        TextView txtEmailUsu = (TextView) navigationView.getHeaderView(0).findViewById(R.id.txtEmailUserNav);
        navImgView.setImageDrawable(imgRound);
        txtEmailUsu.setText(loggedUser.getEmailUser());
        txtNomeUsu.setText(loggedUser.getNameUser());
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProf();
            }
        });

      setLoggedUser();
    }

    @Override
    public void onRestart(){
        super.onRestart();
        reiniciar();
    }

    private void openProf(){
        Intent intent = new Intent(this,ProfileActivity.class);
        intent.putExtra("ProfileUser",loggedUser);
        startActivity(intent);
    }

    private void setLoggedUser(){
        SharedPreferences sp = getSharedPreferences(PREF_NAME,0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("EmailLoggedUser",loggedUser.getEmailUser());
        editor.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Search) {

        }
        else if (id == R.id.nav_Market) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frameContentMain, new MarketFragment());
            ft.commit();
        } else if (id == R.id.nav_Avaliation) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frameContentMain, new CommentFragment());
            ft.commit();
        } else if (id == R.id.nav_Discussions) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frameContentMain, new DiscussionFragment());
            ft.commit();

        } else if (id == R.id.nav_Confing) {
            Intent i = new Intent(this,SettingsActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_RateUs) {

        }
        else  if (id == R.id.nav_LogOut){
            SharedPreferences sp = getSharedPreferences(PREF_NAME,0);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("EmailLoggedUser","");
            editor.commit();
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setLocale(String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
/*        */
    }

    public void loadLocale(){
        SharedPreferences sp = getSharedPreferences(PREF_NAME,0);
        String language = sp.getString("lang","");
        setLocale(language);
    }

    public void reiniciar(){
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }
}
