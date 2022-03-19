package com.example.shoponline.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoponline.Common.ImageSupport;
import com.example.shoponline.Common.MyRoomDatabase;
import com.example.shoponline.Controller.CartController;
import com.example.shoponline.Controller.Dao.ImageDao;
import com.example.shoponline.Model.Cart;
import com.example.shoponline.Model.Image;
import com.example.shoponline.Model.Product;
import com.example.shoponline.R;

public class DetailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView tvName ,tvPrice ,tvQuantity ,tvCategory, tvAmount, tvTotalPrice;
    ImageButton ibPrevious, ibNext;
    int amount = 0;
    double totalPrice = 0;
    Button add;
    Product product;
    private MyRoomDatabase myRoomDatabase;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imageView = findViewById(R.id.ivProduct);
        tvName = findViewById(R.id.tvNameProduct);
        tvPrice = findViewById(R.id.tvPriceProduct);
        tvCategory = findViewById(R.id.tvCategory);
        tvQuantity = findViewById(R.id.tvQuantityProduct);
        ibPrevious = findViewById(R.id.ibPrevious);
        ibNext = findViewById(R.id.ibNext);
        tvAmount = findViewById(R.id.tvAmount);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        tvAmount.setText(Integer.toString(amount));
        tvTotalPrice.setText(Double.toString(totalPrice));
        add = findViewById(R.id.btnAddToCart);
        myRoomDatabase = Room.databaseBuilder(DetailActivity.this, MyRoomDatabase.class, "mydatabase.db")
                .allowMainThreadQueries().fallbackToDestructiveMigration()
                .build();


        initAction();
    }

    private void initAction() {
        loadDetail();
        previousAction();
        nextAction();
        AddToCart();
    }

    private void loadDetail() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }
        product = (Product) bundle.get("object_product");
        ImageDao imageDao = myRoomDatabase.createImageDao();
        Image image = imageDao.loadImageById(Long.valueOf(product.getImageId()));
        ImageSupport imageSupport = new ImageSupport();
        Bitmap btmImage = imageSupport.getBitMapImagebyId(image.getImageUrl());
        imageView.setImageBitmap(btmImage);
        tvName.setText(product.getProductName());
        tvPrice.setText(product.getProductPrice());
        tvCategory.setText(product.getCatelogyOwnerId());
        tvQuantity.setText(product.getProductQuantity());
    }

    private void nextAction() {
        ibNext.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt(tvQuantity.getText().toString());
                double price = Double.parseDouble(tvPrice.getText().toString());
                if (amount < quantity){
                    amount++;
                    tvAmount.setText(Integer.toString(amount));
                    totalPrice =  amount * price;
                    tvTotalPrice.setText(Double.toString(totalPrice));
                }
                else {
                    amount = quantity;
                    tvAmount.setText(Integer.toString(amount));
                    totalPrice =  amount * price;
                    tvTotalPrice.setText(Double.toString(totalPrice));
                }
            }
        });
    }

    private void previousAction() {
        ibPrevious.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt(tvQuantity.getText().toString());
                double price = Double.parseDouble(tvPrice.getText().toString());
                if (amount > 0){
                    amount--;
                    tvAmount.setText(Integer.toString(amount));
                    totalPrice =  amount * price;
                    tvTotalPrice.setText(Double.toString(totalPrice));
                }
                else {
                    amount = 0;
                    tvAmount.setText(Integer.toString(amount));
                    totalPrice =  amount * price;
                    tvTotalPrice.setText(Double.toString(totalPrice));
                }
            }
        });
    }
    private void AddToCart(){
        add.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                Cart c = new Cart();
                c.setProductName(tvName.getText().toString());
                c.setQuantity(Integer.parseInt(tvQuantity.getText().toString()));
                if(c.getQuantity()<=0){
                    return;
                }
                c.setImageId(product.getImageId());
                c.setUnitPrice(Double.parseDouble(product.getProductPrice()));
                c.setProductId(Long.parseLong(product.getProductId()));
                c.setTotalPrice(Integer.parseInt(tvQuantity.getText().toString())*Double.parseDouble(product.getProductPrice()));
                CartController cartController = new CartController();
                SharedPreferences sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
                Long userid = sharedPreferences.getLong("UserId",0);
                c.setAccountId(userid);

                cartController.AddToCart(c);
                Toast toast = Toast.makeText(view.getContext(),"Sucessfull",Toast.LENGTH_LONG);

                toast.show();

            }
        });
    }
}