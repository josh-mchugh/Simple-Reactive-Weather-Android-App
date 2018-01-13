package com.example.jmchugh.cleanarchitectureexample.app.network;

import com.example.jmchugh.cleanarchitectureexample.weather.mvp.model.entity.WeatherForecastModel;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by jmchugh on 1/10/2018.
 */

public interface WeatherRetrofitService {

    @GET("yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22cincinnati%2C%20oh%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys")
    Observable<WeatherForecastModel> getWeatherForecast();
}
