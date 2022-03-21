package com.example.shoponline.View.Fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoponline.Controller.BillController;
import com.example.shoponline.Controller.CartController;
import com.example.shoponline.Controller.ProductController;
import com.example.shoponline.Model.Bill;
import com.example.shoponline.Model.Cart;
import com.example.shoponline.R;
import com.example.shoponline.View.Fragment.Adapter.ListProductCartAdapter;
import com.example.shoponline.View.MainActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class CartFragment extends Fragment{

    private ArrayList<Cart> carts;
    private CartController cartController = new CartController();
    private static ListProductCartAdapter listProductCartAdapter;
    private static RecyclerView rvProducts;
    static TextView tvTotalAmount;
    private ConstraintLayout clTopCart;
    private Button btnBackHome, btnDeleteProductCart, btnPayment;

    public CartFragment() {
    }

    private void initAction() {
        loadProductCart();
        loadData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvTotalAmount = view.findViewById(R.id.tvTotalAmount);
        btnBackHome = view.findViewById(R.id.btnBackHome);
        btnPayment = view.findViewById(R.id.btnPayment);
        btnDeleteProductCart = view.findViewById(R.id.btnDeleteProductCart);
        rvProducts = view.findViewById(R.id.rvProducts);
        clTopCart = view.findViewById(R.id.clTopCart);

        initAction();

        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.replayceFragment(new HomeFragment());
            }
        });

        btnDeleteProductCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteProductCart(listProductCartAdapter.getListCheckBox());
                loadData();
            }
        });

        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double totalPrice = totalAmount(listProductCartAdapter.getListCheckBox());
                BillController billController = new BillController();
                Calendar cal = Calendar.getInstance();
                for (Cart cart: listProductCartAdapter.getListCheckBox()) {
                    Bill bill = new Bill();
                    bill.setAccountId(cart.getAccountId());
                    bill.setProductId(cart.getProductId()+"");
                    bill.setImageId(cart.getImageId());
                    bill.setQuantity(cart.getQuantity());
                    bill.setTotalPrice(totalPrice);
                    bill.setNameProduct(cart.getProductName());
                    bill.setUnitPrice(cart.getUnitPrice());
                    billController.AddBill(bill);
                }
                deleteProductCart(listProductCartAdapter.getListCheckBox());

                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.replayceFragment(new ListPurchasedOrderFragment());
            }
        });
    }


    public void deleteProductCart(ArrayList<Cart> listCheckBox){
        for (Cart product: listCheckBox) {
            try {
                carts.remove(product);
                cartController.DeleteCartById(product.getId());
            }catch (Exception e){
                Log.d("Check Delete", e+"");
            }

        }
        listCheckBox.clear();
        listProductCartAdapter.setListCheckBox(listCheckBox);
    }

    public static double totalAmount(ArrayList<Cart> listCheckBox){
        double total = 0;
        for (Cart product: listCheckBox) {
            total = total + product.getQuantity()*product.getUnitPrice();
        }
        return total;
    }

    public static void updateTotalAmount(ArrayList<Cart> listCheckBox){
        tvTotalAmount.setText(totalAmount(listCheckBox)+"$");
    }

    void loadProductCart(){
        CartController cartController = new CartController();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);
        Long userid = sharedPreferences.getLong("UserId",0);
        carts = cartController.GetCart(userid+"");
    }

    public void loadData(){

        //  RecyclerView visible
        if(carts.size()>0){
            rvProducts.setVisibility(View.VISIBLE);
            clTopCart.setVisibility(View.INVISIBLE);

        }else {
            rvProducts.setVisibility(View.INVISIBLE);
            clTopCart.setVisibility(View.VISIBLE);
        }

        //set adapter
        listProductCartAdapter = new ListProductCartAdapter(getContext(), carts);
        rvProducts.setAdapter(listProductCartAdapter);

        // total amount
        updateTotalAmount(listProductCartAdapter.getListCheckBox());
    }
}