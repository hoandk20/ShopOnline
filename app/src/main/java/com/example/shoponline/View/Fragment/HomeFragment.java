package com.example.shoponline.View.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoponline.Controller.CategoryController;
import com.example.shoponline.Controller.ProductController;
import com.example.shoponline.Model.Category;
import com.example.shoponline.Model.Product;
import com.example.shoponline.R;
import com.example.shoponline.View.Fragment.Adapter.ListProductHomeAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private ImageView imageView;
    private TextView tvName, tvPrice, tvQuantity;
    private ArrayList<Product> products;
    private ArrayList<Category> categories;
    private Spinner spinnerCatelogy;
    private ListProductHomeAdapter listProductHomeAdapter;
    private RecyclerView rvProducts;
    private ProductController productController;

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
        spinnerCatelogy = view.findViewById(R.id.spinnerCatalog);
        initAction();
        listProductHomeAdapter = new ListProductHomeAdapter(getContext(), products);
        rvProducts.setAdapter(listProductHomeAdapter);
    }

    private void initAction() {
        addCatelogy();
        addProdcut();
    }

    private void addCatelogy() {
        CategoryController categoryController = new CategoryController();
        categories = categoryController.GetAllCategory();
        Log.d("Check list categories", "" + categories);
        categories.add(0, new Category("All"));
        ArrayAdapter<Category> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, categories);
        spinnerCatelogy.setAdapter(adapter);
    }

    private void addProdcut() {
        //test API
        productController = new ProductController();
        products = productController.GetAllProducts();

        spinnerCatelogy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String categoryName = spinnerCatelogy.getSelectedItem().toString();
                Toast.makeText(getContext(), "" + categoryName, Toast.LENGTH_SHORT).show();
                Category category = categories.get(i);
                int id = (int) category.getId();
                if (id != 0){
                    products = productController.GetProductsByCategoryId(id);
                    listProductHomeAdapter.updateData(products);
                    Log.d("Check update", "Update success");
                }
                else {
                    products = productController.GetAllProducts();
                    listProductHomeAdapter.updateData(products);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        productController.GetProductsByCategoryId(1);
    }
}