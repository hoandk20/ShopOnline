package com.example.shoponline.Model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(foreignKeys = @ForeignKey(entity = Category.class,
parentColumns = {"categoryId"},
childColumns = {"catelogyOwnerId"},
onDelete = ForeignKey.CASCADE))
public class Product implements Serializable{
    @NonNull
    @PrimaryKey
    private String productId;
    @ColumnInfo
    private String catelogyOwnerId;
    @ColumnInfo
    private String productName;
    @ColumnInfo
    private String productPrice;
    @ColumnInfo
    private String productQuantity;
    @ColumnInfo
    private String ImageId;

    public Product(){}

    public Product(String catelogyOwnerId, String productName, String productPrice, String productQuantity, String imageId) {
        this.catelogyOwnerId = catelogyOwnerId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        ImageId = imageId;
    }

    @NonNull
    public String getProductId() {
        return productId;
    }

    public void setProductId(@NonNull String productId) {
        this.productId = productId;
    }

    public String getCatelogyOwnerId() {
        return catelogyOwnerId;
    }

    public void setCatelogyOwnerId(String catelogyOwnerId) {
        this.catelogyOwnerId = catelogyOwnerId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getImageId() {
        return ImageId;
    }

    public void setImageId(String imageId) {
        ImageId = imageId;
    }
}
