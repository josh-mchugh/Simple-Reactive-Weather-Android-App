package com.example.jmchugh.rxmvp.weather.mvp.view;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.jmchugh.rxmvp.R;
import com.example.jmchugh.rxmvp.weather.mvp.model.entity.Forecast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ME on 1/12/2018.
 */

public class WeatherView extends FrameLayout {

    @BindView(R.id.weather_forecast_recycler_view)
    RecyclerView recyclerView;

    private WeatherRecyclerAdapter weatherRecyclerAdapter;

    public WeatherView(Activity activity) {
        super(activity);

        inflate(getContext(), R.layout.activity_main, this);

        ButterKnife.bind(this);

        weatherRecyclerAdapter = new WeatherRecyclerAdapter();
        recyclerView.setAdapter(weatherRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void updateWeatherView(List<Forecast> forecasts){
        
        weatherRecyclerAdapter.setWeatherForecasts(forecasts);
    }
    
    public void showErrorToast(){

        Toast.makeText(getContext(), "An error has occured.", Toast.LENGTH_SHORT).show();
    }
}
