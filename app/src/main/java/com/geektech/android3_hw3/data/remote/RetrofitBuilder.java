package com.geektech.android3_hw3.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private static MockerApi instance;

    private RetrofitBuilder(){}

    public static MockerApi getInstance(){
        if (instance==null){
            instance = createRetrofit();
        }
        return instance;
    }

    private static MockerApi createRetrofit() {
        return (MockerApi) new Retrofit.Builder()
                .baseUrl("https://android-3-mocker.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MockerApi.class);
    }
}
