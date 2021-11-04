package com.osmany.healthybites;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            JSONObject jsonObject = new JSONObject(getRandomRecipies());
            JSONArray recipes = jsonObject.getJSONArray("recipies");
            JSONObject firstRecipie = recipes.getJSONObject(0);
            String title = firstRecipie.getString("title");
            Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("MainActivity","Failed to parse response data");
        }

    }

    protected String getRandomRecipies() {

        //TODO Need to create a class that extends Thread because network requests cannot be made on the main thread
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/random?number=10")
                .get()
                .addHeader("x-rapidapi-host", BuildConfig.HOST)
                .addHeader("x-rapidapi-key", BuildConfig.KEY)
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("MainActivity","Response failed");
        }

        return null;
    }
}