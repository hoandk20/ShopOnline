package com.example.shoponline.View.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shoponline.Controller.LoginController;
import com.example.shoponline.Model.Account;
import com.example.shoponline.R;
import com.example.shoponline.View.MainActivity;
import com.example.shoponline.View.RegistrationActivity;

import java.io.Serializable;


public class LoginFragment extends Fragment {

    private EditText Username;
    private EditText Password;
    private Button btnLogin;
    private Button btnRegister;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Username = view.findViewById(R.id.editTextLoginUsername);
        Password = view.findViewById(R.id.editTextPassword);
        btnLogin = view.findViewById(R.id.buttonLogin);
        btnRegister = view.findViewById(R.id.btnRegistration);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginController loginController  = new LoginController();
                boolean isSucess = loginController.Login(Username.getText().toString(),Password.getText().toString());
                //user name: hoan , password: 12345   is true
                if(isSucess){
                    Toast toast =Toast.makeText(view.getContext(),"login sucessfull",Toast.LENGTH_LONG);
                    toast.show();

                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();


                    Account a   = loginController.getAccountByName(Username.getText().toString());
                    //Change
                    editor.putLong("UserId",a.getId());
                    editor.putString("Username",Username.getText().toString());
                    editor.putString("Password",Password.getText().toString());
                    editor.putString("Phone",a.getPhone());
                    editor.putString("Address",a.getAddress());
                    editor.putString("ImageId",a.getImageId());


                    editor.commit();
                    Intent intent = new Intent(view.getContext(), MainActivity.class);
                    startActivity(intent);

                }else{
                    Toast toast= Toast.makeText(view.getContext(),"login failure",Toast.LENGTH_LONG);

                    toast.show();
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
}