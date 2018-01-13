package com.example.jmchugh.rxmvp.app.dagger;

import android.content.Context;

import com.example.jmchugh.rxmvp.app.dagger.AppModule.AppModule;
import com.example.jmchugh.rxmvp.app.dagger.AppModule.RetrofitModule;
import com.example.jmchugh.rxmvp.app.network.WeatherRetrofitService;

import dagger.Component;
import okhttp3.OkHttpClient;

/**
 * Created by jmchugh on 1/12/2018.
 */
@AppScope
@Component(modules = {AppModule.class, RetrofitModule.class})
public interface AppComponent {

    Context context();

    OkHttpClient okHttpClient();

    WeatherRetrofitService weatherRetrofitService();
}