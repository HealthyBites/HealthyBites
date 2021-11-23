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

                    String ingredient = tvIngredient.getText().toString();
                    ParseUser currentUser = ParseUser.getCurrentUser();
                    //saveIngredient(currentUser, ingredient);


                    // Register your parse models
                    ParseObject.registerSubclass(GroceryList.class);

                    //ParseObject groceryList = ParseObject.create("GroceryList");
                    GroceryList gList = new GroceryList();
                    //groceryList.setIngredient(ingredient);
                    //groceryList.put("ingredient", ingredient);
                    //post.setImage(new ParseFile(photoFile));
                    //groceryList.setUser(currentUser);
                    //groceryList.put("userId", currentUser);

                    //ParseUser currentUser = ParseUser.getCurrentUser();
                    ParseObject groceryList = ParseObject.create(GroceryList.class);

                    //ParseObject groceryList = new ParseObject("GroceryList");
                    //GroceryList groceryList = new GroceryList();

                    groceryList.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e == null){
                                //Toast.makeText(EditProfileActivity.this, "Profile Updated..", Toast.LENGTH_SHORT).show();
                                //currentUser.put("age", age);
                                //currentUser.put("weight", weight);
                                //currentUser.put("height" , height);
                                //currentUser.put("diet", diet);
                                //Log.i(TAG, "Data: " + " " + age + " " + weight + " " +height + " " +diet);
                                //openMainActivity();
                                //currentUser.put("ingredient", ingredient);
                                //gList.setIngredient(ingredient);
                                //groceryList.put("ingredient", ingredient);
                                //groceryList.put("userId", currentUser);

                                gList.setIngredient(ingredient);
                                gList.setUser(currentUser);

                                btnAddToList.setClickable(false);
                            }else{
                                //Log.i(TAG,"Save in background " + e.getLocalizedMessage());
                                //Toast.makeText(EditProfileActivity.this, "Failed to update profile " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                    //btnAddToList.setClickable(false);
                }
            });
        }
    }

    private void saveIngredient(ParseUser currentUser, String ingredient) {

        GroceryList groceryList = new GroceryList();
        groceryList.setIngredient(ingredient);
        //post.setImage(new ParseFile(photoFile));
        groceryList.setUser(currentUser);

    }
}
