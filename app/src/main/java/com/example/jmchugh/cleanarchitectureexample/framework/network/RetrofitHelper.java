package com.example.jmchugh.cleanarchitectureexample.framework.network;

import com.example.jmchugh.cleanarchitectureexample.framework.network.OkHttpClientGenerator;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jmchugh on 1/10/2018.
 */

public class RetrofitHelper {

    private static Retrofit retrofit;

    protected RetrofitHelper(){

    }

    public static Retrofit getRetrofit(){

        if(retrofit != null){

            return retrofit;
        }

        retrofit = new Retrofit.Builder()
                .baseUrl("https://query.yahooapis.com/v1/public/")
                .client(OkHttpClientGenerator.getHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

}
