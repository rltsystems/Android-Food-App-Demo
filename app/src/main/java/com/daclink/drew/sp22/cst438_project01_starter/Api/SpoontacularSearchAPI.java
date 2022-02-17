package com.daclink.drew.sp22.cst438_project01_starter.Api;

import com.daclink.drew.sp22.cst438_project01_starter.db.Recipe;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SpoontacularSearchAPI {

    //@Headers("Accept-Encoding: gzip")
    @GET("/recipes/complexSearch")
    Call<RecipeResponse> getRecipes(
            @Query("q") String q,
            @Query("apiKey") String app_key,
            @Query("number") int resultMax);
}
