package com.example.shoponline.Controller;

import com.example.shoponline.Common.API;
import com.example.shoponline.Model.Bill;
import com.example.shoponline.Model.Cart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BillController {

    API api = new API();

    public ArrayList<Bill> GetBill(String UserId){
        ArrayList<Bill> list = new ArrayList<>();
        String ApiLogin = String.format(api.GetBillByUserId,UserId);
        String content = api.GetStringFromApi(ApiLogin);
        try {
            JSONObject obj = new JSONObject(content);
            JSONArray jsonArray = obj.getJSONArray("ListBill");
            if (jsonArray != null) {
                for (int i=0;i<jsonArray.length();i++){
                    Bill c = new Bill();
                    JSONObject o = new JSONObject();
                    o = (JSONObject)jsonArray.getJSONObject(i);
                    c.setBillId(o.getString("Id"));
                    c.setProductId(o.getString("ProductId"));
                    c.setNameProduct(o.getString("ProductName"));
                    c.setAccountId(o.getLong("AccountId"));
                    c.setQuantity(o.getInt("Quantity"));
                    c.setUnitPrice(o.getDouble("UnitPrice"));
                    c.setTotalPrice(o.getDouble("TotalPrice"));
                    c.setImageId(o.getString("ImageId"));
                    list.add(c);

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void  AddBill(Bill bill){
        String ApiLogin = String.format(api.AddBill,bill.getAccountId(),bill.getNameProduct(),bill.getProductId(),
                bill.getQuantity(),bill.getUnitPrice(),bill.getTotalPrice(),bill.getImageId());
        String content = api.GetStringFromApi(ApiLogin);
    }
}
