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
import com.example.shoponline.Model.Image;
import com.example.shoponline.Model.Product;
import com.example.shoponline.R;
import com.example.shoponline.View.DetailActivity;
import com.example.shoponline.View.MainActivity;

import java.util.ArrayList;

public class ListProductHomeAdapter extends RecyclerView.Adapter<ListProductHomeAdapter.MyViewHolder> {

    Context context;
    ArrayList<Product> list;
    MyRoomDatabase myRoomDatabase;

    public ListProductHomeAdapter(Context context, ArrayList<Product> list) {
        this.context = context;
        this.list = list;
    }

    public void updateData(ArrayList<Product> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_home, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product product = list.get(position);
        myRoomDatabase = Room.databaseBuilder(context, MyRoomDatabase.class, "mydatabase.db")
                .allowMainThreadQueries().fallbackToDestructiveMigration()
                .build();
        ImageDao imageDao = myRoomDatabase.createImageDao();
        Image image = imageDao.loadImageById(Long.valueOf(list.get(position).getImageId()));

        ImageSupport imageSupport = new ImageSupport();
        Bitmap btmImage = imageSupport.getBitMapImagebyId(image.getImageUrl());
        holder.imageView.setImageBitmap(btmImage);
        holder.tvName.setText(list.get(position).getProductName());
        holder.tvPrice.setText(list.get(position).getProductPrice() + "$");
        holder.tvQuantity.setText(list.get(position).getProductQuantity());
        holder.rlProdcut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToDetailProduct(product);
            }
        });
    }

    private void goToDetailProduct(Product product) {
        Intent intent = new Intent(context, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_product", product);
        intent.putExtras(bundle);
        context.startActivity(intent);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        ImageView imageView;
        TextView tvName, tvPrice, tvQuantity, tvCategory;
        RelativeLayout rlProdcut;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivProduct);
            tvName = itemView.findViewById(R.id.tvNameProduct);
            tvPrice = itemView.findViewById(R.id.tvPriceProduct);
            tvQuantity = itemView.findViewById(R.id.tvQuantityProduct);
            rlProdcut = itemView.findViewById(R.id.rlProduct);
            tvCategory = itemView.findViewById(R.id.tvCategory);
        }
    }
}
