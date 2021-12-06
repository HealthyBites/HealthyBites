package com.osmany.healthybites.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.osmany.healthybites.GroceryList;
import com.osmany.healthybites.R;
import com.osmany.healthybites.adapters.GroceryListAdapter;
import com.osmany.healthybites.data.models.Recipe;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class GroceryListFragment extends Fragment {

    public static final String TAG = "GroceryListFragment";
    protected RecyclerView rvGroceryList;
    protected GroceryListAdapter adapter;
    protected List<String> allIngredients;

    public GroceryListFragment(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grocery_list, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvGroceryList = view.findViewById(R.id.rvGroceryList);

        allIngredients = new ArrayList<>();
        adapter = new GroceryListAdapter(getContext(), allIngredients);

        rvGroceryList.setAdapter(adapter);
        rvGroceryList.setLayoutManager(new LinearLayoutManager(getContext()));

        queryIngredients();

    }

    protected void queryIngredients() {

        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("GroceryList");
        query.whereEqualTo(GroceryList.KEY_USER, ParseUser.getCurrentUser());
        query.addDescendingOrder(GroceryList.KEY_CREATED_KEY);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                List<String> arraylist = new ArrayList<>();

                for(ParseObject object: objects){
                    arraylist.add(object.get("ingredient").toString());
                }
                allIngredients.addAll(arraylist);
                adapter.notifyDataSetChanged();
            }
        });

    }

}
