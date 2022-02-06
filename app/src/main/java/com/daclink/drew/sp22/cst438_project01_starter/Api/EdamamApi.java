package com.daclink.drew.sp22.cst438_project01_starter.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EdamamApi {

    //Tests
    @GET("posts")
    Call<List<Post>> getPosts();
}
