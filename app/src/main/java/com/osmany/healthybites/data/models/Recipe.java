package com.osmany.healthybites.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.InputStream;

public class Recipe {
    @SerializedName("title")
    @Expose
    private String title;

   @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("summary")
    @Expose
    private String summary;

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
