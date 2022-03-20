package com.example.shoponline.View.Fragment;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.shoponline.Controller.ChatController;
import com.example.shoponline.Model.Chat;
import com.example.shoponline.R;
import com.example.shoponline.View.Fragment.Adapter.ListChatAdapter;

import java.util.ArrayList;

public class NotificationFragment extends Fragment {

    ArrayList<Chat> listChat = new ArrayList<>();
    RecyclerView rvListChat;
    ListChatAdapter listChatAdapter;
    Button send;
    EditText etSend;


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
        send = view.findViewById(R.id.buttonSend);
        etSend = view.findViewById(R.id.etSendMessage);

        initAction();
        listChatAdapter = new ListChatAdapter(getContext(), listChat);
        rvListChat.setAdapter(listChatAdapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = etSend.getText().toString();
                AddMessageSender(text);
                final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
                AddMessageBot(text);
                etSend.setText("");
                rvListChat.scrollToPosition(listChat.size() - 1);
            }
        });

    }

    void AddMessageSender(String text) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("Username", "");
        Chat c = new Chat();
        c.setContent(text);
        c.setSenderId("@" + username);
        listChat.add(c);
        listChatAdapter.updateData(listChat);
    }

    Thread thread;

    public void AddMessageBot(String text) {

        ChatController chatController = new ChatController();
        Chat c = chatController.GetBotMessage(text);
        listChat.add(c);
        listChatAdapter.updateData(listChat);

    }

    void loadData() {
        listChat.add(new Chat("@Bot", "xin chào bạn!\nChat 'id(id sản phẩm)' để được tư vấn\n" +
                "Chat 5 mua(số lượng)id(id sản phẩm) để mua sản phầm"));

    }


}