package com.osmany.healthybites;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.TextView;

import com.osmany.healthybites.data.clients.RetrofitClient;
import com.osmany.healthybites.data.models.JsonPlaceHolderApi;
import com.osmany.healthybites.data.models.RecipieList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    String baseUrl = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

}