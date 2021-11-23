package com.osmany.healthybites.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.osmany.healthybites.GroceryList;
import com.osmany.healthybites.R;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class GroceryListAdapter extends RecyclerView.Adapter<GroceryListAdapter.ViewHolderGL> {

    Context context;
    List<GroceryList> ingredientList;

    public GroceryListAdapter(Context context, List<GroceryList> ingredientList) {
        this.context = context;
        this.ingredientList = ingredientList;

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
        GroceryList groceryList = ingredientList.get(position);
        holder.bind(groceryList);
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public void clear() {
        ingredientList.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<GroceryList> list) {
        ingredientList.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolderGL extends RecyclerView.ViewHolder {

        private TextView tvIngredientGL;
        private Button btnDeleteFromList;

        public ViewHolderGL(@NonNull View itemView) {
            super(itemView);
            tvIngredientGL = itemView.findViewById(R.id.tvIngredientGL);
            btnDeleteFromList = itemView.findViewById(R.id.btnDeleteFromList);

            // ingredientList = getIntent().getStringArrayListExtra("ingredients");

            //tvIngredient.setText(ingredientList.get(0));
        }


        public void bind(GroceryList ingredients) {
            tvIngredientGL.setText(ingredients.getIngredient());

            btnDeleteFromList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });
        }


    }

}
