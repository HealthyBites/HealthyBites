package com.osmany.healthybites.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.osmany.healthybites.R;
import com.osmany.healthybites.RecipeDetailActivity;
import com.osmany.healthybites.data.models.Recipe;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;


public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.MyViewHolder> {
    Context context;
    List<Recipe> recipeList ;
    ArrayList<String> ingredientList;

    public RecipeAdapter(Context context, List<Recipe> recipeList){
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
        /*
        holder.description.setText(recipeList.get(position).getTitle());
        Glide
                .with(context)
                .load(recipeList.get(position).getImage())
                .into(holder.myImage);

         */

        Recipe recipe = recipeList.get(position);
        holder.bind(recipe);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        RelativeLayout container;
        TextView title;
        ImageView myImage;
        TextView summary;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvRecipeTitle);
            myImage = itemView.findViewById(R.id.ivRecipeImage);
            container = itemView.findViewById(R.id.container);
        }

        public void bind(Recipe recipe) {
            title.setText(recipe.getTitle());

            Glide
                    .with(context)
                    .load(recipe.getImage())
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
                    i.putExtra("title", recipe.getTitle());
                    i.putExtra("image", recipe.getImage());
                    i.putExtra("summary", recipe.getSummary());
                        ingredientList = new ArrayList<String>();
                    for (int j =0; j < recipe.getExtendedIngredients().size(); j++){
                        ingredientList.add(recipe.getExtendedIngredients().get(j).getName());
                    }
                    i.putStringArrayListExtra("ingredients", ingredientList);
                    //i.putExtra("movie", Parcels.wrap(movie));
                    context.startActivity(i);
                }
            });

        }
    }

}
