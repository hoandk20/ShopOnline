package com.example.shoponline.Model;

public class Category {
    long id;
    String categoryName;

    public long getId() {
        return id;
    }

    public Category() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
