package com.daclink.drew.sp22.cst438_project01_starter.Api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * A class to hold the data for a single recipe from the Spoontacular API json
 */

public class Result {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("calories")
    @Expose
    public int calories;
    @SerializedName("carbs")
    @Expose
    public String carbs;
    @SerializedName("fat")
    @Expose
    public String fat;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("imageType")
    @Expose
    public String imageType;
    @SerializedName("protein")
    @Expose
    public String protein;
    @SerializedName("summary")
    @Expose
    public String summary;
    @SerializedName("sourceUrl")
    @Expose
    public String sourceUrl;
    @SerializedName("sourceName")
    @Expose
    public String sourceName;
    @SerializedName("instructions")
    @Expose
    public String instructions;
    @SerializedName("readyInMinutes")
    @Expose
    public int readyInMinutes;
    @SerializedName("servings")
    @Expose
    public int servings;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getCarbs() {
        return carbs;
    }

    public void setCarbs(String carbs) {
        this.carbs = carbs;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getSummary() {
        return summary;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getInstructions() {
        return instructions;
    }

    public int getReadyInMinutes() {
        return readyInMinutes;
    }

    public int getServings() {
        return servings;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", calories=" + calories +
                ", carbs='" + carbs + '\'' +
                ", fat='" + fat + '\'' +
                ", image='" + image + '\'' +
                ", imageType='" + imageType + '\'' +
                ", protein='" + protein + '\'' +
                '}';
    }
}
