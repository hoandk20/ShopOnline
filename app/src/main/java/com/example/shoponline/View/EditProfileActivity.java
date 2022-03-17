package com.example.shoponline.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        editUserName.setText(sharedPreferences.getString("UserId",""));
        editPhone.setText(sharedPreferences.getString("phone",""));
        editAddress.setText(sharedPreferences.getString("address",""));


        btnSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("UserId",editUserName.getText().toString());
                editor.putString("address",editAddress.getText().toString());
                editor.putString("phone",editPhone.getText().toString());
                editor.apply();

                Toast.makeText(view.getContext(),"Change profile successfully",Toast.LENGTH_LONG).show();
            }
        });

        imageLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}