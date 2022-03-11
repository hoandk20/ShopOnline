package com.example.shoponline.View.Fragment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoponline.Model.Product;
import com.example.shoponline.R;
import com.example.shoponline.View.DetailActivity;

import java.util.ArrayList;

public class ListProductHomeAdapter extends RecyclerView.Adapter<ListProductHomeAdapter.MyViewHolder> {

    Context context;
    ArrayList<Product> list;

    public ListProductHomeAdapter(Context context, ArrayList<Product> list) {
        this.context = context;
        this.list = list;
    }

    public void updateData(ArrayList<Product> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_home,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product product = list.get(position);

        holder.imageView.setImageResource(R.mipmap.ic_launcher);
        holder.tvName.setText(list.get(position).getName());
        holder.tvPrice.setText(list.get(position).getPrice()+"$");
        holder.tvQuantity.setText(list.get(position).getQuantity());

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

    public static class MyViewHolder extends RecyclerView.ViewHolder{


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
