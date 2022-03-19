package com.example.shoponline.View.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
//import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoponline.Common.ImageSupport;
import com.example.shoponline.MapActivity;
import com.example.shoponline.R;
import com.example.shoponline.View.EditPasswordActivity;
import com.example.shoponline.View.EditProfileActivity;
import com.example.shoponline.View.MainActivity;
import com.example.shoponline.View.ProductsPurchased_Activity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    private Button btnLogout;
    private TextView textUserName;
    private TextView textPhone;
    private TextView textAddress;
    private TextView textProducts_purchased;
    private TextView texteditProfile;
    private TextView texteditPass;
    private TextView texteditLocation;
    private ImageView imageView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnLogout = view.findViewById(R.id.btnLogOut);
        textUserName = view.findViewById(R.id.textUserName);
        textPhone = view.findViewById(R.id.textphone);
        textAddress = view.findViewById(R.id.textaddress);
        textProducts_purchased = view.findViewById(R.id.textProducts_purchased);
        texteditProfile = view.findViewById(R.id.texteditProfile);
        texteditPass = view.findViewById(R.id.texteditPass);
        texteditLocation = view.findViewById(R.id.texteditLocation);
        imageView = view.findViewById(R.id.imageViewProfile);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);

        textUserName.setText(sharedPreferences.getString("Username",""));
        textPhone.setText(sharedPreferences.getString("Phone",""));
        textAddress.setText(sharedPreferences.getString("Address",""));
         String imageId = sharedPreferences.getString("ImageId","");
        ImageSupport imageSupport = new ImageSupport();
        imageView.setImageBitmap(imageSupport.getBitMapImagebyId(imageId));


        texteditLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MapActivity.class);
                startActivity(intent);
            }
        });

        texteditPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EditPasswordActivity.class);
                startActivity(intent);
            }
        });
        texteditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EditProfileActivity.class);
                startActivity(intent);
            }
        });
        textProducts_purchased.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ProductsPurchased_Activity.class);
                startActivity(intent);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("UserId","");
                editor.putString("Username","");

                //change
                editor.putString("Phone","");
                editor.putString("Address","");
                editor.putString("Password","");

                editor.commit();
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.replayceFragment(new HomeFragment());
            }
        });


    }
}