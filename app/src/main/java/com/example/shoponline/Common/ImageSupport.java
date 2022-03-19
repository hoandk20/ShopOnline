package com.example.shoponline.Common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.shoponline.Model.Image;
import com.example.shoponline.Model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Base64;

public class ImageSupport {
    API api = new API();
    public String getLinkImageById(String Imageid){
        String Api = String.format(api.GetImageById,Imageid);
        String content = api.GetStringFromApi(Api);

        return content;

    }
    public ArrayList<String> getListBase64ImageProduct(){
        String Api = String.format(api.GetListBase64ImageProduct);
        String content = api.GetStringFromApi(Api);
        ArrayList<String> listBase64 = new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(content);
            JSONArray jsonArray = obj.getJSONArray("Image");
            if (jsonArray != null) {

                for (int i=0;i<jsonArray.length();i++){
                    Product p = new Product();
                    JSONObject o = new JSONObject();
                    o = (JSONObject)jsonArray.getJSONObject(i);


                    listBase64.add(o.getString("Data"));

                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

      return listBase64;
    }

    public Bitmap getBitMapImagebyId(String ImageId){
        String base64String = getLinkImageById(ImageId);
        byte[] decodedString = Base64.getDecoder().decode(base64String);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }
    public ArrayList<Bitmap> getListBitMapImageProduct(){
        ArrayList<String> listS = getListBase64ImageProduct();
        ArrayList<Bitmap> listBitmap =new ArrayList<>();
        for (String i:listS
             ) {
            byte[] decodedString = Base64.getDecoder().decode(i);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            listBitmap.add(decodedByte);
        }


        return listBitmap;
    }
    public ArrayList<Image> getListImageBase64(){
        ArrayList<Image> list = new ArrayList<>();

        String ApiLogin = String.format(api.GetListImageBase64);
        String content = api.GetStringFromApi(ApiLogin);
        try {
            JSONObject obj = new JSONObject(content);
            JSONArray jsonArray = obj.getJSONArray("ListImage");
            if (jsonArray != null) {

                for (int i=0;i<jsonArray.length();i++){
                    Image p = new Image();
                    JSONObject o = new JSONObject();
                    o = (JSONObject)jsonArray.getJSONObject(i);
                    p.setImageId(o.getLong("Id"));
                    p.setImageUrl(o.getString("Data"));
                    list.add(p);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

}
