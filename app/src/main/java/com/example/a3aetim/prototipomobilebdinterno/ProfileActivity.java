package com.example.a3aetim.prototipomobilebdinterno;

import android.app.ActionBar;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.preference.PreferenceActivity;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

import es.dmoral.toasty.Toasty;

public class ProfileActivity extends AppCompatActivity {
    RelativeLayout.LayoutParams mparams;
    private CollapsingToolbarLayout collapsingToolbar;
    private AppBarLayout appBarLayout;
    private RecyclerView recyclerView;


    private Menu collapsedMenu;
    private boolean appBarExpanded = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Ysy");


        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.d(ProfileActivity.class.getSimpleName(), "onOffsetChanged: verticalOffset: " + verticalOffset);

                //  Vertical offset == 0 indicates appBar is fully expanded.
                if (Math.abs(verticalOffset) > 200) {
                    appBarExpanded = false;
                    invalidateOptionsMenu();
                } else {
                    appBarExpanded = true;
                    invalidateOptionsMenu();
                }
            }
        });

    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (collapsedMenu != null
                && (!appBarExpanded || collapsedMenu.size() != 1)) {
            //collapsed
            collapsedMenu.add("Add")
                    .setIcon(R.drawable.ic_menu_send)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        } else {
            //expanded
        }
        return super.onPrepareOptionsMenu(collapsedMenu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        collapsedMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_settings:
                return true;
        }
        if (item.getTitle() == "Add") {
            Toasty.info(this, "clicked add", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
    //No onCreate
    //mparams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
    //    AboutView view = AboutBuilder.with(this)
    //            .setPhoto(R.mipmap.profile_picture)
    //            .setCover(R.mipmap.profile_cover)
    //            .setName("Your Full Name")
    //            .setSubTitle("Mobile Developer")
    //            .setBrief("I'm warmed of mobile technologies. Ideas maker, curious and nature lover.")
    //            .setAppIcon(R.mipmap.ic_launcher)
    //            .setAppName(R.string.app_name)
    //            .setAppName("fdfdfd")
    //            .addGitHubLink("user")
    //            .addFacebookLink("user")
    //            .addFiveStarsAction()
    //            .setVersionNameAsAppSubTitle()
    //            .addShareAction(R.string.app_name)
    //            .setWrapScrollView(true)
    //    addContentView(view, mparams);
}
