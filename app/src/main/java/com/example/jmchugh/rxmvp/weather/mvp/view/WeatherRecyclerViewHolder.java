package com.example.jmchugh.rxmvp.weather.mvp.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.jmchugh.rxmvp.R;
import com.example.jmchugh.rxmvp.weather.mvp.model.entity.Forecast;
import com.github.pwittchen.weathericonview.WeatherIconView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ME on 1/14/2018.
 */

public class WeatherRecyclerViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.weather_forecast_date_text_view)
    TextView forecastDate;

    @BindView(R.id.weather_forecast_item_min_temp)
    TextView minTemp;

    @BindView(R.id.weather_forecast_item_max_temp)
    TextView maxTemp;

    @BindView(R.id.weather_forecast_item_icon)
    WeatherIconView weatherIconView;

    private Context context;

    public WeatherRecyclerViewHolder(View itemView){
        super(itemView);
        this.context = itemView.getContext();
        ButterKnife.bind(this, itemView);
    }

    public void bind(Forecast forecast){

        weatherIconView.setIconResource(context.getString(R.string.wi_day_sunny_overcast));

        forecastDate.setText(forecast.getDate());

        //Text
        minTemp.setText(forecast.getLow());
        maxTemp.setText(forecast.getHigh());
    }
}
