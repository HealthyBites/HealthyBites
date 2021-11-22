package com.osmany.healthybites.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.InputStream;
import java.util.List;

public class Item {
    @SerializedName("label")
    @Expose
    private String title;

   @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("url")
    @Expose
    private String summary;

    @SerializedName("ingredients")
    @Expose
    private List<ExtendedIngredients> extendedIngredients = null;

    public List<ExtendedIngredients> getExtendedIngredients() {
        return extendedIngredients;
    }

    public void setExtendedIngredients(List<ExtendedIngredients> extendedIngredients) {
        this.extendedIngredients = extendedIngredients;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }



}
