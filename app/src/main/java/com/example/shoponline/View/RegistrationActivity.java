package com.example.shoponline.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shoponline.Controller.LoginController;
import com.example.shoponline.Model.Account;
import com.example.shoponline.R;

public class RegistrationActivity extends AppCompatActivity {

    EditText username;
    EditText pass;
    EditText phone;
    EditText address;
    Button btnBack;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        username = findViewById(R.id.etResUsername);
        pass = findViewById(R.id.etResPass);
        phone = findViewById(R.id.etResPhone);
        address = findViewById(R.id.etResAddess);
        btnBack  = findViewById(R.id.btnResBack);
        btnSave  = findViewById(R.id.btnResSave);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Account account = new Account();
                account.setUsername(username.getText().toString());
                account.setPassword(pass.getText().toString());
                account.setPhone(phone.getText().toString());
                account.setAddress(address.getText().toString());
                LoginController loginController = new LoginController();
                loginController.InsertAccount(account);
                finish();
            }
        });
    }
}