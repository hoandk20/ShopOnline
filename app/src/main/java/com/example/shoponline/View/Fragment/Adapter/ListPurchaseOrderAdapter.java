package com.example.shoponline.View.Fragment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.shoponline.Common.ImageSupport;
import com.example.shoponline.Common.MyRoomDatabase;
import com.example.shoponline.Controller.Dao.ImageDao;
import com.example.shoponline.Model.Bill;
import com.example.shoponline.Model.Image;
import com.example.shoponline.Model.Product;
import com.example.shoponline.R;
import com.example.shoponline.View.DetailActivity;

import java.util.ArrayList;

public class ListPurchaseOrderAdapter extends RecyclerView.Adapter<ListPurchaseOrderAdapter.MyViewHolder> {

    Context context;
    ArrayList<Bill> list;
    MyRoomDatabase myRoomDatabase;
    public ListPurchaseOrderAdapter(Context context, ArrayList<Bill> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_purchased, parent, false);
        return new ListPurchaseOrderAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        /*ImageSupport imageSupport = new ImageSupport();
        Bitmap btmImage = imageSupport.getBitMapImagebyId(list.get(position).getImageId());*/

        myRoomDatabase = Room.databaseBuilder(context, MyRoomDatabase.class, "mydatabase.db")
                .allowMainThreadQueries().fallbackToDestructiveMigration()
                .build();
        ImageDao imageDao = myRoomDatabase.createImageDao();
        Image image = imageDao.loadImageById(Long.valueOf(list.get(position).getImageId()));
        ImageSupport imageSupport = new ImageSupport();
        Bitmap btmImage = imageSupport.getBitMapImagebyId(image.getImageUrl());
        holder.imageOrderPurchased.setImageBitmap(btmImage);

        holder.nameProductOrderPurchased.setText(list.get(position).getNameProduct());
        holder.typeOrderPurchased.setText(list.get(position).getType());
        holder.priceAllOrderPurchased.setText(list.get(position).getTotalPrice() + "$");
        holder.numberTotalOrderPurchased.setText(list.get(position).getTotalQuantity()+"");
        holder.numberDetailOrderPurchased.setText(list.get(position).getQuantity()+"");
        holder.priceDetailOrderPurchased.setText(list.get(position).getUnitPrice() +"$");

        holder.DetailViewOrderPurchased.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageOrderPurchased;
        TextView nameProductOrderPurchased,priceAllOrderPurchased,numberTotalOrderPurchased, typeOrderPurchased, numberDetailOrderPurchased, priceDetailOrderPurchased, DetailViewOrderPurchased;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageOrderPurchased = itemView.findViewById(R.id.imageOrderPurchased);
            nameProductOrderPurchased = itemView.findViewById(R.id.nameProductOrderPurchased);
            typeOrderPurchased = itemView.findViewById(R.id.typeOrderPurchased);
            numberDetailOrderPurchased = itemView.findViewById(R.id.numberDetailOrderPurchased);
            DetailViewOrderPurchased = itemView.findViewById(R.id.DetailViewOrderPurchased);
            priceDetailOrderPurchased = itemView.findViewById(R.id.priceDetailOrderPurchased);
            numberTotalOrderPurchased = itemView.findViewById(R.id.numberTotalOrderPurchased);
            priceAllOrderPurchased = itemView.findViewById(R.id.priceAllOrderPurchased);
        }
    }
}


