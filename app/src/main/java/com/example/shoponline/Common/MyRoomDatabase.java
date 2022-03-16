package com.example.shoponline.Common;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.shoponline.Controller.Dao.CatelogyDao;
import com.example.shoponline.Controller.Dao.ImageDao;
import com.example.shoponline.Controller.Dao.ProductDao;
import com.example.shoponline.Model.Bill;
import com.example.shoponline.Model.Cart;
import com.example.shoponline.Model.Category;
import com.example.shoponline.Model.Discount;
import com.example.shoponline.Model.Image;
import com.example.shoponline.Model.Product;

@Database(entities = {Cart.class, Category.class,
        Discount.class, Image.class,
        Product.class}, version = 1)
public abstract class MyRoomDatabase extends RoomDatabase {
    public abstract CatelogyDao createCategoryDao();
    public abstract ImageDao createImageDao();
    public abstract ProductDao createProductDao();
}
