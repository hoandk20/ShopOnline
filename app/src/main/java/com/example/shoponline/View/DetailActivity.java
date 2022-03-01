package com.example.shoponline.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoponline.Model.Product;
import com.example.shoponline.R;

public class DetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView tvName;
    private TextView tvPrice;
    private TextView tvCategory;
    private TextView tvQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imageView = findViewById(R.id.ivProduct);
        tvName = findViewById(R.id.tvNameProduct);
        tvPrice = findViewById(R.id.tvPriceProduct);
        tvCategory = findViewById(R.id.tvCategory);
        tvQuantity = findViewById(R.id.tvQuantityProduct);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }
        Product product = (Product) bundle.get("object_product");
        imageView.setImageResource(R.mipmap.ic_launcher);
        tvName.setText(product.getName());
        tvPrice.setText(product.getPrice());
        tvCategory.setText(product.getCategoryId());
        tvQuantity.setText(product.getQuantity());
    }
}