package com.example.shoponline.View;

import androidx.appcompat.app.AppCompatActivity;

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
        Account account = new Account();
        editUserName.setText(sharedPreferences.getString("Username",""));
        editPhone.setText(sharedPreferences.getString("Phone",""));
        editAddress.setText(sharedPreferences.getString("Address",""));
        account.setId(sharedPreferences.getLong("UserId",0));
        account.setImageId(sharedPreferences.getString("ImageId",""));

        btnSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Username",editUserName.getText().toString());
                editor.putString("Address",editAddress.getText().toString());
                editor.putString("Phone",editPhone.getText().toString());
                editor.apply();
                LoginController loginController = new LoginController();
                account.setUsername(editUserName.getText().toString());
                account.setPhone(editPhone.getText().toString());
                account.setAddress(editAddress.getText().toString());
                account.setPassword(sharedPreferences.getString("Password",""));
                loginController.UpdateAccount(account);

                Toast.makeText(view.getContext(),"Change profile successfully",Toast.LENGTH_LONG).show();
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