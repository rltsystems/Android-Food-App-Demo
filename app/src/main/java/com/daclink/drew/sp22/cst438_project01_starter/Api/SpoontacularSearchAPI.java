package com.daclink.drew.sp22.cst438_project01_starter.Api;

/**
 * An interface which calls the Spoontacular API for a complex recipe search
 */

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SpoontacularSearchAPI {

    //@Headers("Accept-Encoding: gzip")
    @GET("/recipes/complexSearch")
    Call<RecipeResponse> getRecipes(
            @Query("query") String q,
            @Query("apiKey") String app_key,
            @Query("addRecipeInformation") boolean extraInfo,
            @Query("number") int resultMax);
}
