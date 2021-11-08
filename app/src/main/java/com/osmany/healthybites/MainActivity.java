package com.osmany.healthybites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.util.Log;

import com.osmany.healthybites.adapters.RecipeAdapter;
import com.osmany.healthybites.data.clients.RetrofitClient;
import com.osmany.healthybites.data.models.JsonPlaceHolderApi;
import com.osmany.healthybites.data.models.Recipe;
import com.osmany.healthybites.data.models.RecipeList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    String baseUrl = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/";
    RecyclerView recyclerView;
    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvRecipeList);

        JsonPlaceHolderApi jsonPlaceHolderApi = RetrofitClient.getClient(baseUrl).create(JsonPlaceHolderApi.class);

        Call<RecipeList> call = jsonPlaceHolderApi.getRecipies();
        call.enqueue(new Callback<RecipeList>() {
            @Override
            public void onResponse(Call<RecipeList> call, Response<RecipeList> response) {
                if (!response.isSuccessful()) {
                    Log.e(TAG , "Response code: " + response.code());
                    return ;
                }
                RecipeAdapter recipeAdapter = new RecipeAdapter(MainActivity.this,response.body().getRecipe());
                recyclerView.setAdapter(recipeAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }

            @Override
            public void onFailure(Call<RecipeList> call, Throwable t) {
                Log.e(TAG,t.getMessage());
            }
        });


    }

}