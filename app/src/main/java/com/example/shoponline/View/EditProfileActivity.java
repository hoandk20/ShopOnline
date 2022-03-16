package com.example.shoponline.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoponline.Controller.LoginController;
import com.example.shoponline.Model.Account;
import com.example.shoponline.R;

public class EditProfileActivity extends AppCompatActivity {

    private EditText editUserName,editAddress,editPhone;
    private Button btnSaveProfile;
    private TextView imageLeft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        editUserName = findViewById(R.id.editUserName);
        editAddress = findViewById(R.id.editAddress);
        editPhone = findViewById(R.id.editPhone);
        btnSaveProfile = findViewById(R.id.btnSaveEditProfile);
        imageLeft = findViewById(R.id.imageLeftProfile);

        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);


        Intent intent = getIntent();
        Account a = (Account)intent.getSerializableExtra("Account");

        editUserName.setText(a.getUsername());
        editPhone.setText(a.getPhone());
        editAddress.setText(a.getAddress());


        btnSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Account edit = new Account();
                edit.setId(a.getId());
                edit.setUsername(editUserName.getText().toString());
                edit.setPhone(editPhone.getText().toString());
                edit.setAddress(editAddress.getText().toString());
                edit.setImageId(a.getImageId());
                LoginController loginController = new LoginController();
                loginController.UpdateAccount(edit);
                Toast.makeText(view.getContext(),"Change profile successfully",Toast.LENGTH_LONG).show();
                finish();
            }
        });

        imageLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}