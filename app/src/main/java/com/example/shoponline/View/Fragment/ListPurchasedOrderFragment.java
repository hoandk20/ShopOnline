package com.example.shoponline.View.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.example.shoponline.Common.MyRoomDatabase;
import com.example.shoponline.Controller.BillController;
import com.example.shoponline.Controller.Dao.BillDao;
import com.example.shoponline.Controller.Dao.ProductDao;
import com.example.shoponline.Controller.ProductController;
import com.example.shoponline.Model.Bill;
import com.example.shoponline.Model.Category;
import com.example.shoponline.Model.Product;
import com.example.shoponline.Model.SlideShow;
import com.example.shoponline.R;
import com.example.shoponline.View.Fragment.Adapter.ListProductHomeAdapter;
import com.example.shoponline.View.Fragment.Adapter.ListPurchaseOrderAdapter;
import com.google.android.material.tabs.TabLayout;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListPurchasedOrderFragment extends Fragment {

    private ArrayList<Bill> listBill;
    private ListPurchaseOrderAdapter listPurchaseOrderAdapter;
    private MyRoomDatabase myRoomDatabase;

    public ListPurchasedOrderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_purchased_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myRoomDatabase = Room.databaseBuilder(getContext(), MyRoomDatabase.class, "mydatabase.db")
                .allowMainThreadQueries()
                .build();
        RecyclerView rvBill = view.findViewById(R.id.rvListProduct);
        rvBill.setLayoutManager(new LinearLayoutManager(view.getContext()));
        listBill = new ArrayList<>();
        BillController billController = new BillController();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);
        Long userId = sharedPreferences.getLong("UserId",0);
        listBill = billController.GetBill(userId+"");

        listPurchaseOrderAdapter = new ListPurchaseOrderAdapter(view.getContext(),listBill);
        rvBill.setAdapter(listPurchaseOrderAdapter);
    }

    /*private void getAllBillByIdAccount() {
        BillDao billDao = myRoomDatabase.createBillDao();
        listBill = billDao.getAllBillByIdAccount();
    }*/
}