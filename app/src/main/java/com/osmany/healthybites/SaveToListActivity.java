package com.osmany.healthybites;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.osmany.healthybites.adapters.IngredientsAdapter;
import com.osmany.healthybites.fragments.HomeFragment;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class SaveToListActivity extends AppCompatActivity {

    RecyclerView rvIngredients;
    IngredientsAdapter adapter;
    ArrayList<String> ingredientList;

    public SaveToListActivity() {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_save_to_list);

        //Find the recycler view
        rvIngredients = findViewById(R.id.rvIngredientList);
        //Initialize the list of tweets in adapter
        ingredientList = new ArrayList<>();

        ingredientList = getIntent().getStringArrayListExtra("ingredientList");

        ArrayList<String> newList = new ArrayList<>();
        for(int i = 0; i < ingredientList.size(); i++)
        {
            String temp = "";
            if(ingredientList.get(i).contains(",") || ingredientList.get(i).contains("(")){

                temp = ingredientList.get(i);

                temp = temp.split(",")[0];
                temp = temp.split("\\(")[0];

                newList.add(temp);
            }else{
                // If it not contains `someString`, add it as it is to newList
                newList.add(ingredientList.get(i));
            }
        }

        adapter = new IngredientsAdapter(this, newList);
        //Recycler view setup: layout manager and adapter
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvIngredients.setLayoutManager(layoutManager);
        rvIngredients.setAdapter(adapter);


    }

}
