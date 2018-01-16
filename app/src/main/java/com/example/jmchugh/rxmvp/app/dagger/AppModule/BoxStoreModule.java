package com.example.jmchugh.rxmvp.app.dagger.AppModule;

import android.content.Context;

import com.example.jmchugh.rxmvp.app.dagger.AppScope;
import com.example.jmchugh.rxmvp.weather.mvp.model.MyObjectBox;

import dagger.Module;
import dagger.Provides;
import io.objectbox.BoxStore;

/**
 * Created by jmchugh on 1/16/2018.
 */

@Module
public class BoxStoreModule {

    @Provides
    @AppScope
    public BoxStore boxStore(Context context) {

        return MyObjectBox.builder().androidContext(context).build();
    }
}
