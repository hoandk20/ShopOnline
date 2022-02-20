package com.example.shoponline.Controller;

import com.example.shoponline.Common.API;

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
}
