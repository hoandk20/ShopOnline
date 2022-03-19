package com.example.shoponline.Controller;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.shoponline.Common.API;
import com.example.shoponline.Model.Cart;
import com.example.shoponline.Model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CartController {

    API api = new API();


    public ArrayList<Cart> GetCart(String UserId){
        ArrayList<Cart> list = new ArrayList<>();
        String ApiLogin = String.format(api.GetCartByUserId,UserId);
        String content = api.GetStringFromApi(ApiLogin);
        try {
            JSONObject obj = new JSONObject(content);
            JSONArray jsonArray = obj.getJSONArray("CartList");
            if (jsonArray != null) {
                for (int i=0;i<jsonArray.length();i++){
                    Cart c = new Cart();
                    JSONObject o = new JSONObject();
                    o = (JSONObject)jsonArray.getJSONObject(i);
                    c.setId(o.getLong("Id"));
                    c.setProductId(o.getLong("ProductId"));
                    c.setProductName(o.getString("ProductName"));
                    c.setAccountId(o.getLong("AccountId"));
                    c.setQuantity(o.getInt("Quantity"));
                    c.setUnitPrice(o.getDouble("UnitPrice"));
                    c.setImageId(o.getString("ImageId"));
                    list.add(c);

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void AddToCart(Cart cart){
        String ApiLogin = String.format(api.AddToCart,cart.getProductId(), cart.getProductName(),
                cart.getQuantity(),cart.getUnitPrice(), cart.getTotalPrice(),cart.getAccountId(),cart.getImageId());
        String content = api.GetStringFromApi(ApiLogin);
    }

}
