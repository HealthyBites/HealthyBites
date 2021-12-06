package com.osmany.healthybites.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.osmany.healthybites.GroceryList;
import com.osmany.healthybites.R;
import com.osmany.healthybites.adapters.GroceryListAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class GroceryListFragment extends Fragment {

    protected RecyclerView rvGroceryList;
    protected GroceryListAdapter adapter;
    protected List<GroceryList> allIngredients;

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
        //Steps to use the recycler view:
        //0. create layout for one row in the list
        //1. create the adapter
        //2. create the data source
        //3. set the adapter on the recycler view
        rvGroceryList.setAdapter(adapter);
        //4. set the layout manager on the recycler view
        rvGroceryList.setLayoutManager(new LinearLayoutManager(getContext()));

        queryIngredients();

    }

    protected void queryIngredients() {
        ParseQuery<GroceryList> query = ParseQuery.getQuery(GroceryList.class);
        query.include(GroceryList.KEY_USER);

        query.setLimit(20);
        query.addDescendingOrder(GroceryList.KEY_CREATED_KEY);
        query.findInBackground(new FindCallback<GroceryList>() {
            @Override
            public void done(List<GroceryList> groceryList, ParseException e) {
                if(e != null){
                    //Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                for(GroceryList ingredient : groceryList){
                    //Log.i(TAG, "Post: " + post.getDescription() + ", username: " + post.getUser());
                }
                adapter.clear();
                allIngredients.addAll(groceryList);
                adapter.notifyDataSetChanged();
            }
        });

    }


}
