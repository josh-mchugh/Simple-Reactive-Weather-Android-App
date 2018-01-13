package com.example.jmchugh.cleanarchitectureexample.weather.dagger;


import com.example.jmchugh.cleanarchitectureexample.app.dagger.AppComponent;
import com.example.jmchugh.cleanarchitectureexample.weather.WeatherActivity;

import dagger.Component;

/**
 * Created by jmchugh on 1/12/2018.
 */

@WeatherScope
@Component(modules = WeatherModule.class, dependencies = AppComponent.class)
public interface WeatherComponent {

    void inject(WeatherActivity activity);
}
