package com.example.a3aetim.prototipomobilebdinterno;

import java.util.Date;

public class User {
    private int _IdUser;
    private String LoginUser;
    private String PassUser;
    private String NameUser;
    private Date BirthUser;
    private String EmailUser;
    private Byte[] PicUser;
    private int CountryUser;
    private int TypeUser;
    private Date CrtDateUser;
    private int IdLang;
    private int IdDev;

    User(){
        this._IdUser = 0;
        this.BirthUser = null;
        this.CountryUser = 0;
        this.CrtDateUser= null;
        this.EmailUser = null;
        this.IdDev=0;
        this.IdLang=0;
        this.LoginUser=null;
        this.NameUser=null;
        this.PassUser=null;
        this.PicUser=null;
        this.TypeUser=0;
    }
    User(int id, String birth, int country, String crt, String email, int iddev, int idlang, String username, String name, String password, byte[] pic, int type){
        this._IdUser = _IdUser;
        this.BirthUser = birthUser;
        this.CountryUser = countryUser;
        this.CrtDateUser = crtDateUser;
        this.EmailUser = emailUser;
        this.IdDev = idDev;
        this.IdLang = idLang;
        this.LoginUser = loginUser;
        this.NameUser = nameUser;
        this.PassUser = passUser;
        this.PicUser = picUser;
        this.TypeUser = typeUser;
    }
}
