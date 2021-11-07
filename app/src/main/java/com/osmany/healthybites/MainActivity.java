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
    TextView tvTitle;
    String baseUrl = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTitle = findViewById(R.id.tvHello);

        JsonPlaceHolderApi jsonPlaceHolderApi = RetrofitClient.getClient(baseUrl).create(JsonPlaceHolderApi.class);

        Call<RecipieList> call = jsonPlaceHolderApi.getRecipies();
        call.enqueue(new Callback<RecipieList>() {
            @Override
            public void onResponse(Call<RecipieList> call, Response<RecipieList> response) {
                if(!response.isSuccessful()){
                    tvTitle.setText("Code: " + response.code());
                    return;
                }
                tvTitle.setText(response.body().getRecipe().get(0).getTitle());
            }

            @Override
            public void onFailure(Call<RecipieList> call, Throwable t) {
                tvTitle.setText(t.getMessage());
            }
        });
    }

}