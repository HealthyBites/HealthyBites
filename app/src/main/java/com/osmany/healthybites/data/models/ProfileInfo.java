package com.osmany.healthybites.data.models;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Date;

@ParseClassName("User")
public class ProfileInfo extends ParseObject {

    public static final String KEY_EMAIL = "email";
    public static final String KEY_IMAGE = "userImage";
    public static final String KEY_USER = "user";
    public static final String KEY_DIET= "dietaryPreferences";
    public static final String KEY_AGE= "age";
    public static final String KEY_WEIGHT= "weight";
    public static final String KEY_HEIGHT= "height";

    public String getEmail() {
        return getString(KEY_EMAIL);
    }

    public ParseFile getImage() {
        return getParseFile(KEY_IMAGE);
    }

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public String getDiet() {
        return getString(KEY_DIET);
    }

    public String getAge() {
        return getString(KEY_AGE);
    }

    public String getWeight() {
        return getString(KEY_WEIGHT);
    }

    public String getHeight() {
        return getString(KEY_HEIGHT);
    }

    public void setEmail(String email){
        put(KEY_EMAIL, email);
    }

    public void setUser(ParseUser user){
        put(KEY_USER, user);
    }

    public void setImage(ParseFile parseFile){
        put(KEY_IMAGE, parseFile);
    }

    public void setDiet(String diet){
        put(KEY_DIET, diet);
    }

    public void setAge(String age){
        put(KEY_AGE, age);
    }

    public void setHeight(String height){
        put(KEY_HEIGHT, height);
    }

    public void setWeight(String weight){
        put(KEY_WEIGHT, weight);
    }
}
