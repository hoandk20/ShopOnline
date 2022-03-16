package com.example.shoponline.Controller.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.shoponline.Model.Product;

import java.util.List;

@Dao
public interface ProductDao {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProduct(List<Product> products);

    @Query("SELECT * FROM Product")
    List<Product> getAllProduct();

    @Query("SELECT * FROM Product WHERE catelogyOwnerId = :categoryid")
    List<Product> getAllProductById(int categoryid);
}
