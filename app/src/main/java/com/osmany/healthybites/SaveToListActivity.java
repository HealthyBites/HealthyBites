package com.osmany.healthybites;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.osmany.healthybites.adapters.IngredientsAdapter;
import com.osmany.healthybites.fragments.HomeFragment;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class SaveToListActivity extends AppCompatActivity {

    RecyclerView rvIngredients;
    IngredientsAdapter adapter;
    ArrayList<String> ingredientList;

    public SaveToListActivity() {};

    /*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_save_to_list, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvIngredients = view.findViewById(R.id.rvIngredientList);

        ingredientList = new ArrayList<>();
        ingredientList = getActivity().getIntent().getStringArrayListExtra("ingredientList");
        adapter = new IngredientsAdapter(getContext(), ingredientList);

        //Steps to use the recycler view:
        //0. create layout for one row in the list
        //1. create the adapter
        //2. create the data source
        //3. set the adapter on the recycler view
        rvIngredients.setAdapter(adapter);
        //4. set the layout manager on the recycler view
        rvIngredients.setLayoutManager(new LinearLayoutManager(getContext()));

        //queryIngredients();
    }
    */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ingredientList = getIntent().getStringArrayListExtra("ingredientList");
        setContentView(R.layout.activity_save_to_list);

        // Register your parse models
        //ParseObject.registerSubclass(GroceryList.class);
        // Add your initialization code here
        //Parse.initialize(this, "0ou4FU2EEx08px1NG3YxiirZbxbtvpWtJHbD8Npl", "wlBd4PrYq7MPbTbeDhCYopQ1M7wpCccaK8KIpU7r");


        //Find the recycler view
        rvIngredients = findViewById(R.id.rvIngredientList);
        //Initialize the list of tweets in adapter
        ingredientList = new ArrayList<>();

        ingredientList = getIntent().getStringArrayListExtra("ingredientList");

        /*
        //String temp = "";
        for(int i=0; i<ingredientList.size(); i++){
            //temp = ingredientList.get(i);
            char[] str = ingredientList.get(i).toCharArray();
            char[] str2 = new char[str.length];

            for(int j=0; j<str.length; j++){
                while(str[j]!=','){
                    str2[j] = str[j];
                }
            }
            ingredientList.set(i, String.copyValueOf(str2));
        }
        */

        ArrayList<String> newList = new ArrayList<>();
        for(int i = 0; i < ingredientList.size(); i++)
        {
            String temp = "";
            if(ingredientList.get(i).contains(",") || ingredientList.get(i).contains("(")){
                //newList.set(i, ingredientList.get(i).substring(0, ingredientList.get(i).indexOf(",")));
                //someList.set(i, someList.get(i).replace(someString, otherString));
                temp = ingredientList.get(i);
                //temp.replaceAll(",.*", "");
                temp = temp.split(",")[0];
                temp = temp.split("\\(")[0];
                //ingredientList.set(i, temp);
                newList.add(temp);
            }else{
                // If it not contains `someString`, add it as it is to newList
                newList.add(ingredientList.get(i));
            }
        }

        adapter = new IngredientsAdapter(this, newList);
        //Recycler view setup: layout manager and adapter
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvIngredients.setLayoutManager(layoutManager);
        rvIngredients.setAdapter(adapter);


        //adapter.addAll(newList);



    }




    /*
    protected void queryIngredients() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);

        query.setLimit(20);
        query.addDescendingOrder(Post.KEY_CREATED_KEY);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if(e != null){
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                for(Post post : posts){
                    Log.i(TAG, "Post: " + post.getDescription() + ", username: " + post.getUser());
                }
                adapter.clear();
                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();
            }
        });

    }

     */

}
