package com.example.shoponline.View.Fragment;

import static com.example.shoponline.View.Fragment.Adapter.ListProductCartAdapter.getListCheckBox;

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

import com.example.shoponline.Controller.CartController;
import com.example.shoponline.Controller.ProductController;
import com.example.shoponline.Model.Cart;
import com.example.shoponline.R;
import com.example.shoponline.View.Fragment.Adapter.ListProductCartAdapter;

import java.util.ArrayList;

public class CartFragment extends Fragment{

    private static ArrayList<Cart> carts;
    private ProductController productController;
    private static ListProductCartAdapter listProductCartAdapter;
    private static RecyclerView rvProducts;
    static TextView tvTotalAmount, tvStatusCart;
    private ConstraintLayout clTopCart;
    private Button btnBackHome, btnDeleteProductCart, btnPayment;

    public CartFragment() {
    }

    private void initAction() {
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
        rvProducts = view.findViewById(R.id.rvProducts);
        btnBackHome = view.findViewById(R.id.btnBackHome);
        btnPayment = view.findViewById(R.id.btnPayment);
        btnDeleteProductCart = view.findViewById(R.id.btnDeleteProductCart);
//        tvStatusCart = view.findViewById(R.id.tvStatusCart);

        loadProduct();

        initAction();

        btnDeleteProductCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteProductCart(getListCheckBox());
            }
        });

        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteProductCart(getListCheckBox());

                /*
                add vào Bill trong controller
                 */


            }
        });
    }


    public void deleteProductCart(ArrayList<Cart> listCheckBox){
        for (Cart product: listCheckBox) {
            carts.remove(product);
        }
        listCheckBox.clear();
        loadData();
    }

    public static void updateTotalAmount(ArrayList<Cart> listCheckBox){
        double total = 0;
        for (Cart product: listCheckBox) {
            total = total + product.getQuantity()*product.getUnitPrice();
        }
        tvTotalAmount.setText(total+"$");
    }

    public void loadData(){
        // RecyclerView visible
//        if(carts.size()>0){
//            rvProducts.setVisibility(View.VISIBLE);
//            clTopCart.setVisibility(View.INVISIBLE);
//        }else {
//            rvProducts.setVisibility(View.INVISIBLE);
//            clTopCart.setVisibility(View.VISIBLE);
//        }

        // total amount
        updateTotalAmount(getListCheckBox());

        //set adapter
        listProductCartAdapter = new ListProductCartAdapter(getContext(), carts);
        rvProducts.setAdapter(listProductCartAdapter);
    }



    private void loadProduct() {
        CartController cartController = new CartController();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);
        Long userid = sharedPreferences.getLong("UserId",0);
        carts = cartController.GetCart(userid+"");
        Log.d("Check cart", ""+carts.size());
    }
}