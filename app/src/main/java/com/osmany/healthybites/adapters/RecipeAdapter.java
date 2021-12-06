package com.osmany.healthybites.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.osmany.healthybites.MainActivity;
import com.osmany.healthybites.R;
import com.osmany.healthybites.RecipeDetailActivity;
import com.osmany.healthybites.data.models.Item;
import com.osmany.healthybites.data.models.Recipe;
import com.osmany.healthybites.fragments.HomeFragment;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import retrofit2.http.Query;


public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.MyViewHolder> {
    Context context;
    int ITEM_COUNT = 10;
    List<Recipe> recipeList;
    ArrayList<String> ingredientList;
    Recipe recipe;

    public RecipeAdapter(Context context, List<Recipe> recipeList) {
        this.context = context;
        this.recipeList = recipeList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_recipe, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

//        holder.title.setText(recipeList.get(position).getItem().getTitle());
//        Glide
//                .with(context)
//                .load(recipeList.get(position).getItem().getImage())
//                .into(holder.myImage);



        recipe = recipeList.get(new Random().nextInt(100));
        holder.bind(recipe);
    }

    @Override
    public int getItemCount() {
        return ITEM_COUNT;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        RelativeLayout container;
        TextView title;
        ImageButton ibFavorites;
        ImageView myImage;
        int emptyStar = R.drawable.empty_star;
        int filledStar = R.drawable.filled_star;
        TextView summary;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvRecipeTitle);
            myImage = itemView.findViewById(R.id.ivRecipeImage);
            container = itemView.findViewById(R.id.container);
            ibFavorites = itemView.findViewById(R.id.ibStar);
            ibFavorites.setOnClickListener(imgButtonHandler);
            ibFavorites.setTag("1");

        }

        View.OnClickListener imgButtonHandler = new View.OnClickListener() {

            public void onClick(View v) {
                if(ibFavorites.getTag() == "1") {
                    ibFavorites.setImageResource(filledStar);
                    ibFavorites.setTag("2");
                    ParseUser parseUser = ParseUser.getCurrentUser();
                    ParseObject favorites = new ParseObject("Favorites");
                    favorites.put("recipeName", recipe.getItem().getTitle());
                    favorites.put("userId", parseUser);
                    favorites.saveInBackground();
                }else{
                    ibFavorites.setImageResource(emptyStar);
                    ibFavorites.setTag("1");
                }
            }
        };

        public void bind(Recipe recipe) {
            title.setText(recipe.getItem().getTitle());

            Glide
                    .with(context)
                    .load(recipe.getItem().getImage())
                    .placeholder(R.drawable.placeholder)
                    .fitCenter()
                    .transform(new RoundedCornersTransformation(100,0))
                    .into(myImage);


            // 1. Register click listener on the whole row
            container.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    // 2. Navigate to a new activity on tap
                    Intent i = new Intent(context, RecipeDetailActivity.class);
                    i.putExtra("title", recipe.getItem().getTitle());
                    i.putExtra("image", recipe.getItem().getImage());
                    i.putExtra("summary", recipe.getItem().getSummary());
                        ingredientList = new ArrayList<String>();
                    for (int j = 0; j < recipe.getItem().getExtendedIngredients().size(); j++){
                        ingredientList.add(recipe.getItem().getExtendedIngredients().get(j).getName());
                    }
                    i.putStringArrayListExtra("ingredients", ingredientList);
                    //i.putExtra("movie", Parcels.wrap(movie));
                    context.startActivity(i);
                }
            });

        }
    }

}
