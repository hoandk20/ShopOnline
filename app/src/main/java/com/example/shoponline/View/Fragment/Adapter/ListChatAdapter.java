package com.example.shoponline.View.Fragment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoponline.Model.Cart;
import com.example.shoponline.Model.Chat;
import com.example.shoponline.R;

import java.util.ArrayList;

public class ListChatAdapter extends RecyclerView.Adapter<ListChatAdapter.MyViewHolder>{

    Context context;
    ArrayList<Chat> list;

    public ListChatAdapter(Context context, ArrayList<Chat> list) {
        this.context = context;
        this.list = list;
    }
    public void updateData(ArrayList<Chat> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListChatAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
        return new ListChatAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListChatAdapter.MyViewHolder holder, int position) {
        holder.tvSenderId.setText(list.get(position).getSenderId());
        holder.tvContent.setText(list.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvSenderId, tvContent;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSenderId = itemView.findViewById(R.id.tvSenderId);
            tvContent = itemView.findViewById(R.id.tvContent);
        }
    }
}
