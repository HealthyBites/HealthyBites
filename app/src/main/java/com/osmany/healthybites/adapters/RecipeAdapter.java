package com.osmany.healthybites.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.osmany.healthybites.R;
import com.osmany.healthybites.data.models.Recipe;

import java.util.ArrayList;
import java.util.List;


public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.MyViewHolder> {
    Context context;
    List<Recipe> recipeList ;

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
        holder.description.setText(recipeList.get(position).getTitle());
        Glide
                .with(context)
                .load(recipeList.get(position).getImage())
                .into(holder.myImage);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView description;
        ImageView myImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.tvRecipeDescription);
            myImage = itemView.findViewById(R.id.ivRecipeImage);
        }
    }

}
