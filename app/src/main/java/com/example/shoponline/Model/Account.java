package com.example.shoponline.Model;

public class Account {
    long Id;
    String Username;

    public long getId() {
        return Id;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setPassword(String password) {
        Password = password;
    }

    String Password;
}
