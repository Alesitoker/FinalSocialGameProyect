package com.iessaladillo.alejandro.finalsocialgameproyect.data.local.model;

public class User {

    String uid;
    String name;
    String email;

    public User(String uid, String name, String email) {
        this.uid = uid;
        this.name = name;
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
