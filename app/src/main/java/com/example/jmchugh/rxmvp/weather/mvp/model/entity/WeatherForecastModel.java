package com.example.jmchugh.rxmvp.weather.mvp.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherForecastModel {

    @SerializedName("query")
    @Expose
    private Query query;

    public Query getQuery() {

        return query;
    }

    public void setQuery(Query query) {

        this.query = query;
    }

}
