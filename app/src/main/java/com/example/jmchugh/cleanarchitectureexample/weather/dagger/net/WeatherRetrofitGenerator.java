package com.example.jmchugh.cleanarchitectureexample.weather.data.net;

import com.example.jmchugh.cleanarchitectureexample.framework.network.RetrofitHelper;

/**
 * Created by jmchugh on 1/10/2018.
 */

public class WeatherRetrofitGenerator {

    private static WeatherRetrofitService weatherRetrofitService;

    public static WeatherRetrofitService getWeatherRetrofitService(){

        if(weatherRetrofitService != null){

            return weatherRetrofitService;
        }

        weatherRetrofitService = RetrofitHelper.getRetrofit().create(WeatherRetrofitService.class);

        return weatherRetrofitService;
    }
}
