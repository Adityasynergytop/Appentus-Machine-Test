package com.e.appentusmachinetest.datasource;



import com.e.appentusmachinetest.api.RetrofitClient;

import com.e.appentusmachinetest.model.ImageResponsemodel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
 
public class ItemDataSource extends PageKeyedDataSource<Integer, ImageResponsemodel> {
 
    //the size of a page that we want
    public static final int PAGE_SIZE = 30;
 
    //we will start from the first page which is 1
    private static final int FIRST_PAGE = 1;

 
 
    //this will be called once to load the initial data
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, ImageResponsemodel> callback) {
        RetrofitClient.getInstance()
                .getApi().getList(FIRST_PAGE)
                .enqueue(new Callback<List<ImageResponsemodel>>() {
                    @Override
                    public void onResponse(Call<List<ImageResponsemodel>> call, Response<List<ImageResponsemodel>> response) {
                        if (response.body() != null) {
                            callback.onResult(response.body(), null, FIRST_PAGE + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ImageResponsemodel>> call, Throwable t) {


                    }


                });
    }
 
    //this will load the previous page
    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, ImageResponsemodel> callback) {
        RetrofitClient.getInstance()
                .getApi().getList(params.key)
                .enqueue(new Callback<List<ImageResponsemodel>>() {
                    @Override
                    public void onResponse(Call<List<ImageResponsemodel>> call, Response<List<ImageResponsemodel>> response) {
 
                        //if the current page is greater than one
                        //we are decrementing the page number
                        //else there is no previous page
                        Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                        if (response.body() != null) {
 
                            //passing the loaded data
                            //and the previous page key 
                            callback.onResult(response.body(), adjacentKey);
                        }
                    }
 
                    @Override
                    public void onFailure(Call<List<ImageResponsemodel>> call, Throwable t) {

                    }
                });
    }
 
    //this will load the next page
    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, ImageResponsemodel> callback) {
        RetrofitClient.getInstance()
                .getApi()
                .getList(params.key)
                .enqueue(new Callback<List<ImageResponsemodel>>() {
                    @Override
                    public void onResponse(Call<List<ImageResponsemodel>> call, Response<List<ImageResponsemodel>> response) {
                        
                        if (response.body() != null) {
                            //if the response has next page
                            //incrementing the next page number
                            Integer key = response.body().size()>0 ? params.key + 1 : null;
                            
                            //passing the loaded data and next page value 
                            callback.onResult(response.body(), key);
                        }
                    }
 
                    @Override
                    public void onFailure(Call<List<ImageResponsemodel>> call, Throwable t) {

                    }
                });
    }
}