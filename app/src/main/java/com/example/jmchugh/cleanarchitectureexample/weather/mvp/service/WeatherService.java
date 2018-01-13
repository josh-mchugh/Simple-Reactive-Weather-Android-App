package com.example.jmchugh.cleanarchitectureexample.weather.mvp.service;

import android.app.Activity;

import com.example.jmchugh.cleanarchitectureexample.app.network.WeatherRetrofitService;

/**
 * Created by jmchugh on 1/12/2018.
 */

public class WeatherService {

    private final Activity activity;
    private final WeatherRetrofitService weatherRetrofitService;

    public WeatherService(Activity activity, WeatherRetrofitService weatherRetrofitService){

         this.activity = activity;
         this.weatherRetrofitService = weatherRetrofitService;
    }

    public WeatherRetrofitService getWeatherRetrofitService() {

        return weatherRetrofitService;
    }
}
