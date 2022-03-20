package com.example.shoponline.View.Fragment.Adapter;

import static com.example.shoponline.View.Fragment.CartFragment.updateTotalAmount;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.shoponline.Common.ImageSupport;
import com.example.shoponline.Common.MyRoomDatabase;
import com.example.shoponline.Controller.Dao.ImageDao;
import com.example.shoponline.Model.Cart;
import com.example.shoponline.Model.Image;
import com.example.shoponline.Model.Product;
import com.example.shoponline.R;
import com.example.shoponline.View.Fragment.CartFragment;

import java.util.ArrayList;

public class ListProductCartAdapter extends RecyclerView.Adapter<ListProductCartAdapter.MyViewHolder> {

    Context context;
    ArrayList<Cart> list;
    MyRoomDatabase myRoomDatabase;
    static ArrayList<Cart> listCheckBox = new ArrayList<>();

    public static ArrayList<Cart> getListCheckBox() {
        return listCheckBox;
    }

    public ListProductCartAdapter(Context context, ArrayList<Cart> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ListProductCartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_cart, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListProductCartAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Cart cart = list.get(position);

        //detail product
        myRoomDatabase = Room.databaseBuilder(context, MyRoomDatabase.class, "mydatabase.db")
                .allowMainThreadQueries().fallbackToDestructiveMigration()
                .build();

        ImageDao imageDao = myRoomDatabase.createImageDao();
        Image image = imageDao.loadImageById(Long.valueOf(cart.getImageId()));
        ImageSupport imageSupport = new ImageSupport();
        Bitmap btmImage = imageSupport.getBitMapImagebyId(image.getImageUrl());

        holder.ivProduct.setImageBitmap(btmImage);
        holder.tvNameProduct.setText(list.get(position).getProductName());
        holder.tvPriceProduct.setText(list.get(position).getUnitPrice() + "$");
        holder.tvQuantityProduct.setText(list.get(position).getQuantity()+"");

        int quantity = Integer.parseInt((String) holder.tvQuantityProduct.getText());
        if(quantity==1){
            holder.btnMinus.setVisibility(View.INVISIBLE);
        }

        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt((String) holder.tvQuantityProduct.getText());
                if(quantity==2){ holder.btnMinus.setVisibility(View.INVISIBLE);}
                quantity--;

                // thiếu trừ quantity trong controller
                cart.setQuantity(quantity);

                holder.tvQuantityProduct.setText(quantity + "");
                updateTotalAmount(listCheckBox);
            }
        });
        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt((String) holder.tvQuantityProduct.getText());
                if(quantity==1){
                    holder.btnMinus.setVisibility(View.VISIBLE);
                }
                quantity++;

                // thiếu cộng quantity trong controller
                cart.setQuantity(quantity);

                holder.tvQuantityProduct.setText(quantity + "");
                updateTotalAmount(listCheckBox);
            }
        });

        holder.cbkProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list != null && list.size() > 0) {
                    if (holder.cbkProduct.isChecked()) {
                        listCheckBox.add(list.get(position));
                    } else {
                        listCheckBox.remove(list.get(position));
                    }
                    updateTotalAmount(listCheckBox);
                }
            }
        });
    }


    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProduct;
        TextView tvNameProduct, tvQuantityProduct, tvPriceProduct, tvTotalAmount;
        Button btnMinus, btnPlus;
        CheckBox cbkProduct;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProduct = itemView.findViewById(R.id.ivProduct);
            tvNameProduct = itemView.findViewById(R.id.tvNameProduct);
            tvQuantityProduct = itemView.findViewById(R.id.tvQuantityProduct);
            tvPriceProduct = itemView.findViewById(R.id.tvPriceProduct);
            tvTotalAmount = itemView.findViewById(R.id.tvTotalAmount);
            btnMinus = itemView.findViewById(R.id.btnMinus);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            cbkProduct = itemView.findViewById(R.id.cbkProduct);
        }
    }
}
