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

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.a3aetim.Myndie.Classes.Application;

import java.util.HashMap;

public class ApplicationActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    TextView mTitle,mPrice,mVersion,mDesc, mPublisherName, mReleaseDate;
    Application app;
    private Toolbar toolbar;
    private SliderLayout mSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
        mPrice =(TextView)findViewById(R.id.txtPriceApp);
        mTitle =(TextView)findViewById(R.id.txtTitleApp);
        mVersion =(TextView)findViewById(R.id.txtVersionApp);
        mDesc =(TextView)findViewById(R.id.txtDescApp);
        mPublisherName = (TextView)findViewById(R.id.txtPublisherNameApp);
        mReleaseDate = (TextView)findViewById(R.id.txtReleaseDateApp);
        mSlider = (SliderLayout)findViewById(R.id.sliderApplication);


        preencherSlider();
        preencherCampos();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    public void preencherCampos(){
        app  =(Application) getIntent().getSerializableExtra("App");
        mTitle.setText(app.getTitle());
        mVersion.setText(app.getVersion());
        mPublisherName.setText(app.getPublisherName());
        mReleaseDate.setText(String.valueOf(app.getReleaseDate()));
        mPrice.setText("R$ "+String.valueOf(app.getPrice()));
        mDesc.setText("Aqui irá uma breve descrição do aplicativo"+String.valueOf(app.get_IdApp()));
    }

    public void preencherSlider(){
        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Descrição Imagem 1",R.drawable.ic_error_outline_white_48dp);
        file_maps.put("Descrição Imagem 2", R.drawable.ic_launcher_foreground);

        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mSlider.addSlider(textSliderView);
        }

        mSlider.setPresetTransformer(SliderLayout.Transformer.Fade);
        mSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mSlider.stopAutoCycle(); //Ele não fica rodando sozinho
        //mSlider.setCustomAnimation(new DescriptionAnimation());
        //mSlider.setDuration(4000);
        mSlider.addOnPageChangeListener(this);
    }

    @Override
    protected void onStop() {
        //Importante para não sobrecarregar a memória do celular (ficaria rodando no fundo)
        mSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
