package com.osmany.healthybites;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RecipieList {
    @SerializedName("recipes")
    @Expose
    private List<Recipie> recipes = null;

    public List<Recipie> getRecipe() {
        return recipes;
    }

    public void setRecipie(List<Recipie> recipe) {
        this.recipes = recipe;
    }
}







