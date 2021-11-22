package com.osmany.healthybites.data.models;

import com.osmany.healthybites.BuildConfig;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface JsonPlaceHolderApi {
//    856832bf48msh783ac55978b7517p16fe50jsn043078a575d9
//    @Headers({"x-rapidapi-host:" + BuildConfig.HOST,
//            "x-rapidapi-key:" + BuildConfig.KEY})
    @Headers({"x-rapidapi-host:" + "edamam-recipe-search.p.rapidapi.com",
            "x-rapidapi-key:" + "856832bf48msh783ac55978b7517p16fe50jsn043078a575d9"})
    @GET("search?q=healthy")
    Call<RecipeList> getRecipies();
}
