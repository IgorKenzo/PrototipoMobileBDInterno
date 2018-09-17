package com.example.a3aetim.prototipomobilebdinterno;

import java.io.Serializable;

public class User implements Serializable {
    private int _IdUser;
    private String LoginUser;
    private String PassUser;
    private String NameUser;
    private String BirthUser;
    private String EmailUser;
    private byte[] PicUser;
    private int CountryUser;
    private int TypeUser;
    private String CrtDateUser;
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
    User(int id, String birthUser, int countryUser, String crtDateUser, String emailUser, int iddev, int idlang, String loginUser, String nameUser, String passUser, byte[] picUser, int typeUser){
        this._IdUser = id;
        this.BirthUser = birthUser;
        this.CountryUser = countryUser;
        this.CrtDateUser = crtDateUser;
        this.EmailUser = emailUser;
        this.IdDev = iddev;
        this.IdLang = idlang;
        this.LoginUser = loginUser;
        this.NameUser = nameUser;
        this.PassUser = passUser;
        this.PicUser = picUser;
        this.TypeUser = typeUser;
    }

    public byte[] getPicUser() {
        return PicUser;
    }

    public String getNameUser() {
        return NameUser;
    }

    public String getEmailUser() {
        return EmailUser;
    }
}