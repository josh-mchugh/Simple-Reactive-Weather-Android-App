package com.example.jmchugh.cleanarchitectureexample.app.dagger.AppModule;

import android.content.Context;

import com.example.jmchugh.cleanarchitectureexample.app.dagger.AppScope;
import com.example.jmchugh.cleanarchitectureexample.app.network.WeatherRetrofitService;

import java.io.File;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by jmchugh on 1/12/2018.
 */

@Module
public class RetrofitModule {

    @Provides
    @AppScope
    public WeatherRetrofitService weatherRetrofitService(Retrofit retrofit) {

        return retrofit.create(WeatherRetrofitService.class);
    }

    @Provides
    @AppScope
    public Retrofit retrofit(OkHttpClient okHttpClient) {

        return new Retrofit.Builder()
                .baseUrl("https://query.yahooapis.com/v1/public/")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @AppScope
    public OkHttpClient okHttpClient(HttpLoggingInterceptor loggingInterceptor, Cache cache) {

        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(1, TimeUnit.MINUTES)
                .cache(cache)
                .build();
    }

    @Provides
    @AppScope
    public HttpLoggingInterceptor httpLoggingInterceptor() {

        return new HttpLoggingInterceptor(message -> Timber.tag("OkHttp").d(message))
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @AppScope
    public Cache cache(Context context) {

        return new Cache(new File(context.getCacheDir(), "okhttp_cache"), 10 * 1024 * 1024);
    }
}
