package com.osmany.healthybites.data.models;

import com.osmany.healthybites.BuildConfig;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface JsonPlaceHolderApi {

    @Headers({"x-rapidapi-host:" + BuildConfig.HOST,
            "x-rapidapi-key:" + BuildConfig.KEY})
    @GET("search?q=healthy&from=0&to=100")
    Call<RecipeList> getRecipies();
}
