package com.osmany.healthybites.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.osmany.healthybites.GroceryList;
import com.osmany.healthybites.R;
import com.osmany.healthybites.SaveToListActivity;
import com.osmany.healthybites.data.models.Recipe;
import com.parse.Parse;
import com.parse.ParseFile;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder>{

    Context context;
    List<String> ingredientList;

    public IngredientsAdapter(Context context, List<String> ingredientList) {
        this.context = context;
        this.ingredientList = ingredientList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_ingredient, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //ArrayList<String> ingredients = ingredientList.get(position);
        //holder.bind();
        //tvIngredient.setText(ingredientList.get(0));
        String ingredient = ingredientList.get(position);
        holder.bind(ingredient);
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public void addAll(ArrayList<String> list) {
        ingredientList.addAll(list);
        //notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvIngredient;
        private Button btnAddToList;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIngredient = itemView.findViewById(R.id.tvIngredient);
            btnAddToList = itemView.findViewById(R.id.btnAddToList);

           // ingredientList = getIntent().getStringArrayListExtra("ingredients");

            //tvIngredient.setText(ingredientList.get(0));
        }

        public void bind(String ingredient) {
            tvIngredient.setText(ingredient);

            btnAddToList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ParseUser currentUser = ParseUser.getCurrentUser();
                    saveIngredient(currentUser, ingredient);
                    Toast.makeText(context, "Ingredient saved successfully!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void saveIngredient(ParseUser currentUser, String ingredient) {
        ParseObject groceryList = new ParseObject("GroceryList");
        //GroceryList groceryList = new GroceryList(currentUser, ingredient);
        //groceryList.setIngredient(ingredient);
        groceryList.put("ingredient", ingredient);
        //post.setImage(new ParseFile(photoFile));
        //groceryList.setUser(currentUser);
        groceryList.put("userId", currentUser);
        groceryList.saveInBackground();
    }
}
