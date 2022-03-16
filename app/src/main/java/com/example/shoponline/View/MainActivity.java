package com.example.shoponline.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoponline.Common.API;
import com.example.shoponline.Controller.LoginController;
import com.example.shoponline.R;
import com.example.shoponline.View.Fragment.CartFragment;
import com.example.shoponline.View.Fragment.HomeFragment;
import com.example.shoponline.View.Fragment.LoginFragment;
import com.example.shoponline.View.Fragment.MenuFragment;
import com.example.shoponline.View.Fragment.NotificationFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    boolean isLogin = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        setContentView(R.layout.activity_main);

        SharedPreferences pref = getSharedPreferences("User",MODE_PRIVATE);
        String UserId = pref.getString("UserId","");
        isLogin = (UserId=="")?false:true;

        if(isLogin){
            replayceFragment(new HomeFragment());
            bottomNavigationView = findViewById(R.id.bottomNavigationView);
            initAction();
            action();
        }else{
            replayceFragment(new LoginFragment());
        }

    }

    private void action() {
    }

    private void initAction() {
        bottomNavigationViewAction();
    }

    private void bottomNavigationViewAction() {
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    replayceFragment(new HomeFragment());
                    break;
                case R.id.cart:
                    replayceFragment(new CartFragment());
                    break;
                case R.id.notification:
                    replayceFragment(new NotificationFragment());
                    break;
                case R.id.menu:
                    replayceFragment(new MenuFragment());
                    break;
            }
            return true;
        });
    }

    private void replayceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}