package com.example.shoponline.View.Fragment;

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
        listBill.clear();
        listBill = new ArrayList<>();
        //getAllBillByIdAccount();
        listBill.add(new Bill("b01", 01, LocalDate.parse("18-12-2000", DateTimeFormatter.ofPattern("dd-MM-yyyy")),"p01",10,10,2000,20000,"abc","Bim Bim","a"));
        listBill.add(new Bill("b02", 01, LocalDate.parse("18-12-2000", DateTimeFormatter.ofPattern("dd-MM-yyyy")),"p02",10,10,2000,20000,"abc","Bom","b"));
        listBill.add(new Bill("b03", 01, LocalDate.parse("18-12-2000", DateTimeFormatter.ofPattern("dd-MM-yyyy")),"p03",10,10,2000,20000,"abc","Bao","c"));
        listBill.add(new Bill("b04", 01, LocalDate.parse("18-12-2000", DateTimeFormatter.ofPattern("dd-MM-yyyy")),"p04",10,10,2000,20000,"abc","Min","d"));
        listBill.add(new Bill("b05", 01, LocalDate.parse("18-12-2000", DateTimeFormatter.ofPattern("dd-MM-yyyy")),"p05",10,10,2000,20000,"abc","Hoa","e"));

        listPurchaseOrderAdapter = new ListPurchaseOrderAdapter(view.getContext(),listBill);
        rvBill.setAdapter(listPurchaseOrderAdapter);
    }

    /*private void getAllBillByIdAccount() {
        BillDao billDao = myRoomDatabase.createBillDao();
        listBill = billDao.getAllBillByIdAccount();
    }*/
}