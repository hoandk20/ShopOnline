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


import com.example.shoponline.R;


public class EditPasswordActivity extends AppCompatActivity {

    private EditText editNew, editOld,editRe_end;
    private Button btnSave;
    private TextView textNoti;
    private TextView imageLeftPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);

        editNew = findViewById(R.id.editNewPass);
        editOld = findViewById(R.id.editOldPass);
        editRe_end = findViewById(R.id.editRe_NewPass);
        btnSave = findViewById(R.id.btnSaveEditPass);
        textNoti = findViewById(R.id.textNoti_Pass);
        imageLeftPass = findViewById(R.id.imageLeftPass);

        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editOld.getText().toString() == sharedPreferences.getString("password","")){

                    if(editNew.getText().toString() == editRe_end.getText().toString()){
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("password",editNew.getText().toString());
                        editor.apply();
                        Toast.makeText(view.getContext(),"Change password successfully",Toast.LENGTH_LONG).show();

                    }else{
                        textNoti.setText("Re-entered password is incorrect");
                    }

                }else{
                    textNoti.setText("Incorrect password");
                }
            }
        });

        imageLeftPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}