package com.osmany.healthybites.adapters;

import android.content.Context;
import android.content.Intent;
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
import com.osmany.healthybites.MainActivity;
import com.osmany.healthybites.R;
import com.osmany.healthybites.SaveToListActivity;
import com.osmany.healthybites.fragments.GroceryListFragment;
import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class GroceryListAdapter extends RecyclerView.Adapter<GroceryListAdapter.ViewHolderGL> {

    Context context;
    List<String> ingredients;

    public GroceryListAdapter(Context context, List<String> ingredients) {
        this.context = context;
        this.ingredients = ingredients;

    }

    @NonNull
    @Override
    public ViewHolderGL onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_ingredient_gl, parent, false);

        return new ViewHolderGL(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderGL holder, int position) {
        String ingredient = ingredients.get(position);
        holder.bind(ingredient);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public void clear() {
        ingredients.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<String> list) {
        ingredients.addAll(list);
        notifyDataSetChanged();
    }

    public void updateAll(List<GroceryList> parseGroceryList){
        //this.ingredients = parseGroceryList;
        notifyDataSetChanged();
    }

    class ViewHolderGL extends RecyclerView.ViewHolder {

        private TextView tvIngredientGL;
        private Button btnDeleteFromList;

        public ViewHolderGL(@NonNull View itemView) {
            super(itemView);
            tvIngredientGL = itemView.findViewById(R.id.tvIngredientGL);
            btnDeleteFromList = itemView.findViewById(R.id.btnDeleteFromList);

        }


        public void bind(String ingredient) {

            tvIngredientGL.setText(ingredient);

            btnDeleteFromList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("GroceryList");
                    query.whereEqualTo(GroceryList.KEY_USER, ParseUser.getCurrentUser());
                    query.whereEqualTo("ingredient", ingredient);
                    query.findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List<ParseObject> objects, ParseException e) {
                            String objectID = "";
                            for(ParseObject object: objects){
                                objectID = object.getObjectId();
                            }

                            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("GroceryList");
                            query.getInBackground(objectID, new GetCallback<ParseObject>() {
                                @Override
                                public void done(ParseObject object, ParseException e) {
                                    object.deleteInBackground();
                                    notifyDataSetChanged();

                                    Toast.makeText(context, "Ingredient deleted successfully!", Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    });

                }
            });



        }


    }



}
