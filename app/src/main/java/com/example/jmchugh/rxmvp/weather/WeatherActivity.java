package com.example.jmchugh.rxmvp.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jmchugh.rxmvp.CleanArchitectureApplication;
import com.example.jmchugh.rxmvp.weather.dagger.DaggerWeatherComponent;
import com.example.jmchugh.rxmvp.weather.dagger.WeatherModule;
import com.example.jmchugh.rxmvp.weather.mvp.presenter.WeatherPresenter;
import com.example.jmchugh.rxmvp.weather.mvp.view.WeatherView;

import javax.inject.Inject;

public class WeatherActivity extends AppCompatActivity {

    @Inject
    WeatherView view;

    @Inject
    WeatherPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerWeatherComponent.builder()
                .appComponent(CleanArchitectureApplication.get(this).component())
                .weatherModule(new WeatherModule(this))
                .build().inject(this);

        setContentView(view);
        presenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
