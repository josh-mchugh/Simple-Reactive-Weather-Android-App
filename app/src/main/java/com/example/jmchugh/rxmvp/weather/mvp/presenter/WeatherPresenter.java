package com.example.jmchugh.rxmvp.weather.mvp.presenter;

import com.example.jmchugh.rxmvp.weather.mvp.model.TestEntity;
import com.example.jmchugh.rxmvp.weather.mvp.model.WeatherModel;
import com.example.jmchugh.rxmvp.weather.mvp.model.entity.Forecast;
import com.example.jmchugh.rxmvp.weather.mvp.view.WeatherView;

import java.util.List;
import java.util.Random;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

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
                        forecasts -> storeAndUpdateView(forecasts),
                        error -> {
                            Timber.e(error, "Unable to retrieve forecasts.");
                            view.showErrorToast();
                        }
                );

        compositeDisposable.add(weatherDisposable);
    }

    public void onDestroy(){

        compositeDisposable.clear();
    }

    private void storeAndUpdateView(List<Forecast> forecasts){

        model.getBoxStore().boxFor(TestEntity.class).put(new TestEntity(String.format("Test Value %s", Math.abs(new Random().nextInt(100)))));

        view.updateWeatherView(forecasts);
    }
}
