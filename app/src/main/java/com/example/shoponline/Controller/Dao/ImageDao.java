package com.example.shoponline.Controller.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.shoponline.Model.Image;

import java.util.List;

@Dao
public interface ImageDao {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertImage(List<Image> images);

    @Query("SELECT * FROM Image Where imageId = :imageId")
    Image loadImageById(Long imageId);
}
