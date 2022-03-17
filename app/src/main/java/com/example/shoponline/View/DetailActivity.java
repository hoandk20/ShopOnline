package com.example.shoponline.View;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoponline.Common.ImageSupport;
import com.example.shoponline.Model.Product;
import com.example.shoponline.R;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView tvName ,tvPrice ,tvQuantity ,tvCategory, tvAmount, tvTotalPrice;
    ImageButton ibPrevious, ibNext;
    int amount = 0;
    double totalPrice = 0;
    Button btnAddToCart;

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
        btnAddToCart = findViewById(R.id.btnAddToCart);

        initAction();
    }

    private void initAction() {
        loadDetail();
        previousAction();
        nextAction();
        addToCart();
    }

    private void addToCart(){
        // Sau sửa thành listProductCart get từ Controller
        ArrayList<Product> products = new ArrayList<>();
        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }
        Product product = (Product) bundle.get("object_product");
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add
                products.add(product);

                Toast toast=Toast.makeText(DetailActivity.this, "Thêm sản phẩm thành công",   Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            }
        });
    }

    private void loadDetail() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }
        Product product = (Product) bundle.get("object_product");
        ImageSupport imageSupport = new ImageSupport();
        Bitmap btmImage = imageSupport.getBitMapImageById(Long.parseLong(product.getImageId()));
        imageView.setImageBitmap(btmImage);
        tvName.setText(product.getName());
        tvPrice.setText(product.getPrice());
        tvCategory.setText(product.getCategoryId());
        tvQuantity.setText(product.getQuantity());
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