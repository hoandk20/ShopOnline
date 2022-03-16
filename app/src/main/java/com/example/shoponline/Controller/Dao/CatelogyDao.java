package com.example.shoponline.Controller.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.shoponline.Model.Category;
import com.example.shoponline.Model.CategoryWithProducts;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CatelogyDao {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Category category);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllCatelogy(List<Category> categorys);

    @Query("SELECT * FROM category")
    List<Category> getAllCategory();

    @Query("SELECT * FROM category")
    List<CategoryWithProducts> getCategoryWithProduct();
}
