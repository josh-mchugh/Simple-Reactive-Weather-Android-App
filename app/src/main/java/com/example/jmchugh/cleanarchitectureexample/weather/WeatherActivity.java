package com.example.jmchugh.cleanarchitectureexample.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.crashlytics.android.Crashlytics;
import com.example.jmchugh.cleanarchitectureexample.BuildConfig;
import com.example.jmchugh.cleanarchitectureexample.CleanArchitectureApplication;
import com.example.jmchugh.cleanarchitectureexample.app.dagger.AppModule.RetrofitModule;
import com.example.jmchugh.cleanarchitectureexample.app.network.WeatherRetrofitService;
import com.example.jmchugh.cleanarchitectureexample.weather.dagger.DaggerWeatherComponent;
import com.example.jmchugh.cleanarchitectureexample.weather.dagger.WeatherModule;
import com.example.jmchugh.cleanarchitectureexample.weather.mvp.service.WeatherService;
import com.example.jmchugh.cleanarchitectureexample.weather.mvp.view.WeatherForecastAdapter;
import com.example.jmchugh.cleanarchitectureexample.R;
import com.example.jmchugh.cleanarchitectureexample.app.logging.CrashReportTree;
import com.google.firebase.analytics.FirebaseAnalytics;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class WeatherActivity extends AppCompatActivity {

    @BindView(R.id.weather_forecast_recycler_view)
    RecyclerView recyclerView;

    private WeatherForecastAdapter weatherForecastAdapter;

    private Disposable weatherSubscription;

    @Inject
    WeatherService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerWeatherComponent.builder()
                .appComponent(CleanArchitectureApplication.get(this).component())
                .weatherModule(new WeatherModule(this))
                .build().inject(this);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        weatherForecastAdapter = new WeatherForecastAdapter();
        recyclerView.setAdapter(weatherForecastAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        weatherSubscription = service.getWeatherRetrofitService()
                .getWeatherForecast()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        weatherForecast -> weatherForecastAdapter.setWeatherForecasts(weatherForecast.getQuery().getResults().getChannel().getItem().getForecast()),
                      error -> Timber.e(error, "Unable to retrieve weather data.")
                );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        this.weatherSubscription.dispose();
    }
}
