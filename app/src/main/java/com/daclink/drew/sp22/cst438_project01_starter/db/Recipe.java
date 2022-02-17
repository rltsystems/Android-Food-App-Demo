package com.daclink.drew.sp22.cst438_project01_starter.db;

import android.text.Html;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.daclink.drew.sp22.cst438_project01_starter.Api.Result;

/**
 * A class which holds all recipe information for local use
 */

@Entity(tableName = "recipes")
public class Recipe {

    @PrimaryKey(autoGenerate = true)
    private int recipeId;

    private String recipeName;
    private Double totalCalories;
    private String recipeDescription;
    public String carbs;
    public String fat;
    public String image;
    public String imageType;
    public String protein;
    public String sourceName;
    public String sourceUrl;
    private String instructions;
    private int readyInMinutes;
    private int servings;

    @Ignore
    public Recipe(String recipeName, Double totalCalories, String recipeDescription) {
        this.recipeName = recipeName;
        this.totalCalories = totalCalories;
        this.recipeDescription = recipeDescription;
    }

    public Recipe(String recipeName, int servings, int readyInMinutes, String recipeDescription,
                  String sourceName, String sourceUrl) {
        this.recipeName = recipeName;
        this.servings = servings;
        this.readyInMinutes= readyInMinutes;
        this.recipeDescription = recipeDescription;
        this.sourceName = sourceName;
        this.sourceUrl = sourceUrl;
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
        this.readyInMinutes = searchresult.getReadyInMinutes();
        this.servings = searchresult.getServings();
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
        return (this.recipeName + "\n\nServings: " + this.servings + "   |   Ready in " +
                this.readyInMinutes + " minutes" + "\n\nFrom: " + this.sourceName +
                "\n\nGet the Full Recipe:\n " + this.sourceUrl);
    }

    public String displayRecipeDescripInstruct(){
        return (Html.fromHtml(this.recipeDescription) + "\n\n" + Html.fromHtml(this.instructions));
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCarbs() {
        return carbs;
    }

    public String getFat() {
        return fat;
    }

    public String getImageType() {
        return imageType;
    }

    public String getProtein() {
        return protein;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public int getReadyInMinutes() {
        return readyInMinutes;
    }

    public int getServings() {
        return servings;
    }

    public void setCarbs(String carbs) {
        this.carbs = carbs;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public void setReadyInMinutes(int readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }
}
