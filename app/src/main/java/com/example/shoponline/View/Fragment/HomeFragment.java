package com.example.shoponline.View.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.shoponline.Controller.CategoryController;
import com.example.shoponline.Controller.ProductController;
import com.example.shoponline.Controller.SlideShowController;
import com.example.shoponline.Model.Category;
import com.example.shoponline.Model.Product;
import com.example.shoponline.Model.SlideShow;
import com.example.shoponline.R;
import com.example.shoponline.View.Fragment.Adapter.ListProductHomeAdapter;
import com.example.shoponline.View.Fragment.Adapter.The_Slide_items_Pager_Adapter;
import com.example.shoponline.View.MainActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    private ArrayList<Product> products;
    private ArrayList<Category> categories;
    private Spinner spinnerCatelogy;
    private ListProductHomeAdapter listProductHomeAdapter;
    private ProductController productController;
    private List<SlideShow> listSlideShow;
    private ViewPager page;
    private TabLayout tabLayout;

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

        RecyclerView rvProducts = view.findViewById(R.id.rvListProduct);
        spinnerCatelogy = view.findViewById(R.id.spinnerCatalog);
        page = view.findViewById(R.id.my_pager) ;
        tabLayout = view.findViewById(R.id.my_tablayout);
        initAction();
        listProductHomeAdapter = new ListProductHomeAdapter(getContext(), products);
        rvProducts.setAdapter(listProductHomeAdapter);
    }

    private void initAction() {
        addSlideShow();
        addCatelogy();
        addProdcut();
    }

    private void addSlideShow() {
        SlideShowController slideShowController = new SlideShowController();
        listSlideShow = slideShowController.GetSlideShow();
        The_Slide_items_Pager_Adapter itemsPager_adapter = new The_Slide_items_Pager_Adapter(getContext(), listSlideShow);
        page.setAdapter(itemsPager_adapter);

        // The_slide_timer
        java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new The_slide_timer(),2000,3000);
        tabLayout.setupWithViewPager(page,true);
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
                if (id != 0) {
                    products = productController.GetProductsByCategoryId(id);
                    listProductHomeAdapter.updateData(products);
                    Log.d("Check update", "Update success");
                } else {
                    products = productController.GetAllProducts();
                    listProductHomeAdapter.updateData(products);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private class The_slide_timer extends TimerTask {
        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (page.getCurrentItem()< listSlideShow.size()-1) {
                        page.setCurrentItem(page.getCurrentItem()+1);
                    }
                    else
                        page.setCurrentItem(0);
                }
            });
        }
    }
}