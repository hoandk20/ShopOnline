package com.example.shoponline;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoponline.Common.API;
import com.example.shoponline.Controller.LoginController;

public class MainActivity extends AppCompatActivity {

    EditText Username;
    EditText Password;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        setContentView(R.layout.activity_main);

        Username = findViewById(R.id.editTextLoginUsername);
        Password = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.buttonLogin);
            btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginController loginController  = new LoginController();
                boolean isSucess = loginController.Login(Username.getText().toString(),Password.getText().toString());
                //user name: hoan , password: 12345   is true
                if(isSucess){
                    Toast toast =Toast.makeText(view.getContext(),"login sucessfull",Toast.LENGTH_LONG);
                    toast.show();
                }else{
                    Toast toast= Toast.makeText(view.getContext(),"login failure",Toast.LENGTH_LONG);

                    toast.show();
                }


            }
        });

    }
}