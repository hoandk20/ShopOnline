package com.example.shoponline.View.Fragment;

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
import com.example.shoponline.R;


public class LoginFragment extends Fragment {

    private EditText Username;
    private EditText Password;
    private Button btnLogin;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Username = view.findViewById(R.id.editTextLoginUsername);
        Password = view.findViewById(R.id.editTextPassword);
        btnLogin = view.findViewById(R.id.buttonLogin);
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