package com.osmany.healthybites;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class RecipeDetailActivity extends AppCompatActivity {

    TextView title;
    ImageView myImage;
    TextView summary;
    TextView ingredients;
    ArrayList<String> ingredientList;
    String ingredientString = "";

    Button saveIngredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        title = findViewById(R.id.tvRecipeTitle);
        myImage = findViewById(R.id.ivRecipeImage);
        summary = findViewById(R.id.tvRecipeSummary);
        ingredients = findViewById(R.id.tvRecipeIngredients);

        saveIngredients = findViewById(R.id.btnIngredients);

        title.setText(getIntent().getStringExtra("title"));
        Glide
                .with(this)
                .load(getIntent().getStringExtra("image"))
                .fitCenter()
                .transform(new RoundedCornersTransformation(100,0))
                .into(myImage);

        summary.setText(getIntent().getStringExtra("summary"));


        ingredientList = getIntent().getStringArrayListExtra("ingredients");
        for(int i = 0; i < ingredientList.size(); i++){
            ingredientString+= ingredientList.get(i) + "\n";
        }
        ingredients.setText(ingredientString);

        saveIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goSaveToListActivity();
            }
        });

    }

    private void goSaveToListActivity() {
        Intent i = new Intent(this, SaveToListActivity.class);

        i.putStringArrayListExtra("ingredientList", ingredientList);

        startActivity(i);
        //finish();
    }
}
