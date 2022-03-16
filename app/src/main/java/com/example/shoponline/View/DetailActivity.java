package com.example.shoponline.View;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoponline.Common.ImageSupport;
import com.example.shoponline.Model.Product;
import com.example.shoponline.R;

public class DetailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView tvName ,tvPrice ,tvQuantity ,tvCategory, tvAmount, tvTotalPrice;
    ImageButton ibPrevious, ibNext;
    int amount = 0;
    double totalPrice = 0;

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

        initAction();
    }

    private void initAction() {
        loadDetail();
        previousAction();
        nextAction();
    }

    private void loadDetail() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }
        Product product = (Product) bundle.get("object_product");
        ImageSupport imageSupport = new ImageSupport();
        Bitmap btmImage = imageSupport.getBitMapImagebyId(product.getImageId());
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
}