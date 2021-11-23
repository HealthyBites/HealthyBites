package com.osmany.healthybites.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.osmany.healthybites.R;
import com.osmany.healthybites.profile.EditProfileActivity;
import com.osmany.healthybites.profile.LoginActivity;
import com.parse.ParseUser;


public class ProfileFragment extends Fragment {

    public static final String TAG = "ProfileFragment";

    private TextView tvLogout;
    private TextView tvEditProfile;
    private TextView tvChangePassword;
    private TextView tvDiet;
    private TextView tvEmail;
    private TextView tvAge;
    private TextView tvWeight;
    private TextView tvHeight;
    private TextView tvName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvLogout = view.findViewById(R.id.tvLogout);

        tvEditProfile = view.findViewById(R.id.tvEditProfile);
        tvChangePassword = view.findViewById(R.id.tvChangePassword);

        tvName = view.findViewById(R.id.tvName);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvDiet = view.findViewById(R.id.tvDiet);
        tvAge = view.findViewById(R.id.tvAge);
        tvHeight = view.findViewById(R.id.tvHeight);
        tvWeight = view.findViewById(R.id.tvWeight);

        ParseUser currentUser = ParseUser.getCurrentUser();
        if(currentUser != null){
            Log.d(TAG, "onViewCreated: " + currentUser.getUsername());
            tvName.setText(currentUser.getString("username"));
            tvEmail.setText(currentUser.getEmail());
            tvAge.setText(currentUser.getString("age"));
            tvHeight.setText(currentUser.getString("height"));
            tvWeight.setText(currentUser.getString("weight"));
            tvDiet.setText(currentUser.getString("diet"));
            }
        if(currentUser.getString("age") == null){
            currentUser.put("age", "Add your age?");
            tvAge.setText(currentUser.getString("age"));
        }
        if (currentUser.getString("height") == null){
            currentUser.put("height", "Add your height?");
            tvHeight.setText(currentUser.getString("height"));
        }
        if(currentUser.getString("weight") == null){
            currentUser.put("weight", "Add your weight?");
            tvWeight.setText(currentUser.getString("weight"));
        }
        if(currentUser.getString("diet") == null){
            currentUser.put("diet", "Add your diet preferences?");
            tvDiet.setText(currentUser.getString("diet"));
        }

        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logOut();
                if(ParseUser.getCurrentUser() == null){
                    goLoginActivity();
                }
            }
        });


        tvEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goEditProfileActivity();
            }
        });
    }

    private void goLoginActivity() {
        Intent i = new Intent(getContext(), LoginActivity.class);
        startActivity(i);
        getActivity().onBackPressed();
    }

    private  void goEditProfileActivity(){
        Intent i = new Intent(getContext(), EditProfileActivity.class);
        startActivity(i);
    }


}