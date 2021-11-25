package com.osmany.healthybites;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("GroceryList")
public class GroceryList extends ParseObject {



    public static final String KEY_INGREDIENT = "ingredient";
    public static final String KEY_QUANTITY = "quantity";
    public static final String KEY_USER = "userId";
    public static final String KEY_CREATED_KEY = "createdAt";

    public GroceryList(ParseUser user, String ingredient) {
         //= type;
        //this.value = value;
    }

    public String getIngredient(){
        return  getString(KEY_INGREDIENT);
    }

    public void setIngredient(String ingredient){
        put(KEY_INGREDIENT, ingredient);
    }

    public int getQuantity(){
        return getInt(KEY_QUANTITY);
    }

    public void setQuantity(int quantity){
        put(KEY_QUANTITY, quantity);
    }

    public ParseUser getUser(){
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser userId){
        put(KEY_USER, userId);
    }

}
