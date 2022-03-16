package com.example.shoponline.Common;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class API {
    public String Login = "https://personal-p3ceovob.outsystemscloud.com/ShoppingOnline/rest/API/Login?InUsername=%s&InPassword=%s";
    public String GetAllProducts = "https://personal-p3ceovob.outsystemscloud.com/ShoppingOnline/rest/API/GetAllProducts";
    public String GetAllCategorys="https://personal-p3ceovob.outsystemscloud.com/ShoppingOnline/rest/API/GetAllCategorys";
    public String GetAllProductByCategoryId= "https://personal-p3ceovob.outsystemscloud.com/ShoppingOnline/rest/API/GetProductByCategoryId?InCategoryId=%s";
    public String GetImageById = "https://personal-p3ceovob.outsystemscloud.com/ShoppingOnline/rest/API/GetImageById?InId=%s";
    public String InsertAccount = "https://personal-p3ceovob.outsystemscloud.com/ShoppingOnline/rest/API/InsertAccount?InUsername=%s&InPassword=%s&InPhoneNumber=%s&InAddress=%s";
    public String getAccountByName = "https://personal-p3ceovob.outsystemscloud.com/ShoppingOnline/rest/API/GetAccountInfo?InUsername=%s";
    public String GetListBase64ImageProduct = "https://personal-p3ceovob.outsystemscloud.com/ShoppingOnline/rest/API/GetAllProductImage";

    public String GetStringFromApi(String api){

        try {
            URL url = new URL(api);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();

            if(responseCode!=200){
                throw new RuntimeException("Http responsecode: "+responseCode);
            }else{
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while(scanner.hasNext()){
                    informationString.append(scanner.nextLine());
                }
                scanner.close();
                return informationString.toString();
            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
