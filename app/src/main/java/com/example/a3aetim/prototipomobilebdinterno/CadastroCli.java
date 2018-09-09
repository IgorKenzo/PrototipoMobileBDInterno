package com.example.a3aetim.prototipomobilebdinterno;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static android.media.MediaRecorder.VideoSource.CAMERA;

public class CadastroCli extends Activity implements AdapterView.OnItemSelectedListener {
    DatabaseHelper helper;
    EditText txtName, txtEmail,txtUser, txtPass, txtBirth;
    Spinner country,lang;
    Date crtdata;
    int countryid,langid,PICK_IMAGE = 100;
    Button btnChoosePic;
    SimpleDateFormat df;
    Uri imageURI;
    ImageView imgvArchive;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cli);
        btnChoosePic = (Button)findViewById(R.id.btnChoosePic);
        df = new SimpleDateFormat("dd/MM/yyyy");
        helper = new DatabaseHelper(this);
        country = (Spinner)findViewById(R.id.spnCountry);
        lang = (Spinner)findViewById(R.id.spnLang);
        country.setOnItemSelectedListener(this);
        lang.setOnItemSelectedListener(this);
        btnChoosePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog();
            }
        });
        txtName = (EditText)findViewById(R.id.edtName);
        txtEmail = (EditText)findViewById(R.id.edtEmail);
        txtUser = (EditText)findViewById(R.id.edtUser);
        txtPass = (EditText)findViewById(R.id.edtPass);
        txtEmail = (EditText)findViewById(R.id.edtEmail);
        txtBirth = (EditText)findViewById(R.id.edtBirth);
        crtdata = new Date();
        imgvArchive = (ImageView)findViewById(R.id.imgvArchive);
        List<String> paises = new ArrayList<>(Arrays.asList("Brazil","United States","Canada"));

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, paises );
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country.setAdapter(dataAdapter);

        List<String> linguagens = new ArrayList<>(Arrays.asList("Portuguese","English"));

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, linguagens );
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lang.setAdapter(dataAdapter2);
    }
    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this,AlertDialog.THEME_DEVICE_DEFAULT_DARK);
        pictureDialog.setTitle("Selecione");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }
    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, PICK_IMAGE);
    }
    private void takePhotoFromCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, 3);
        }
        else{
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);}
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == PICK_IMAGE) {
            if (data != null) {
                imageURI = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageURI);

                    Toast.makeText(this, "Escolhida", Toast.LENGTH_SHORT).show();
                    imgvArchive.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            bitmap = (Bitmap) data.getExtras().get("data");
            imgvArchive.setImageBitmap(bitmap);
            Toast.makeText(this, "Escolhida", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void Cad(View view){
        byte[] img = getBitmapAsByteArray(bitmap);
        countryid = country.getSelectedItemPosition();
        langid = lang.getSelectedItemPosition();
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values =  new ContentValues();
        values.put("LoginUser", String.valueOf(txtUser.getText()));
        values.put("PassUser",String.valueOf(txtPass.getText()));
        values.put("NameUser",String.valueOf(txtName.getText()));
        values.put("BirthUser",String.valueOf(txtBirth.getText()));
        values.put("EmailUser",String.valueOf(txtBirth.getText()));
        values.put("PicUser",img);
        values.put("CountryUser",countryid);
        values.put("TypeUser",0);
        values.put("CrtDateUser", String.valueOf(crtdata));
        values.put("IdLang",langid);
        long res = db.insert("User",null,values);
        if(res != -1){
            Toast.makeText(this, "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Um erro ocorreu!", Toast.LENGTH_SHORT).show();
        }
    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }
    @Override
    protected void onDestroy(){
        helper.close();
        super.onDestroy();
    }



    public void listar(View view) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT _IdUser, NameUser from User", null);
        cursor.moveToFirst();
        int id = 0;
        String name ="";
        String[] valores = new String[10];
        for (int i = 0; i < cursor.getCount(); i++) {
            id = cursor.getInt(0);
            name = cursor.getString(1);
            valores = new String[]{id + name};
        }
        AlertDialog.Builder listaUsu = new AlertDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_DARK);
        listaUsu.setTitle("usuarios");
        listaUsu.setCancelable(true);
        for(int i = 0 ; i < cursor.getCount() ; i++) {
            listaUsu.setItems(valores, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0:

                            break;

                    }
                }
            });
            listaUsu.show();
        }

    }
}
