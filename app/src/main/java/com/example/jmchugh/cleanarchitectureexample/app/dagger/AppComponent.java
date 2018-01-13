package com.example.jmchugh.cleanarchitectureexample.app.dagger;

import android.content.Context;

import com.example.jmchugh.cleanarchitectureexample.app.dagger.AppModule.AppModule;
import com.example.jmchugh.cleanarchitectureexample.app.dagger.AppModule.RetrofitModule;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by jmchugh on 1/12/2018.
 */
@AppScope
@Component(modules = {AppModule.class, RetrofitModule.class})
public interface AppComponent {

    Context context();

    OkHttpClient okHttpClient();

    Retrofit retrofit();
}
