package com.osmany.healthybites;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.TextView;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTitle = findViewById(R.id.tvHello);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

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