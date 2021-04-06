package com.e.appentusmachinetest.api;
 

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
 
public class RetrofitClient {

    // Dfine base url for getting list
    private static final String BASE_URL = "https://picsum.photos/v2/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;
 
 
    private RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    // Create instance of Retrofit in Singlton pattern
    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }
 
    public Api getApi() {
        return retrofit.create(Api.class);
    }
}