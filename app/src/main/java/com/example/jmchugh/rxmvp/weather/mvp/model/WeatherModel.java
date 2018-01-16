package com.example.jmchugh.rxmvp.weather.mvp.model;

import android.app.Activity;

import com.example.jmchugh.rxmvp.app.network.WeatherRetrofitService;
import com.example.jmchugh.rxmvp.weather.mvp.model.entity.WeatherForecastModel;

import io.objectbox.BoxStore;
import io.reactivex.Observable;

/**
 * Created by ME on 1/12/2018.
 */

public class WeatherModel {

    private final Activity activity;
    private final WeatherRetrofitService retrofitService;
    private final BoxStore boxStore;

    public WeatherModel(Activity activity, WeatherRetrofitService retrofitService, BoxStore boxStore){

        this.activity = activity;
        this.retrofitService = retrofitService;
        this.boxStore = boxStore;
    }

    public Observable<WeatherForecastModel> getWeather(){

        return retrofitService.getWeatherForecast();
    }

    public BoxStore getBoxStore(){

        return boxStore;
    }
}
