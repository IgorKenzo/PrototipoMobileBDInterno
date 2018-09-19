package com.example.a3aetim.prototipomobilebdinterno;

import java.io.Serializable;

public class Application implements Serializable{
    private int _IdApp;
    private String Title;
    private double Price;
    //private String Description;
    private String Version;

    Application(int id,String title,double preco,String ver){
        _IdApp = id;
        Title = title;
        Price = preco;
        //Description = desc;
        Version = ver;
    }

    public int get_IdApp() {
        return _IdApp;
    }

    public String getTitle() {
        return Title;
    }

    public double getPrice() {
        return Price;
    }

    /*public String getDescription() {
        return Description;
    }*/

    public String getVersion() {
        return Version;
    }
}
