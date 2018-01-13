package com.example.jmchugh.cleanarchitectureexample.weather.dagger;

import android.app.Activity;

import com.example.jmchugh.cleanarchitectureexample.app.network.WeatherRetrofitService;
import com.example.jmchugh.cleanarchitectureexample.weather.mvp.model.WeatherModel;
import com.example.jmchugh.cleanarchitectureexample.weather.mvp.presenter.WeatherPresenter;
import com.example.jmchugh.cleanarchitectureexample.weather.mvp.view.WeatherView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jmchugh on 1/12/2018.
 */

@Module
public class WeatherModule {

    private final Activity activity;

    public WeatherModule(Activity activity){

        this.activity = activity;
    }

    @Provides
    @WeatherScope
    public WeatherView view() {

        return new WeatherView(activity);
    }

    @Provides
    @WeatherScope
    public WeatherModel model(WeatherRetrofitService weatherRetrofitService) {

        return new WeatherModel(activity, weatherRetrofitService);
    }

    @Provides
    @WeatherScope
    public WeatherPresenter presenter(WeatherView view, WeatherModel model) {

        return new WeatherPresenter(view, model);
    }
}
