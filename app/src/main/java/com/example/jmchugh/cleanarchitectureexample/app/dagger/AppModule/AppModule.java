package com.example.jmchugh.cleanarchitectureexample.app.dagger.AppModule;

import android.app.Application;
import android.content.Context;

import com.example.jmchugh.cleanarchitectureexample.app.dagger.AppScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jmchugh on 1/12/2018.
 */

@Module
public class AppModule {

    private final Context context;

    public AppModule(Application application){

        this.context = application.getApplicationContext();
    }

    @Provides
    @AppScope
    public Context context() {

        return context;
    }
}
