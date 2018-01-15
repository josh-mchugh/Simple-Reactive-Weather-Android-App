package com.example.jmchugh.rxmvp.weather.dagger;


import com.example.jmchugh.rxmvp.app.dagger.AppComponent;
import com.example.jmchugh.rxmvp.weather.WeatherActivity;
import com.example.jmchugh.rxmvp.weather.mvp.view.WeatherView;

import dagger.Component;

/**
 * Created by jmchugh on 1/12/2018.
 */

@WeatherScope
@Component(modules = WeatherModule.class, dependencies = AppComponent.class)
public interface WeatherComponent {

    void inject(WeatherActivity activity);

    void inject(WeatherView view);
}
