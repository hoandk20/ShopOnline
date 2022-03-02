package com.example.shoponline.Model;

import java.io.Serializable;

public class Product implements Serializable {
    String Id;
    String CategoryId;
    String Name;
    String Price;
    String Quantity;
    String ImageId;

    public Product(String categoryId, String name, String price, String quantity, String imageId) {
        CategoryId = categoryId;
        Name = name;
        Price = price;
        Quantity = quantity;
        ImageId = imageId;
    }
    public  Product(){

    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(String categoryId) {
        CategoryId = categoryId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getImageId() {
        return ImageId;
    }

    public void setImageId(String imageId) {
        ImageId = imageId;
    }
}
