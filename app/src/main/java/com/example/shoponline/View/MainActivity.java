package com.example.shoponline.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoponline.Common.API;
import com.example.shoponline.Common.MyRoomDatabase;
import com.example.shoponline.Controller.CategoryController;
import com.example.shoponline.Controller.Dao.CatelogyDao;
import com.example.shoponline.Controller.Dao.ImageDao;
import com.example.shoponline.Controller.Dao.ProductDao;
import com.example.shoponline.Controller.LoginController;
import com.example.shoponline.Controller.ProductController;
import com.example.shoponline.Model.Category;
import com.example.shoponline.Model.Product;
import com.example.shoponline.R;
import com.example.shoponline.View.Fragment.CartFragment;
import com.example.shoponline.View.Fragment.HomeFragment;
import com.example.shoponline.View.Fragment.LoginFragment;
import com.example.shoponline.View.Fragment.MenuFragment;
import com.example.shoponline.View.Fragment.NotificationFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private MyRoomDatabase myRoomDatabase;

    boolean isLogin = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        setContentView(R.layout.activity_main);
        myRoomDatabase = Room.databaseBuilder(MainActivity.this, MyRoomDatabase.class, "mydatabase.db")
                .allowMainThreadQueries().fallbackToDestructiveMigration()
                .build();
        loadData();
        checkLogin();


            replayceFragment(new HomeFragment());
            bottomNavigationView = findViewById(R.id.bottomNavigationView);
            initAction();
            action();


    }
    void checkLogin(){
        SharedPreferences pref = getSharedPreferences("User",MODE_PRIVATE);
        String Username = pref.getString("Username","");
        isLogin = (Username=="")?false:true;
    }

    private void loadData() {
        loadCategory();
        loadProduct();
        loadImage();
    }

    private void loadImage() {


    }

    private void loadProduct() {
        ProductDao productDao = myRoomDatabase.createProductDao();
        ProductController productController = new ProductController();
        List<Product> products = productController.GetAllProducts();
        productDao.insertProduct(products);
        Log.d("Check add product", "" + products.size());
    }

    private void loadCategory() {
        CatelogyDao catelogyDao = myRoomDatabase.createCategoryDao();
        CategoryController categoryController = new CategoryController();
        List<Category> categories = categoryController.GetAllCategory();
        catelogyDao.insertAllCatelogy(categories);
        Log.d("Check add catelogy", "" + categories.size());
    }

    private void action() {
    }

    private void initAction() {
        bottomNavigationViewAction();
    }

    private void bottomNavigationViewAction() {
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                        replayceFragment(new HomeFragment());
                    break;
                case R.id.cart:

                    if(isLogin){   replayceFragment(new CartFragment());
                    }else{
                        replayceFragment(new LoginFragment());
                    }
                    break;
                case R.id.notification:
                    if(isLogin){  replayceFragment(new NotificationFragment());
                    }else{
                        replayceFragment(new LoginFragment());
                    }

                    break;
                case R.id.menu:
                    if(isLogin){  replayceFragment(new MenuFragment());
                    }else{
                        replayceFragment(new LoginFragment());
                    }

                    break;
            }
            return true;
        });
    }

    public void replayceFragment(Fragment fragment){
        checkLogin();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}