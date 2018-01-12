package com.example.jmchugh.cleanarchitectureexample.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.crashlytics.android.Crashlytics;
import com.example.jmchugh.cleanarchitectureexample.BuildConfig;
import com.example.jmchugh.cleanarchitectureexample.weather.data.net.WeatherRetrofitGenerator;
import com.example.jmchugh.cleanarchitectureexample.weather.mvp.view.WeatherForecastAdapter;
import com.example.jmchugh.cleanarchitectureexample.R;
import com.example.jmchugh.cleanarchitectureexample.framework.logging.CrashReportTree;
import com.google.firebase.analytics.FirebaseAnalytics;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Timber.plant(BuildConfig.DEBUG ? new Timber.DebugTree() : new CrashReportTree());

        Fabric.with(this, new Crashlytics());

        FirebaseAnalytics.getInstance(this);

        weatherForecastAdapter = new WeatherForecastAdapter();
        recyclerView.setAdapter(weatherForecastAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        weatherSubscription = WeatherRetrofitGenerator.getWeatherRetrofitService()
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
