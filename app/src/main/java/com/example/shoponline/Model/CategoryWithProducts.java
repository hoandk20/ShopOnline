package com.example.shoponline.Model;


import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class CategoryWithProducts {
    @Embedded
    public Category category;
    @Relation(parentColumn = "categoryId",
    entityColumn = "catelogyOwnerId")
    public List<Product> products;
}
