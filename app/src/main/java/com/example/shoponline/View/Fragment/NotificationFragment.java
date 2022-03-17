package com.example.shoponline.View.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoponline.Model.Chat;
import com.example.shoponline.R;
import com.example.shoponline.View.Fragment.Adapter.ListChatAdapter;

import java.util.ArrayList;

public class NotificationFragment extends Fragment {

    ArrayList<Chat> listChat = new ArrayList<>();
    RecyclerView rvListChat;
    ListChatAdapter listChatAdapter;


    public NotificationFragment() {
    }

    private void initAction() {
        loadData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvListChat = view.findViewById(R.id.rvListChat);

        initAction();

        listChatAdapter = new ListChatAdapter(getContext(), listChat);
        rvListChat.setAdapter(listChatAdapter);

    }

    void loadData(){
        listChat.add(new Chat("Kiuer", "Alo"));
        listChat.add(new Chat("HoanDk", "Alo000"));
        listChat.add(new Chat("Kiuer", "Alo"));
        listChat.add(new Chat("HoanDk", "Aloo"));
    }
}