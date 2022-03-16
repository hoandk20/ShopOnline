package com.example.shoponline.Controller;

import com.example.shoponline.Common.API;
import com.example.shoponline.Model.Account;
import com.example.shoponline.Model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginController {
    API api = new API();
    public boolean Login(String username,String password){
        String ApiLogin = String.format(api.Login,username,password);
        String content = api.GetStringFromApi(ApiLogin);
        System.out.println(content);
        try {
            JSONObject obj = new JSONObject(content);
            int UserId = (int) obj.get("Id");
            if(UserId>0){
                return true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void InsertAccount(Account account){
        String ApiLogin = String.format(api.InsertAccount,account.getUsername(),account.getPassword(),account.getPhone(),account.getAddress());
        String content = api.GetStringFromApi(ApiLogin);
    }
    public void UpdateAccount(Account account){
        String ApiLogin = String.format(api.UpdateAccount,account.getUsername(),account.getPassword(),account.getPhone(),account.getAddress(),account.getId());
        String content = api.GetStringFromApi(ApiLogin);
    }
    public Account GetAccountByName(String name){
        Account a = new Account();
        String ApiLogin = String.format(api.GetUserByName,name);
        String content = api.GetStringFromApi(ApiLogin);
        try {
            JSONObject obj = new JSONObject(content);
            JSONArray jsonArray = obj.getJSONArray("Account");
            if (jsonArray != null) {

                for (int i=0;i<jsonArray.length();i++){

                    JSONObject o = new JSONObject();
                    o = (JSONObject)jsonArray.getJSONObject(i);
                    a.setId(o.getLong("Id"));
                    a.setUsername(o.getString("Username"));
                    a.setPassword(o.getString("Password"));
                    a.setPhone(o.getString("Phone"));
                    a.setAddress(o.getString("Address"));
                    a.setImageId(o.getLong("ImageId"));

                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return a;
    }

}
