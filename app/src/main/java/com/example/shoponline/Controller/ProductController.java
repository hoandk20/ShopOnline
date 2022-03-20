package com.example.shoponline.Controller;

import com.example.shoponline.Common.API;
import com.example.shoponline.Model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
                    p.setProductId(o.getString("Id"));
                    p.setCatelogyOwnerId(o.getString("CategoryId"));
                    p.setProductName(o.getString("Name"));
                    p.setProductPrice(o.getString("Price"));
                    p.setProductQuantity(o.getString("Quantity"));
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
                    p.setProductId(o.getString("Id"));
                    p.setCatelogyOwnerId(o.getString("CategoryId"));
                    p.setProductName(o.getString("Name"));
                    p.setProductPrice(o.getString("Price"));
                    p.setProductQuantity(o.getString("Quantity"));
                    p.setImageId(o.getString("ImageId"));
                    list.add(p);

                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Product GetProductById(int id){


        String ApiLogin = String.format(api.GetProductById,id);
        String content = api.GetStringFromApi(ApiLogin);
        try {
            JSONObject obj = new JSONObject(content);
            JSONArray jsonArray = obj.getJSONArray("ListProduct");
            if (jsonArray != null) {

                for (int i=0;i<jsonArray.length();i++){
                    Product p = new Product();
                    JSONObject o = new JSONObject();
                    o = (JSONObject)jsonArray.getJSONObject(i);
                    p.setProductId(o.getString("Id"));
                    p.setCatelogyOwnerId(o.getString("CategoryId"));
                    p.setProductName(o.getString("Name"));
                    p.setProductPrice(o.getString("Price"));
                    p.setProductQuantity(o.getString("Quantity"));
                    p.setImageId(o.getString("ImageId"));

                    return p;

                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
       return  null;
    }


    public String getResponseMessage (long Productid){

        String des = "";

        return des;
    }

    public void GetDescription(String ProductId){

    }


}
