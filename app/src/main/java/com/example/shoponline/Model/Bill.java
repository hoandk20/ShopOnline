package com.example.shoponline.Model;

import java.sql.Date;
import java.time.LocalDate;

public class Bill {
    private String billId;
    private long accountId;
    private LocalDate dateBuy;
    private String productId;
    private int quantity;
    private int totalQuantity;
    private double unitPrice;
    private double totalPrice;
    private String imageId;
    private String nameProduct;
    private String type;

    public Bill() {
    }

    public Bill(String billId, long accountId, LocalDate dateBuy, String productId, int quantity, int totalQuantity, double unitPrice, double totalPrice, String imageId, String nameProduct, String type) {
        this.billId = billId;
        this.accountId = accountId;
        this.dateBuy = dateBuy;
        this.productId = productId;
        this.quantity = quantity;
        this.totalQuantity = totalQuantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.imageId = imageId;
        this.nameProduct = nameProduct;
        this.type = type;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public LocalDate getDateBuy() {
        return dateBuy;
    }

    public void setDateBuy(LocalDate dateBuy) {
        this.dateBuy = dateBuy;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
