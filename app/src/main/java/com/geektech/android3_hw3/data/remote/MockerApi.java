package com.geektech.android3_hw3.data.remote;

import com.geektech.android3_hw3.data.model.Publish;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MockerApi {

    @GET("posts")
    Call<List<Publish>> getPublishes();

    @GET("posts/{id}")
    Call<Publish> getPublish(@Path("id") Integer id);

    @POST("posts")
    Call<Publish> createPublish(@Body Publish publish);

    @DELETE("posts/{id}")
    Call<Publish> deletePublish(@Path("id") Integer publishId);

    @PUT("posts/{id}")
    Call<Publish> updatePublish(@Path("id") Integer id, @Body Publish publish);
}
