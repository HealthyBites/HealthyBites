package com.osmany.healthybites.models;

import android.content.Context;
import android.widget.TextView;

import com.osmany.healthybites.R;
import com.osmany.healthybites.data.clients.RetrofitClient;
import com.osmany.healthybites.data.models.JsonPlaceHolderApi;
import com.osmany.healthybites.data.models.RecipieList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Recipie {
    private String title;
    public Recipie() {
        String baseUrl = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/";

        JsonPlaceHolderApi jsonPlaceHolderApi = RetrofitClient.getClient(baseUrl).create(JsonPlaceHolderApi.class);

        Call<RecipieList> call = jsonPlaceHolderApi.getRecipies();
        call.enqueue(new Callback<RecipieList>() {
            @Override
            public void onResponse(Call<RecipieList> call, Response<RecipieList> response) {
                if (!response.isSuccessful()) {
                    title = Integer.toString(response.code());

                    return ;
                }
                title = response.body().getRecipe().get(0).getTitle();
            }

            @Override
            public void onFailure(Call<RecipieList> call, Throwable t) {
                title = t.getMessage();
            }
        });
    }
}
