package com.example.shoponline.View.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoponline.Controller.CategoryController;
import com.example.shoponline.Controller.ProductController;
import com.example.shoponline.Model.Product;
import com.example.shoponline.R;
import com.example.shoponline.View.Fragment.Adapter.ListProductHomeAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private ImageView imageView;
    private TextView tvName, tvPrice, tvQuantity;
    private ArrayList<Product> products;
    private ListProductHomeAdapter listProductHomeAdapter;
    private RecyclerView rvProducts;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvProducts = view.findViewById(R.id.rvListProduct);
        initAction();

        listProductHomeAdapter = new ListProductHomeAdapter(getContext(), products);
        rvProducts.setAdapter(listProductHomeAdapter);
    }

    private void initAction() {
        addProdcut();
    }

    private void addProdcut() {
        products = new ArrayList<Product>();
        ProductController productController = new ProductController();
        products = productController.GetAllProducts();


        //test API
        CategoryController categoryController = new CategoryController();
        categoryController.GetAllCategory();
        productController.GetProductsByCategoryId(1);

    }
}