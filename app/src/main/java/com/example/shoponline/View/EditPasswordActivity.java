package com.example.shoponline.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;


import com.example.shoponline.Controller.LoginController;
import com.example.shoponline.R;
import com.example.shoponline.View.Fragment.MenuFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class EditPasswordActivity extends AppCompatActivity {

  //  private EditText editNew, editRe_end;
    private Button btnSave;
    private TextView textNoti;
    private TextView imageLeftPass;
    private TextInputEditText editOld,editNew, editRe_end;

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
        editOld.setText(sharedPreferences.getString("Password",""));
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editOld.getText().toString().equals( sharedPreferences.getString("Password",""))){
                    if(editNew.getText().toString().equals(editRe_end.getText().toString())  ){

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("Password",editNew.getText().toString());
                        editor.apply();
                        LoginController loginController = new LoginController();
                        loginController.UpdatePassword(editRe_end.getText().toString(),sharedPreferences.getLong("UserId",0)+"");
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

                finish();

            }
        });


    }
}