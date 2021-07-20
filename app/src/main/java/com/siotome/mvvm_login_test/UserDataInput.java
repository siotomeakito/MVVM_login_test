package com.siotome.mvvm_login_test;

import android.util.Patterns;

public class UserDataInput {
    private String email;
    private String password;
    private String name;
    private String UID;

    public UserDataInput(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email == null ? "" : email;
    }

    public String getPassword() {
        return password == null ? "" : password;
    }

    public boolean isEmailValid() {
        return Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches();
    }

    public void setName(String username, String uid){
        this.name = username;
        this.UID = uid;
    }

    public String getName(){
        return name == null ? "" : name;
    }
}
