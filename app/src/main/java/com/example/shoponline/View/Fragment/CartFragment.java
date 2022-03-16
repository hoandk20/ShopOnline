package com.example.shoponline.View.Fragment;

import static com.example.shoponline.R.id.cbkProduct;
import static com.example.shoponline.R.id.view;
import static com.example.shoponline.View.Fragment.Adapter.ListProductCartAdapter.getListCheckBox;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.shoponline.Controller.ProductController;
import com.example.shoponline.Model.Product;
import com.example.shoponline.R;
import com.example.shoponline.View.Fragment.Adapter.ListProductCartAdapter;

import org.chromium.base.Callback;

import java.util.ArrayList;

public class CartFragment extends Fragment{

    private static ArrayList<Product> products;
    private ProductController productController;
    private static ListProductCartAdapter listProductCartAdapter;
    private static RecyclerView rvProducts;
    static TextView tvTotalAmount, tvStatusCart;
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
        tvStatusCart = view.findViewById(R.id.tvStatusCart);

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


    public void deleteProductCart(ArrayList<Product> listCheckBox){
        for (Product product: listCheckBox) {
            products.remove(product);
        }
        listCheckBox.clear();
        loadData();
    }

    public static void updateTotalAmount(ArrayList<Product> listCheckBox){
        double total = 0;
        for (Product product: listCheckBox) {
            total = total + Double.parseDouble(product.getQuantity())*Double.parseDouble(product.getPrice());
        }
        tvTotalAmount.setText(total+"$");
    }

    public void loadData(){
        // RecyclerView visible
        if(products.size()>0){
            tvStatusCart.setVisibility(View.INVISIBLE);
            btnBackHome.setVisibility(View.INVISIBLE);
        }else {
            rvProducts.setVisibility(View.INVISIBLE);
        }

        // total amount
        updateTotalAmount(getListCheckBox());

        //set adapter
        listProductCartAdapter = new ListProductCartAdapter(getContext(), products);
        rvProducts.setAdapter(listProductCartAdapter);
    }



    private void loadProduct() {
        productController = new ProductController();
        //products cart demo
        //getProductCart
        products = productController.GetAllProducts();
    }

}