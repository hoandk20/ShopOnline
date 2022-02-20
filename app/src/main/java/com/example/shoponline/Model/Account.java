package com.example.shoponline.Model;

public class Account {
    String Id;
    String Username;

    public String getId() {
        return Id;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setId(String id) {
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
