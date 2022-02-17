package com.daclink.drew.sp22.cst438_project01_starter.db;

import android.text.Html;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.daclink.drew.sp22.cst438_project01_starter.Api.Result;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@Entity(tableName = "recipes")
public class Recipe {

    @PrimaryKey(autoGenerate = true)
    private int recipeId;

    private String recipeName;
    private Double totalCalories;
    private String recipeDescription;
    public String carbs;
    public String fat;
    //public String image;
    //public String imageType;
    public String protein;
    public String sourceName;
    public String sourceUrl;
    private String instructions;


    public Recipe(String recipeName, Double totalCalories, String recipeDescription) {
        this.recipeName = recipeName;
        this.totalCalories = totalCalories;
        this.recipeDescription = recipeDescription;
    }

    // create a recipe from a search result
    public Recipe(Result searchresult) {
        this.recipeName = searchresult.getTitle();
        this.totalCalories = (searchresult.getCalories() * 1.0);
        this.recipeDescription = searchresult.getSummary();
        this.carbs = searchresult.getCarbs();
        this.fat = searchresult.getFat();
        this.protein = searchresult.getProtein();
        this.sourceUrl = searchresult.getSourceUrl();
        this.sourceName = searchresult.getSourceName();
        this.instructions = searchresult.getInstructions();
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public Double getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(Double totalCalories) {
        this.totalCalories = totalCalories;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void resultToRecipe(Result searchresult){
        this.recipeName = searchresult.getTitle();
        this.totalCalories = (searchresult.getCalories() * 1.0);
        this.carbs = searchresult.getCarbs();
        this.fat = searchresult.getFat();
        this.protein = searchresult.getProtein();
    }

    public String displayRecipeBar(){
        return (this.recipeName + "\nSource: " + this.sourceName);
    }

    public String displayRecipeDescripInstruct(){
        return (Html.fromHtml(this.recipeDescription) + "\n\n" + Html.fromHtml(this.instructions));
    }
}
