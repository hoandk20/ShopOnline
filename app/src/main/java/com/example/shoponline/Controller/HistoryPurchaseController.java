package com.example.shoponline.Controller;

import com.example.shoponline.Common.API;
import com.example.shoponline.Model.Bill;
import com.example.shoponline.Model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class HistoryPurchaseController {

    API api = new API();

    public ArrayList<Bill> GetAllBillById(String id){
        ArrayList<Bill> list = new ArrayList<>();

        String ApiLogin = String.format(api.GetAllBillById,id);
        String content = api.GetStringFromApi(ApiLogin);
        try {
            JSONObject obj = new JSONObject(content);
            JSONArray jsonArray = obj.getJSONArray("Bill");
            if (jsonArray != null) {

                for (int i=0;i<jsonArray.length();i++){
                    Bill b = new Bill();
                    JSONObject o = new JSONObject();
                    o = (JSONObject)jsonArray.getJSONObject(i);
                    b.setBillId(o.getString("Id"));
                    b.setAccountId(o.getLong("AccountID"));
                    b.setDateBuy(LocalDate.parse(o.getString("DateBuy"), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    b.setProductId(o.getString("ProductId"));
                    b.setQuantity(o.getInt("Quantity"));
                    b.setTotalQuantity(o.getInt("TotalQuantity"));
                    b.setUnitPrice(o.getDouble("UnitPrice"));
                    b.setTotalPrice(o.getDouble("TotalPrice"));
                    b.setImageId(o.getString("ImageId"));
                    b.setNameProduct(o.getString("NameProduct"));
                    b.setType(o.getString("Type"));
                    list.add(b);

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
