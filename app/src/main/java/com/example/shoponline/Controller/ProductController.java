package com.example.shoponline.Controller;

import com.example.shoponline.Common.API;
import com.example.shoponline.Model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductController {
    API api = new API();
    public ArrayList<Product> GetAllProducts(){
        ArrayList<Product> list = new ArrayList<>();

        String ApiLogin = String.format(api.GetAllProducts);
        String content = api.GetStringFromApi(ApiLogin);
        try {
            JSONObject obj = new JSONObject(content);
            JSONArray jsonArray = obj.getJSONArray("Product");
            if (jsonArray != null) {

                for (int i=0;i<jsonArray.length();i++){
                    Product p = new Product();
                    JSONObject o = new JSONObject();
                    o = (JSONObject)jsonArray.getJSONObject(i);
                    p.setId(o.getString("Id"));
                    p.setCategoryId(o.getString("CategoryId"));
                    p.setName(o.getString("Name"));
                    p.setPrice(o.getString("Price"));
                    p.setQuantity(o.getString("Quantity"));
                    p.setImageId(o.getString("ImageId"));
                    list.add(p);

                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
    public ArrayList<Product> GetProductsByCategoryId(int cateId){
        ArrayList<Product> list = new ArrayList<>();

        String ApiLogin = String.format(api.GetAllProductByCategoryId,cateId);
        String content = api.GetStringFromApi(ApiLogin);
        try {
            JSONObject obj = new JSONObject(content);
            JSONArray jsonArray = obj.getJSONArray("Product");
            if (jsonArray != null) {

                for (int i=0;i<jsonArray.length();i++){
                    Product p = new Product();
                    JSONObject o = new JSONObject();
                    o = (JSONObject)jsonArray.getJSONObject(i);
                    p.setId(o.getString("Id"));
                    p.setCategoryId(o.getString("CategoryId"));
                    p.setName(o.getString("Name"));
                    p.setPrice(o.getString("Price"));
                    p.setQuantity(o.getString("Quantity"));
                    p.setImageId(o.getString("ImageId"));
                    list.add(p);

                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

}
