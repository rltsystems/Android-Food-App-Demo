package com.daclink.drew.sp22.cst438_project01_starter.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Interface which holds the API functions and specific call. Currently only a GET is needed.
 */

public interface EdamamApi {

    @Headers({"app_id: 240368db", "app_key: 88fc3f1f7a2140811c629d893f18ed42"})
    @GET("https://api.edamam.com/api/recipes/v2?type=public&q=chicken&app_id=240368db&app_key=88fc3f1f7a2140811c629d893f18ed42")
    Call<List<Post>> getPosts(@Header("Query") String q);
}
