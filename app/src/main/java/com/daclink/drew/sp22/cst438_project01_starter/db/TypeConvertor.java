package com.daclink.drew.sp22.cst438_project01_starter.db;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class TypeConvertor implements Serializable {

    @TypeConverter
    public String fromOptionValuesList(ArrayList<Recipe> recipes) {
        if (recipes == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Recipe>>() {
        }.getType();
        String json = gson.toJson(recipes, type);
        return json;
    }

    @TypeConverter
    public ArrayList<Recipe> toOptionValuesList(String recipeString) {
        if (recipeString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Recipe>>() {
        }.getType();
        ArrayList<Recipe> recipeList = gson.fromJson(recipeString, type);
        return recipeList;
    }
}
