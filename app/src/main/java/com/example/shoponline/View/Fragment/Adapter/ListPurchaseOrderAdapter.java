package com.example.shoponline.View.Fragment.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.shoponline.Common.ImageSupport;
import com.example.shoponline.Common.MyRoomDatabase;
import com.example.shoponline.Controller.Dao.ImageDao;
import com.example.shoponline.Model.Bill;
import com.example.shoponline.Model.Image;
import com.example.shoponline.R;

import java.time.format.DateTimeFormatter;
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
        Bill bill = list.get(position);

        myRoomDatabase = Room.databaseBuilder(context, MyRoomDatabase.class, "mydatabase.db")
                .allowMainThreadQueries().fallbackToDestructiveMigration()
                .build();
        ImageDao imageDao = myRoomDatabase.createImageDao();
        Image image = imageDao.loadImageById(Long.valueOf(bill.getImageId()));
        ImageSupport imageSupport = new ImageSupport();
        Bitmap btmImage = imageSupport.getBitMapImagebyId(image.getImageUrl());

//        bill.getDateBuy().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        holder.imageOrderPurchased.setImageBitmap(btmImage);
        holder.nameProductOrderPurchased.setText(bill.getNameProduct());
//        holder.dateProduct.setText(bill.getDateBuy().format(DateTimeFormatter.ofPattern("dd-MMM-yy"))+"");
//        Log.d("check date", bill.getDateBuy().format(DateTimeFormatter.ofPattern("dd-MMM-yy"))+"");
        holder.quantityProduct.setText("x"+bill.getQuantity());
        holder.dateBuyProduct.setText(bill.getDateBuy()+"");
        if(bill.getDateBuy() == null){
            Log.d("check date buy", "Ok");
        }
        holder.priceProduct.setText(bill.getUnitPrice()+"");


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageOrderPurchased;
        TextView nameProductOrderPurchased,priceProduct,numberTotalOrderPurchased, quantityProduct, dateBuyProduct;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageOrderPurchased = itemView.findViewById(R.id.imageOrderPurchased);
            nameProductOrderPurchased = itemView.findViewById(R.id.nameProductOrderPurchased);
            priceProduct = itemView.findViewById(R.id.priceProductOrderPurchased);
            quantityProduct = itemView.findViewById(R.id.quantityProductOrderPurchased);
            dateBuyProduct = itemView.findViewById(R.id.dateProductOrderPurchased);
        }
    }
}


