package com.example.jmchugh.cleanarchitectureexample.weather.dagger;

import android.app.Activity;

import dagger.Module;

/**
 * Created by jmchugh on 1/12/2018.
 */

@Module
public class WeatherModule {

    private final Activity activity;

    public WeatherModule(Activity activity){

        this.activity = activity;
    }


}
