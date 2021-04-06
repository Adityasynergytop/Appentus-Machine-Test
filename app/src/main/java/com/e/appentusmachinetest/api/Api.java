package com.e.appentusmachinetest.api;

import com.e.appentusmachinetest.model.ImageResponsemodel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
 
public interface Api {


    // call Api for get List of image and other details
    @GET("list")
    Call<List<ImageResponsemodel>> getList(@Query("page") int page);
}