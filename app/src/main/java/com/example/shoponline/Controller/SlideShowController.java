package com.example.shoponline.Controller;

import com.example.shoponline.Model.SlideShow;
import com.example.shoponline.R;

import java.util.ArrayList;

public class SlideShowController {
    public ArrayList<SlideShow> GetSlideShow(){
        ArrayList<SlideShow> list = new ArrayList<>();
        list.add(new SlideShow(R.drawable.giam_gia_sach, "Title 1"));
        list.add(new SlideShow(R.drawable.kem_danh_rang, "Title 2"));
        list.add(new SlideShow(R.drawable.images, "Title 3"));
        list.add(new SlideShow(R.drawable.giam_mobile, "Title 4"));
        return list;
    }
}
