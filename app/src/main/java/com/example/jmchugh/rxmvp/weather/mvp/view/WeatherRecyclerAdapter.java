package com.example.jmchugh.rxmvp.weather.mvp.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jmchugh.rxmvp.weather.mvp.model.entity.Forecast;
import com.example.jmchugh.rxmvp.R;
import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * Created by jmchugh on 1/10/2018.
 */

public class WeatherRecyclerAdapter extends RecyclerView.Adapter<WeatherRecyclerViewHolder> {

    private List<Forecast> forecasts;

    @Override
    public WeatherRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_weather_forecast_item, parent, false);

        return new WeatherRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherRecyclerViewHolder holder, int position) {

        holder.bind(forecasts.get(position));
    }

    @Override
    public int getItemCount() {

        return forecasts != null ? forecasts.size() : 0;
    }

    public void setWeatherForecasts(List<Forecast> forecasts){

        if(forecasts == null){

            throw new IllegalArgumentException("List cannot but null.");
        }

        this.forecasts = ImmutableList.copyOf(forecasts);
        notifyDataSetChanged();
    }
}
