package com.example.a3aetim.prototipomobilebdinterno;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context,"dbMyndie",null , 1 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Developer(_IdDev INTEGER PRIMARY KEY AUTOINCREMENT, InfoDev TEXT, NumSoftDev INTEGER, NameDev TEXT);");
        db.execSQL("CREATE TABLE User(_IdUser INTEGER PRIMARY KEY AUTOINCREMENT, LoginUser TEXT UNIQUE, PassUser TEXT, NameUser TEXT, " +
                   "BirthUser DATE, EmailUser TEXT, PicUser BLOB, CountryUser INTEGER, TypeUser TINYINT, CrtDateUser DATE, IdLang INTEGER, IdDev INTEGER REFERENCES Developer(_IdDev));");
        db.execSQL("CREATE TABLE Application(_IdApp INTEGER PRIMARY KEY AUTOINCREMENT, NameApp TEXT, VersionApp TEXT, PriceApp DECIMAL, PublisherNameApp TEXT, ReleaseDateApp DATE, ArquiveApp TEXT" +
                    "IdDev REFERENCES Developer(_IdDev), TypeApp INTEGER, PegiApp INTEGER);");
        db.execSQL("CREATE TABLE Comment(_IdComm INTEGER PRIMARY KEY AUTOINCREMENT, ValueComm TEXT, DateComm DATETIME, IdUser REFERENCES User(_IdUser), IdApp REFERENCES Applocation(_IdApp));");
        db.execSQL("CREATE TABLE Review(_IdRev INTEGER PRIMARY KEY AUTOINCREMENT, ValueRev TEXT, DescRev TEXT, DateRev DATETIME, VersionRev TEXT, IdUser REFERENCES User(_IdUser), IdApp REFERENCES Applocation(_IdApp));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
