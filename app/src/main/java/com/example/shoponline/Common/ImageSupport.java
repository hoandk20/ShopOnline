package com.example.shoponline.Common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Base64;

public class ImageSupport {
    API api = new API();
    public String getLinkImageById(long imageId){
        String Api = String.format(api.GetImageById,imageId);
        String content = api.GetStringFromApi(Api);

        return content;

    }

    public Bitmap getBitMapImageById(long imageId){
        String base64String = getLinkImageById(imageId);
        byte[] decodedString = Base64.getDecoder().decode(base64String);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }
}
