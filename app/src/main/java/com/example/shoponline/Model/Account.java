package com.example.shoponline.Model;

import java.io.Serializable;

public class Account implements Serializable {
    long Id;
    String Username;
    String phone;

    public Account() {
    }

    String Password;

    public void setId(long id) {
        Id = id;
    }

    public String getImageId() {
        return ImageId;
    }

    public void setImageId(String imageId) {
        ImageId = imageId;
    }

    String address;
    String ImageId;
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


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Account(String username, String phone, String password, String address) {
        Username = username;
        this.phone = phone;
        Password = password;
        this.address = address;
    }
}
