package com.example.shoponline.Model;

public class Description {
    long productId;
    String des;

    public Description(long productId, String des) {
        this.productId = productId;
        this.des = des;
    }

    public long getProductId() {
        return productId;
    }

    public Description() {
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
