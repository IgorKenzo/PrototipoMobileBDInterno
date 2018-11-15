package com.example.a3aetim.Myndie.Classes;

import java.io.Serializable;
import java.util.Date;

public class Application implements Serializable{
    private int _IdApp;
    private String Title;
    private double Price;
    private String Description;
    private String Version;
    private String PublisherName;
    private String ReleaseDate;

    public Application(int id, String title, double preco, String ver, String desc, String publisher, String releasedate){
        _IdApp = id;
        Title = title;
        Price = preco;
        Description = desc;
        Version = ver;
        PublisherName = publisher;
        ReleaseDate = releasedate;
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

    public String getDescription() {
        return Description;
    }

    public String getVersion() {
        return Version;
    }

    public String getReleaseDate(){return ReleaseDate;}

    public String getPublisherName(){return PublisherName; }
}
