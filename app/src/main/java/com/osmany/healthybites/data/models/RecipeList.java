package com.osmany.healthybites.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecipeList {
    @SerializedName("recipes")
    @Expose
    private List<Recipe> recipes = null;

    public List<Recipe> getRecipe() {
        return recipes;
    }

    public void setRecipie(List<Recipe> recipe) {
        this.recipes = recipe;
    }

}







