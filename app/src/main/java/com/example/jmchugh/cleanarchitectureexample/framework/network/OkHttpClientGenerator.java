package com.example.jmchugh.cleanarchitectureexample.framework.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

/**
 * Created by jmchugh on 1/10/2018.
 */

public class OkHttpClientGenerator {

    private static OkHttpClient httpClient;

    protected OkHttpClientGenerator(){

    }

    public static OkHttpClient getHttpClient(){

        if(httpClient != null){

            return httpClient;
        }

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.networkInterceptors().add(getHttpLoggingInterceptor());

        httpClient = httpClientBuilder.build();

        return httpClient;
    }

    private static HttpLoggingInterceptor getHttpLoggingInterceptor() {

        return new HttpLoggingInterceptor(message -> Timber.tag("OkHttp").d(message)).setLevel(HttpLoggingInterceptor.Level.BODY);
    }
}
