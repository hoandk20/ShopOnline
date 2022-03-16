package com.example.shoponline.Controller;

import com.example.shoponline.Common.API;
import com.example.shoponline.Model.Category;
import com.example.shoponline.Model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryController {
    API api = new API();
    public ArrayList<Category> GetAllCategory(){
        ArrayList<Category> list = new ArrayList<>();

        list.add(new Category(1,"Moniter"));
        list.add(new Category(2,"Phone"));
        list.add(new Category(3,"Book"));
        list.add(new Category(4,"Board"));
        list.add(new Category(5,"Computer"));
        list.add(new Category(6,"Mouse"));
        list.add(new Category(7,"Tablet"));
        list.add(new Category(8,"PC"));
        return list;
    }
}
