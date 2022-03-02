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

        String ApiLogin = String.format(api.GetAllCategorys);
        String content = api.GetStringFromApi(ApiLogin);
        try {
            JSONObject obj = new JSONObject(content);
            JSONArray jsonArray = obj.getJSONArray("CategoryList");
            if (jsonArray != null) {

                for (int i=0;i<jsonArray.length();i++){
                    Category p = new Category();
                    JSONObject o = new JSONObject();
                    o = (JSONObject)jsonArray.getJSONObject(i);
                    p.setId(o.getLong("Id"));
                    p.setCategoryName(o.getString("CategoryName"));
                    list.add(p);
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
