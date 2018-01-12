package com.example.jmchugh.cleanarchitectureexample.weather.mvp.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jmchugh.cleanarchitectureexample.weather.mvp.model.Forecast;
import com.example.jmchugh.cleanarchitectureexample.R;
import com.google.common.collect.ImmutableList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jmchugh on 1/10/2018.
 */

public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastAdapter.WeatherForecastHolder> {

    private List<Forecast> forecasts;

    @Override
    public WeatherForecastHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_weather_forecast_item, parent, false);

        return new WeatherForecastHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherForecastAdapter.WeatherForecastHolder holder, int position) {

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

    class WeatherForecastHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.weather_forecast_item_icon)
        ImageView icon;

        @BindView(R.id.weather_forecast_item_min_temp)
        TextView minTemp;

        @BindView(R.id.weather_forecast_item_max_temp)
        TextView maxTemp;

        public WeatherForecastHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Forecast forecast){

            //Glide

            //Text
            minTemp.setText(forecast.getLow());
            maxTemp.setText(forecast.getHigh());
        }
    }
}
