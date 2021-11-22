package com.osmany.healthybites.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.osmany.healthybites.R;
import com.osmany.healthybites.adapters.RecipeAdapter;
import com.osmany.healthybites.data.clients.RetrofitClient;
import com.osmany.healthybites.data.models.JsonPlaceHolderApi;
import com.osmany.healthybites.data.models.RecipeList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    String baseUrl = "https://edamam-recipe-search.p.rapidapi.com/";
    RecyclerView recyclerView;
    String TAG = "HomeActivity";

    public HomeFragment(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_random_recipe, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rvRecipeList);

        JsonPlaceHolderApi jsonPlaceHolderApi = RetrofitClient.getClient(baseUrl).create(JsonPlaceHolderApi.class);

        Call<RecipeList> call = jsonPlaceHolderApi.getRecipies();
        call.enqueue(new Callback<RecipeList>() {
            @Override
            public void onResponse(Call<RecipeList> call, Response<RecipeList> response) {
                if (!response.isSuccessful()) {
                    Log.e(TAG , "Response code: " + response.code());
                    return ;
                }

                RecipeAdapter recipeAdapter = new RecipeAdapter(getContext(),response.body().getRecipe());
                recyclerView.setAdapter(recipeAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onFailure(Call<RecipeList> call, Throwable t) {
                Log.e(TAG,t.getMessage());
            }
        });

    }
}
