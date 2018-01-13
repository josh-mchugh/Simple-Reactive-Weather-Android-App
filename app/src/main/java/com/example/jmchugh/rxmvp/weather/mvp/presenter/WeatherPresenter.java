package com.example.jmchugh.rxmvp.weather.mvp.presenter;

import com.example.jmchugh.rxmvp.weather.mvp.model.WeatherModel;
import com.example.jmchugh.rxmvp.weather.mvp.view.WeatherView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jmchugh on 1/12/2018.
 */

public class WeatherPresenter {

    private final WeatherView view;
    private final WeatherModel model;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public WeatherPresenter(WeatherView view, WeatherModel model){

        this.view = view;
        this.model = model;
    }

    public void onCreate(){

        Disposable weatherDisposable = model.getWeather()
                .map(weather -> weather.getQuery().getResults().getChannel().getItem().getForecast())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        forecasts -> view.updateWeatherView(forecasts),
                        error -> view.showErrorToast()
                );

        compositeDisposable.add(weatherDisposable);
    }

    public void onDestroy(){
        compositeDisposable.clear();
    }
}
