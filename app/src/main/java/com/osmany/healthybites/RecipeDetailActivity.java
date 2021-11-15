package com.osmany.healthybites;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class RecipeDetailActivity extends AppCompatActivity {

    TextView title;
    ImageView myImage;
    TextView summary;
    TextView ingredients;
    List<String> ingredientList;
    String ingredientString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        title = findViewById(R.id.tvRecipeTitle);
        myImage = findViewById(R.id.ivRecipeImage);
        summary = findViewById(R.id.tvRecipeSummary);
        ingredients = findViewById(R.id.tvRecipeIngredients);

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

    }
}
