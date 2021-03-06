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
    public String GetCartByUserId = "https://personal-p3ceovob.outsystemscloud.com/ShoppingOnline/rest/API/GetCartByUserId?InUserId=%s";
    public String DeleteCartById = "https://personal-p3ceovob.outsystemscloud.com/ShoppingOnline/rest/API/DeleteCartById?InCartId=%25s&fbclid=IwAR2ubIpH18bVL8-T-pMZJdeFaMheG-rgi7mxkj0xodfwwMJ3McaJizZiDH4";
    public String AddToCart = "https://personal-p3ceovob.outsystemscloud.com/ShoppingOnline/rest/API/AddToCart?InProductId=%s&InProductName=%s&InQuantity=%s&InUnitPrice=%s&InTotalPrice=%s&InAccountId=%s&InImageId=%s";
    public String UpdateAccount = "https://personal-p3ceovob.outsystemscloud.com/ShoppingOnline/rest/API/UpdateAccount?InUsername=%s&InPassword=%s&InPhoneNumber=%s&InAddress=%s&InUserId=%s";
    public String UpdatePassword = "https://personal-p3ceovob.outsystemscloud.com/ShoppingOnline/rest/API/ChangePassword?InAccountId=%s&InPassword=%s";
    public String GetListImageBase64 = "https://personal-p3ceovob.outsystemscloud.com/ShoppingOnline/rest/API/GetListImageBase64";

    public String GetBillByUserId = "https://personal-p3ceovob.outsystemscloud.com/ShoppingOnline/rest/API/GetBillByUserId?InUseId=%s";
    public String AddBill= "https://personal-p3ceovob.outsystemscloud.com/ShoppingOnline/rest/API/AddToBill?InAccountId=%s&InProductName=%s&InProductId=%s&InQuantity=%s&InunitPrice=%s&InTotalPrice=%s&InImageId=%s";
    public String GetProductById = "https://personal-p3ceovob.outsystemscloud.com/ShoppingOnline/rest/API/GetProductById?InProductId=%s";
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
