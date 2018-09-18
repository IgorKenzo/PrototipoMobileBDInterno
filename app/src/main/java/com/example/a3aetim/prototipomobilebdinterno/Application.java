package com.example.a3aetim.prototipomobilebdinterno;

public class Application {
    private String Title;
    private double Preco;

    Application(String title,double preco){
        Title = title;
        Preco = preco;
    }

    public String getTitle() {
        return Title;
    }

    public double getPreco() {
        return Preco;
    }
}
