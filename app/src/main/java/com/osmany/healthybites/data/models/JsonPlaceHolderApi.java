package com.osmany.healthybites.data.models;

import com.osmany.healthybites.BuildConfig;
import com.osmany.healthybites.data.models.RecipieList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface JsonPlaceHolderApi {
    @Headers({"x-rapidapi-host:" + BuildConfig.HOST,
            "x-rapidapi-key:" + BuildConfig.KEY})
    @GET("recipes/random?number=10")
    Call<RecipieList> getRecipies();
}
