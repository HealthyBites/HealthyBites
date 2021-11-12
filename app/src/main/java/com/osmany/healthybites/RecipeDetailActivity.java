package com.osmany.healthybites;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class RecipeDetailActivity extends AppCompatActivity {

    TextView title;
    ImageView myImage;
    TextView summary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        title = findViewById(R.id.tvRecipeTitle);
        myImage = findViewById(R.id.ivRecipeImage);
        summary = findViewById(R.id.tvRecipeSummary);

        title.setText(getIntent().getStringExtra("title"));
        Glide
                .with(this)
                .load(getIntent().getStringExtra("image"))
                .fitCenter()
                .transform(new RoundedCornersTransformation(100,0))
                .into(myImage);

        summary.setText(getIntent().getStringExtra("summary"));
    }
}
