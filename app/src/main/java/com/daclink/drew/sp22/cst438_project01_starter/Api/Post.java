package com.daclink.drew.sp22.cst438_project01_starter.Api;

/**
 * Class which is used to store the result of a single JSON element from the API
 */

public class Post {
    // query text
    private String q;

    // recipe title
    private String label;

    // calorie count
    private String calories;

    // total cooking time
    private String time;

    // diet type (egg-free, gluten-free, etc.)
    private String[] diet;

    // health diet (high-fiber, low-fat, etc.)
    private String[] health;

    // culture of origin
    private String[] cuisineType;

    // meal type (breakfast, lunch, etc.)
    private String[] mealType;

    // course or dish type (starter, main course, etc.)
    private String[] dishType;


    public String getQ() {
        return q;
    }

    public String getLabel() {
        return label;
    }

    public String getCalories() {
        return calories;
    }

    public String getTime() {
        return time;
    }

    public String[] getDiet() {
        return diet;
    }

    public String[] getHealth() {
        return health;
    }

    public String[] getCuisineType() {
        return cuisineType;
    }

    public String[] getMealType() {
        return mealType;
    }

    public String[] getDishType() {
        return dishType;
    }
}
